package ufrgs.inf.formais.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Map;

import ufrgs.inf.formais.automata.NFA;
import ufrgs.inf.formais.helper.State;
import ufrgs.inf.formais.helper.StateSymbolTuple;
import ufrgs.inf.formais.helper.Symbol;

public class NFAStorage {
	
	public void save(NFA nfa, String pathToSave) throws IOException {
		String formattedNfa = toFileFormat(nfa);
		Files.write(Paths.get(pathToSave), formattedNfa.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
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
