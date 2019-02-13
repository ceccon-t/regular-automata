package ufrgs.inf.formais.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import ufrgs.inf.formais.builders.AutomataConverter;
import ufrgs.inf.formais.helper.State;
import ufrgs.inf.formais.helper.StateSymbolTuple;
import ufrgs.inf.formais.helper.Symbol;
import ufrgs.inf.formais.helper.Word;

public class NFAe extends NFA {
	
	protected String type = "Non-Deterministic Finite Automaton with epsilon transitions";
	
	private HashMap<State, HashSet<State>> epsilonTransitions;
	
	public NFAe() {
		
	}
	
	public NFAe(String name,
				HashSet<Symbol> alphabet,
				HashSet<State> states,
				State initialState,
				HashSet<State> finalStates,
				HashMap<StateSymbolTuple, HashSet<State>> transitionFunction,
				HashMap<State, HashSet<State>> epsilonTransitions) {
		this.name = name;
		this.alphabet = alphabet;
		this.states = states;
		this.initialState = initialState;
		this.finalStates = finalStates;
		this.transitionFunction = transitionFunction;
		this.epsilonTransitions = epsilonTransitions;
	}
	
	public String getType() {
		return type;
	}
	
	public HashMap<State, HashSet<State>> getEpsilonTransitions() {
		return this.epsilonTransitions;
	}
	
	public void setEpsilonTransitions(HashMap<State, HashSet<State>> epsilonTransitions) {
		this.epsilonTransitions = epsilonTransitions;
	}

	/*
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
	*/

	@Override
	public String stringifyTransitionFunction() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n- Transition function: \n");
		// Normal transitions
		for (Map.Entry<StateSymbolTuple, HashSet<State>> transition : transitionFunction.entrySet()) {
			StateSymbolTuple key = transition.getKey();
			State state = key.getState();
			Symbol symbol = key.getSymbol();
			for (State destiny : transition.getValue()) {
				sb.append("( " + state + " , " + symbol + " ) => " + destiny + "\n");
			}
		}
		// Epsilon transitions
		for (Map.Entry<State, HashSet<State>> epsilonTransition : epsilonTransitions.entrySet()) {
			State origin = epsilonTransition.getKey();
			for (State destiny : epsilonTransition.getValue()) {
				sb.append("( " + origin + " , ) => " + destiny + "\n");
			}
		}
		sb.append("\n-End of transition function-\n");
		
		return sb.toString();
	}
	
	

}
