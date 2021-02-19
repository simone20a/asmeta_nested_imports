/*******************************************************************************
 * Copyright (c) 2005, 2006 ASMETA group (http://asmeta.sourceforge.net)
 * License Information: http://asmeta.sourceforge.net/licensing/
 * 
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License version 2 as
 *   published by the Free Software Foundation.
 * 
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 * 
 *   You should have received a copy of the GNU General Public License
 *   along with this program; if not, write to the Free Software
 *   Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301
 *   USA
 * 
 *   http://www.gnu.org/licenses/gpl.txt
 * 
 *   
 *******************************************************************************/

package org.asmeta.simulator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.xml.Log4jEntityResolver;
import org.asmeta.parser.Utility;
import org.asmeta.simulator.readers.MonFuncReader;
import org.asmeta.simulator.util.StandardLibrary;
import org.asmeta.simulator.value.IntegerValue;
import org.asmeta.simulator.value.UndefValue;
import org.asmeta.simulator.value.Value;

import asmeta.definitions.DefinitionsFactory;
import asmeta.definitions.Function;
import asmeta.definitions.MonitoredFunction;
import asmeta.definitions.domains.DomainsFactory;

/**
 * The environment returns the value of the monitored functions.
 */
public final class Environment {

	private static final Object[][] OBJECTS = new Object[][] { 
		{ "mCurrTimeNanosecs", TimeUnit.NANOSECONDS },
		{ "mCurrTimeMillisecs", TimeUnit.MILLISECONDS }, 
		{ "mCurrTimeSecs", TimeUnit.SECONDS },
		{ "mCurrTimeMins", TimeUnit.MINUTES },
		{ "mCurrTimeHours", TimeUnit.HOURS }		
	};

	public enum TimeMngt {
		// link the time variable to the time of the java machine
		use_java_time,
		// ask the user
		ask_user,
		// increment the time unit by 1 at each step
		// TODO delta
		auto_increment;
	}
	// map from monitored functions to time units
	final static Map<String, TimeUnit> monTimeFunctions = Stream
			.of(OBJECTS)
			.collect(Collectors.toMap(data -> (String) data[0], data -> (TimeUnit) data[1]));
	// map from time units to functions names - reverse
	final static Map<TimeUnit,String> monTimeUnits = monTimeFunctions.entrySet()
		       .stream()
		       .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));

	// it can be null: automatic
	public static TimeUnit currentTimeUnit = null;
	public static TimeMngt timeMngt;
	

	private static boolean START_TIME_FROM_0 = true;
	/**
	 * Parses an input string and returns a value.
	 */
	private MonFuncReader monFuncReader;

	private Instant startFrom;
	private Instant currentStateInstant;


	
	public Environment(MonFuncReader monFuncReader) {
		this.monFuncReader = monFuncReader;
		if (START_TIME_FROM_0)
			startFrom = Instant.now();
		else
			startFrom = Instant.MIN;
		currentStateInstant = startFrom;
	}

	/**
	 * Returns the value of a monitored function.
	 * 
	 * @param location a location
	 * @param state    the current state
	 * @return the location value
	 */
	public Value<?> read(Location location, State state) {
		// state does not contain location value otherwise must not be asked
		assert state.locationMap.get(location) == null;
		// if it is time, read in a special way
		// check is the location refers to time quantities (special monitored functions)
		if (location.getName().startsWith("mCurrTime")) {
			assert monTimeFunctions.keySet().contains(location.getName());
			// check if a time is already set in the state
			// convert if possible
			// if it is not possible throw Exception
			return find_mCurrTime(state, location);
		}
		// not a time location
		return monFuncReader.read(location, state);
	}

	// find the current time of the location in tu timeunit
	// considering that the teh current time could be set in other time units
	private Value find_mCurrTime(State state, Location location) {
		// extract the time unit
		TimeUnit locationTimeUnit = getTimeUnit(location);
		assert locationTimeUnit != null;
		// set the time unit if not already set
		if (currentTimeUnit == null)
			currentTimeUnit = locationTimeUnit;
		// scan the state and look for time of the time unit for the simulation
		// case base, it is asking a time in the time of the simulation
		// check if its convertible
		//if (timeMngt != TimeMngt.use_java_time && currentTimeUnit.convert(1, locationTimeUnit) == 0 )
		//	// log a warning
		//
		// check if time information is already memorized in the state
		//
		boolean timeSet = state.getMonLocs().keySet().stream().anyMatch( x -> monTimeFunctions.keySet().contains(x.getName()));
		// if not found, it's the first time the simulator has been asked to get 
		// the  time in the current state in the current Time unit
		// set current instant for the state in the current Time unit
		if (!timeSet) {
			System.out.println("no time info in the current state - looking for " + currentTimeUnit);
			if (timeMngt == TimeMngt.use_java_time) {
				currentStateInstant = Instant.now();
			} else if (timeMngt == TimeMngt.ask_user) {
				// if asking to the user a differen time format 
				if (locationTimeUnit != currentTimeUnit) {
					// not java time and location time unit != current Time unit
					// create the location and ask to the user
					MonitoredFunction func = DefinitionsFactory.eINSTANCE.createMonitoredFunction();
					func.setName(monTimeUnits.get(currentTimeUnit));
					func.setArity(0);
					func.setCodomain(DomainsFactory.eINSTANCE.createIntegerDomain());
					Location timeLocation = new Location(func, new IntegerValue[0]); 
					Value<Long> firstTimeValue = monFuncReader.read(timeLocation, state);
					currentStateInstant = startFrom.plus(firstTimeValue.getValue(),currentTimeUnit.toChronoUnit());
					System.out.println("setting current time  " + firstTimeValue + " " + monTimeUnits.get(currentTimeUnit));
				} else {
					// ask this right way
					Value<Long> firstTimeValue = monFuncReader.read(location, state);
					currentStateInstant = startFrom.plus(firstTimeValue.getValue(),currentTimeUnit.toChronoUnit());
					System.out.println("setting current (and location) time  " + firstTimeValue + " " + monTimeUnits.get(currentTimeUnit));
					// done in this case
					return firstTimeValue;
				} 
			} else {
				assert timeMngt == TimeMngt.auto_increment;
				currentStateInstant = currentStateInstant.plus(1, currentTimeUnit.toChronoUnit());
			}
		}
		// compute the time 
		//it could be also in the ask_user and current time unit as well, but not ask it again
		long deltaTime = startFrom.until(currentStateInstant,  locationTimeUnit.toChronoUnit());
		System.out.println("converting  " + deltaTime + locationTimeUnit.toChronoUnit());
		return new IntegerValue(deltaTime);
	}

	// return the temporal unit
	private TimeUnit getTimeUnit(Location location) {
		assert monTimeFunctions.containsKey(location.getName());
		return monTimeFunctions.get(location.getName());
	}

}
