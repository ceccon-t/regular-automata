package ufrgs.inf.formais.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import ufrgs.inf.formais.automata.NFA;
import ufrgs.inf.formais.builders.NFABuilder;
import ufrgs.inf.formais.helper.State;
import ufrgs.inf.formais.helper.StateSymbolTuple;
import ufrgs.inf.formais.helper.Symbol;

public class NFAStorage {
	
	public void save(NFA nfa, String pathToSave) throws IOException {
		String formattedNfa = toFileFormat(nfa);
		Files.write(Paths.get(pathToSave), formattedNfa.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
	}
	
	public NFA load(File file) throws IOException {
		/*
		 * Expects a nfa specification of the following format, on a text file:
		 * 
		 * Name=({comma-separated alphabet},{comma-separated states},initial state,{comma-separated final states})
		 * Prog
		 * (state,symbol)=state		one or more lines in this format, defining the transition function
		 * 
		 * 
		 * Example:
		 * 
		 * AUTOMATON=({a,b},{q0,q1,q2,q3},q0,{q1,q3})
		 * Prog
		 * (q0,a)=q1
		 * (q0,b)=q2
		 * (q1,b)=q2
		 * (q2,a)=q3
		 * (q2,a)=q2
		 * (q3,a)=q3
		 * (q3,b)=q2
		 * 
		 */
		
		NFABuilder builder = new NFABuilder();
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		int initPos;
		int lastPos;
		
		String definitions;
		
		String top = br.readLine();
		
		if (top.indexOf("Integrantes:") == -1) { // Is in official format, should read as specified on project definition 
			String[] header = top.split("=");
			builder.setName(header[0]);
			
			definitions = header[1].substring(header[1].indexOf('(')+1, header[1].indexOf(')'));
			
			initPos = 1;
			lastPos = definitions.indexOf('}');
			String[] symbols = definitions.substring(initPos, lastPos).split(",");
			builder.addSymbolListToAlphabetFromString(Arrays.asList(symbols));
			
			initPos = lastPos+3;
			lastPos = definitions.indexOf('}', initPos);
			String[] states = definitions.substring(initPos, lastPos).split(",");
			builder.addStateListFromString(Arrays.asList(states));
		} else { // Is in alternate format, should read as specified on project example
			br.readLine(); // jumps an empty line separating list of group integrants from automaton definition
			top = br.readLine();
			
			String[] header = top.split("=");
			builder.setName(header[0]);
			
			definitions = header[1].substring(header[1].indexOf('(')+1, header[1].indexOf(')'));
			
			// Order of alphabet and set of states is flipped from official definition, so read accordingly
			initPos = 1;
			lastPos = definitions.indexOf('}');
			String[] states = definitions.substring(initPos, lastPos).split(",");
			builder.addStateListFromString(Arrays.asList(states));
			
			initPos = definitions.indexOf('{', lastPos)+1;
			lastPos = definitions.indexOf('}', initPos);
			String[] symbols = definitions.substring(initPos, lastPos).split(",");
			builder.addSymbolListToAlphabetFromString(Arrays.asList(symbols));
		}
		
		initPos = lastPos+2;
		lastPos = definitions.indexOf(',', initPos);
		builder.setInitialStateFromString(definitions.substring(initPos, lastPos).trim());
		
		initPos = definitions.indexOf('{', lastPos) + 1;
		lastPos = definitions.indexOf('}', initPos);
		String[] finalStates = definitions.substring(initPos, lastPos).split(",");
		builder.addFinalStateListFromString(Arrays.asList(finalStates));
		
		br.readLine(); // skip 'Prog' line
		
		String transition;
		while ((transition = br.readLine()) != null) {
			String initState = transition.substring(transition.indexOf('(')+1, transition.indexOf(','));
			String symbol = transition.substring(transition.indexOf(',')+1, transition.indexOf(')'));
			String destinyState = transition.substring(transition.indexOf("=")+1).trim();
			builder.addTransitionFromStrings(initState, symbol, destinyState);
		}
		
		br.close();
		
		return builder.build();
	}

	private String toFileFormat(NFA nfa) {
		StringBuilder sb = new StringBuilder();
		
		// Name
		sb.append(nfa.getName() + "=");
		
		// Definition
		sb.append("(");
		//   Alphabet
		sb.append("{");
		int symbolsSoFar = 0;
		int symbolsTotal = nfa.getAlphabet().size();
		for (Symbol symbol : nfa.getAlphabet()) {
			sb.append(symbol);
			symbolsSoFar++;
			if (symbolsSoFar < symbolsTotal) sb.append(","); 
		}
		sb.append("},");
		//   States
		sb.append("{");
		//     All states
		int statesSoFar = 0;
		int statesTotal = nfa.getStates().size();
		for (State state : nfa.getStates()) {
			sb.append(state);
			statesSoFar++;
			if (statesSoFar < statesTotal) sb.append(",");
		}
		//     Initial State
		sb.append("}," + nfa.getInitialState() + ",");
		//     Final States
		sb.append("{");
		int finalStatesSoFar = 0;
		int finalStatesTotal = nfa.getFinalStates().size();
		for (State finalState : nfa.getFinalStates()) {
			sb.append(finalState);
			finalStatesSoFar++;
			if (finalStatesSoFar < finalStatesTotal) sb.append(",");
		}
		sb.append("}");
		sb.append(")\n");
		
		// Transition function
		sb.append("Prog");
		sb.append("\n");
		for (Map.Entry<StateSymbolTuple, HashSet<State>> transition : nfa.getTransitionFunction().entrySet()) {
			State originState = transition.getKey().getState();
			Symbol symbol = transition.getKey().getSymbol();
			for (State destinationState : transition.getValue()) {
				sb.append("(" + originState + "," + symbol + ")=" + destinationState + "\n");
			}
		}
		
		return sb.toString();
	}
}
