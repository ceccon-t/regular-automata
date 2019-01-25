package ufrgs.inf.formais.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import ufrgs.inf.formais.builders.AutomataConverter;
import ufrgs.inf.formais.helper.State;
import ufrgs.inf.formais.helper.StateSymbolTuple;
import ufrgs.inf.formais.helper.Symbol;
import ufrgs.inf.formais.helper.Word;

public class NFA extends Automaton {

	protected String type = "Non-Deterministic Finite Automaton";
	
	private HashMap<StateSymbolTuple, HashSet<State>> transitionFunction;
	
	public NFA() {
		
	}
	
	public NFA(String name,
				HashSet<Symbol> alphabet,
				HashSet<State> states,
				State initialState,
				HashSet<State> finalStates,
				HashMap<StateSymbolTuple, HashSet<State>> transitionFunction) {
		this.name = name;
		this.alphabet = alphabet;
		this.states = states;
		this.initialState = initialState;
		this.finalStates = finalStates;
		this.transitionFunction = transitionFunction;
	}
	
	public String getType() {
		return type;
	}

	public HashMap<StateSymbolTuple, HashSet<State>> getTransitionFunction() {
		return transitionFunction;
	}

	public void setTransitionFunction(HashMap<StateSymbolTuple, HashSet<State>> transitionFunction) {
		this.transitionFunction = transitionFunction;
	}
	
	public boolean isDeterministic() {
		for (Map.Entry<StateSymbolTuple, HashSet<State>> transition : transitionFunction.entrySet()) {
			if (transition.getValue().size() > 1) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean recognize(Word word) {
		return AutomataConverter.nfaToDfa(this).recognize(word);
	}

	@Override
	public String stringifyTransitionFunction() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n- Transition function: \n");
		for (Map.Entry<StateSymbolTuple, HashSet<State>> transition : transitionFunction.entrySet()) {
			StateSymbolTuple key = transition.getKey();
			State state = key.getState();
			Symbol symbol = key.getSymbol();
			for (State destiny : transition.getValue()) {
				sb.append("( " + state + " , " + symbol + " ) => " + destiny + "\n");
			}
		}
		sb.append("\n-End of transition function-\n");
		
		return sb.toString();
	}
	
	

}
