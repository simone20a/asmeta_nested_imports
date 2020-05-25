package org.asmeta.atgt.generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.asmeta.nusmv.AsmetaSMV;
import org.asmeta.nusmv.AsmetaSMVOptions;

import tgtlib.definitions.expression.Expression;

/**
 * generates the test by Nusmv 
 *
 */
public class TestGenerationWithNuSMV {
	
	private String asmPath;
	private Expression tp;
	private AsmetaSMV asmetaSMV;

	static private Logger logger = Logger.getLogger(TestGenerationWithNuSMV.class);
	
	public static boolean useLTLandBMC = false;
	
	/**
	 * 
	 * @param asmPath
	 * @param expression
	 * @throws Exception
	 */
	public TestGenerationWithNuSMV(String asmPath, Expression expression) throws Exception {
		this.asmPath = asmPath;
		this.tp = expression;
		org.asmeta.nusmv.AsmetaSMVOptions.useCoi = false;
	}

	public Map<String, String> getVariablesMap() {
		return asmetaSMV.mv.nusmvNameToLocation;
	}

	private void buildNuSMV() throws Exception {
		logger.debug("building the asmetasmv");
		asmetaSMV = new AsmetaSMV(asmPath);
		AsmetaSMVOptions.keepNuSMVfile = true;
		AsmetaSMVOptions.simplifyDerived = false;
		AsmetaSMVOptions.setPrintCounterExample(true);
		logger.debug("translate the maps");
		asmetaSMV.translation();
		logger.debug("add cex");
		Set<String> trapProps = new HashSet<String>();
		String tpS = tp.accept(ExpressionToSMV.EXPR_TO_SMV).toString();
		if (useLTLandBMC) {
			// use LTL
			trapProps.add("G(!((" + tpS + ") & X(TRUE)))");
			asmetaSMV.addLtlProperties(trapProps);
		} else {
			// normal trap property
			trapProps.add("AG(!(" + tpS + "))");			
			asmetaSMV.addCtlProperties(trapProps);
		}
		asmetaSMV.createNuSMVfile();
	}

	/**
	 * It checks the test predicate with id "tpId". If the test predicate is
	 * infeasible it returns null, otherwise counterexample.
	 * 
	 * @param tpId
	 * @return
	 * @throws Exception
	 */
	public Counterexample checkTpWithModelChecker() throws Exception {
		buildNuSMV();
		// 
		asmetaSMV.useBMC = useLTLandBMC;
		asmetaSMV.executeNuSMV();
		BufferedReader br = new BufferedReader(new StringReader(asmetaSMV.outputRunNuSMVreplace));
		//System.err.println(asmetaSMV.outputRunNuSMVreplace);
		return parseCounterExample(br);
	}

	/**
	 * reads the counter example from the reader
	 * 
	 * @param br
	 * @return
	 * @throws IOException
	 */
	static Counterexample parseCounterExample(BufferedReader br) throws IOException {
		br.readLine();
		boolean result = br.readLine().contains("is true");
		Counterexample counterexample = null;
		if (result) {
			br.close();
		} else {
			br.readLine();
			br.readLine();
			br.readLine();
			String line;
			counterexample = new Counterexample();
			ModelCheckerState nusmvState = null;
			boolean loopStart = false;
			while ((line = br.readLine()) != null) {
				if (line.matches(" *-> State: [0-9]+.[0-9]+ <-")) {
					if (nusmvState != null) {
						counterexample.addState(nusmvState);
					}
					nusmvState = new ModelCheckerState(loopStart);
					loopStart = false;
				} else if (line.contains("-- Loop starts here")) {
					loopStart = true;
				} else if (TestGenerationWithNuSMV.useLTLandBMC && line.startsWith("--")) {
					continue;
				} else {
					String[] varValue = line.replaceAll(" ", "").split("=");
					if (varValue.length == 2) {
						nusmvState.addVarValue(varValue[0], varValue[1]);
					} 
				}
			}
			if (nusmvState != null) {
				counterexample.addState(nusmvState);
			}
			br.close();
			completeCounterExample(counterexample);
		}
		return counterexample;
	}

	/**
	 * It completes a counterexample trace. Indeed, if the value of a variable
	 * is unchanged passing from state s_{i} to state s_{i+1}, in state s_{i+1}
	 * it is not printed.
	 * 
	 * non so se � necessario che adesso il generatore pu� farlo lui ....
	 */
	static protected void completeCounterExample(Counterexample counterexample) {
		for (int i = 1; i < counterexample.length(); i++) {
			ModelCheckerState currentState = counterexample.getState(i);
			ModelCheckerState nextState = counterexample.getState(i + 1);
			Set<String> nextStateVars = nextState.getVars();
			for (String var : currentState.getVars()) {
				if (!nextStateVars.contains(var)) {
					nextState.addVarValue(var, currentState.getVarValue(var));
				}
			}
		}
	}
}