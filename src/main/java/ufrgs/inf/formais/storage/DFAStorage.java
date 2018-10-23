package ufrgs.inf.formais.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import ufrgs.inf.formais.automata.DFA;
import ufrgs.inf.formais.helper.State;
import ufrgs.inf.formais.helper.StateSymbolTuple;
import ufrgs.inf.formais.helper.Symbol;

public class DFAStorage {
	
	public void save(DFA dfa, String pathToSave) throws IOException {
		String formattedDfa = toFileFormat(dfa);
		Files.write(Paths.get(pathToSave), formattedDfa.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
	}
	
	private String toFileFormat(DFA dfa) {
		StringBuilder sb = new StringBuilder();
		
		// Name
		sb.append(dfa.getName() + "=");
		
		// Definition
		sb.append("(");
		//   Alphabet
		sb.append("{");
		int symbolsSoFar = 0;
		int symbolsTotal = dfa.getAlphabet().size();
		for (Symbol symbol : dfa.getAlphabet()) {
			sb.append(symbol);
			symbolsSoFar++;
			if (symbolsSoFar < symbolsTotal) sb.append(","); 
		}
		sb.append("},");
		//   States
		sb.append("{");
		//     All states
		int statesSoFar = 0;
		int statesTotal = dfa.getStates().size();
		for (State state : dfa.getStates()) {
			sb.append(state);
			statesSoFar++;
			if (statesSoFar < statesTotal) sb.append(",");
		}
		//     Initial State
		sb.append("}," + dfa.getInitialState() + ",");
		//     Final States
		sb.append("{");
		int finalStatesSoFar = 0;
		int finalStatesTotal = dfa.getFinalStates().size();
		for (State finalState : dfa.getFinalStates()) {
			sb.append(finalState);
			finalStatesSoFar++;
			if (finalStatesSoFar < finalStatesTotal) sb.append(",");
		}
		sb.append("}");
		sb.append(")\n");
		
		// Transition function
		sb.append("Prog");
		sb.append("\n");
		for (Map.Entry<StateSymbolTuple, State> transition : dfa.getTransitionFunction().entrySet()) {
			State state = transition.getKey().getState();
			Symbol symbol = transition.getKey().getSymbol();
			sb.append("(" + state + "," + symbol + ")=" + transition.getValue() + "\n");
		}
		
		return sb.toString();
	}

}
