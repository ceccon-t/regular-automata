package ufrgs.inf.formais.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class DFA {
	
	private String name;
	
	private HashSet<String> alphabet;
	
	private HashSet<String> states;
	
	private String initialState;
	
	private HashSet<String> finalStates;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashSet<String> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(HashSet<String> alphabet) {
		this.alphabet = alphabet;
	}

	public HashSet<String> getStates() {
		return states;
	}

	public void setStates(HashSet<String> states) {
		this.states = states;
	}

	public String getInitialState() {
		return initialState;
	}

	public void setInitialState(String initialState) {
		this.initialState = initialState;
	}

	public HashSet<String> getFinalStates() {
		return finalStates;
	}

	public void setFinalStates(HashSet<String> finalStates) {
		this.finalStates = finalStates;
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
	

}