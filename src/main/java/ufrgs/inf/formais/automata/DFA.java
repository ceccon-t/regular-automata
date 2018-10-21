package ufrgs.inf.formais.automata;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import ufrgs.inf.formais.helper.Symbol;
import ufrgs.inf.formais.helper.Tuple;
import ufrgs.inf.formais.helper.Word;

public class DFA extends Automaton{
	
	protected String type = "Deterministic Finite Automaton";
	
	private HashMap<Tuple, String> transitionFunction;
	
	public DFA() {
		
	}
	
	public DFA(String name, 
			HashSet<Symbol> alphabet,
			HashSet<String> states, 
			String initialState, 
			HashSet<String> finalStates,
			HashMap<Tuple, String> transitionFunction) {
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

	public HashMap<Tuple, String> getTransitionFunction() {
		return transitionFunction;
	}

	public void setTransitionFunction(HashMap<Tuple, String> transitionFunction) {
		this.transitionFunction = transitionFunction;
	}
	
	public boolean recognize(List<String> word) {
		String state = initialState;
		
		int pos = 0;
		
		while (pos < word.size()) {
			Tuple transition = new Tuple(state, word.get(pos));
			
			if (!transitionFunction.containsKey(transition)) {
				return false;
			}
			
			state = transitionFunction.get(transition);
			
			pos++;
		}
		
		return (finalStates.contains(state)) ? true : false ;
	}
	
	public boolean recognize(Word word) {
		return this.recognize(word.getSequence());
	}
	
	public String stringifyTransitionFunction() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n- Transition function: \n");
		for (Map.Entry<Tuple, String> transition : transitionFunction.entrySet()) {
			Tuple key = transition.getKey();
			String destiny = transition.getValue();
			sb.append("( " + key.getLeft() + " , " + key.getRight() + " ) => " + destiny + "\n");
		}
		sb.append("\n-End of transition function-\n");
		
		return sb.toString();
	}
	

}