package ufrgs.inf.formais.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class DFA extends Automaton{
	
	protected String type = "Deterministic Finite Automaton";
	
	private HashMap<ImmutablePair<String, String>, String> transitionFunction;
	
	public DFA() {
		
	}
	
	public DFA(String name, 
			HashSet<String> alphabet,
			HashSet<String> states, 
			String initialState, 
			HashSet<String> finalStates,
			HashMap<ImmutablePair<String, String>, String> transitionFunction) {
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

	public HashMap<ImmutablePair<String, String>, String> getTransitionFunction() {
		return transitionFunction;
	}

	public void setTransitionFunction(HashMap<ImmutablePair<String, String>, String> transitionFunction) {
		this.transitionFunction = transitionFunction;
	}
	
	public boolean recognize(List<String> word) {
		String state = initialState;
		
		int pos = 0;
		
		while (pos < word.size()) {
			ImmutablePair<String, String> transition = new ImmutablePair<String, String>(state, word.get(pos));
			
			if (!transitionFunction.containsKey(transition)) {
				return false;
			}
			
			state = transitionFunction.get(transition);
			
			pos++;
		}
		
		return (finalStates.contains(state)) ? true : false ;
	}
	
	public String stringifyTransitionFunction() {
		StringBuilder sb = new StringBuilder();
		
		return sb.toString();
	}
	

}