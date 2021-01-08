package org.asmeta.framework.enforcerAirConditioner;

import java.io.*;
import java.util.Scanner;

import org.asmeta.framework.enforcer.Enforcer;
import org.asmeta.framework.enforcer.FeedbackLoop;
import org.asmeta.framework.enforcer.Knowledge;
import org.asmeta.framework.managedSystem.ExtManagedSystemAC;
public class mainExtIOSanitisationLoop{
	
public static void main(String[] args)  {
	
	/** Initialization*/

	Enforcer.setConfigFile("./resources/AirConditioner/configL.properties");

	//Create system handle 
	ExtManagedSystemAC managedSystem =  new ExtManagedSystemAC(); 
	//Create system knowledge and loop
	Knowledge k = new KnowledgeAirConditioner();
	FeedbackLoop loop = new FeedbackLoopAirConditionerExt(managedSystem.getProbe(),managedSystem.getEffector(),k);
	
	//Create a new specialized enforcer for the AirConditioner system
	Enforcer e = new EnforcerAirConditioner_v2(managedSystem, k, loop);
	
	/** Running*/
	//Scanner input = new Scanner(System.in);
	//String s;
	try {
		/** Running -- example of output sanitisation via MAPE-K*/
		System.out.println("Initial air speed of the system: " + managedSystem.getAirIntensity());
		managedSystem.setRoomTemperature(40);
		//managedSystem.setAirIntensity();
		System.out.println("Room temperature measured by the system: " + managedSystem.getRoomTemperature());
		System.out.println("Air speed set by the system: " + managedSystem.getAirIntensity());
		//Output sanitisation via a MAPE-K loop
		e.runLoop();
		System.out.println("Air speed forced to: " + ((KnowledgeAirConditioner) k).airSpeed);
		System.out.println("Air speed set by the system: " + managedSystem.getAirIntensity());
		System.out.println("Starting/ending loop time: " + loop.getStartTime() + "\t" + loop.getEndTime() + "\n\n");
		
		managedSystem.setRoomTemperature(22);
		//managedSystem.setAirIntensity();
		System.out.println("Room temperature measured by the system: " + managedSystem.getRoomTemperature());
		System.out.println("Air speed set by the system: " + managedSystem.getAirIntensity());
		//Output sanitisation via a MAPE-K loop
		e.runLoop();
		System.out.println("Air speed forced to: " + ((KnowledgeAirConditioner) k).airSpeed);
		System.out.println("Air speed set by the system: " + managedSystem.getAirIntensity());
		System.out.println("Starting/ending loop time: " + loop.getStartTime() + "\t" + loop.getEndTime());
	}
	catch (Exception ex) {
		ex.printStackTrace();
	}
	finally {
		
	}
	System.out.println("Shutting down the system ...Done.");
}
}