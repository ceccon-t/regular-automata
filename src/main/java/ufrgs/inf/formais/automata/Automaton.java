package ufrgs.inf.formais.automata;

import java.util.HashSet;

import ufrgs.inf.formais.helper.Symbol;


public abstract class Automaton {

	protected String type = "Automaton";
	
	protected String name;
	
	protected HashSet<Symbol> alphabet;
	
	protected HashSet<String> states;
	
	protected String initialState;
	
	protected HashSet<String> finalStates;

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashSet<Symbol> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(HashSet<Symbol> alphabet) {
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
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n===\n");
		sb.append("AUTOMATON " + this.hashCode() + ":\n");
		sb.append("Name: " + this.getName() + "\n");
		sb.append("Type: " + this.getType() + "\n");
		
		// Alphabet
		sb.append("Alphabet:");
		for (Symbol symbol : this.getAlphabet()) {
			sb.append(" '" + symbol + "'");
		} 
		sb.append("\n");
		
		// States
		sb.append("States:");
		for (String state : this.getStates()) {
			sb.append(" '" + state + "'");
		}
		sb.append("\n");
		
		sb.append("Initial state: " + this.getInitialState() + "\n");
		
		// Final states
		sb.append("Final states:");
		for (String finalState: this.getFinalStates()) {
			sb.append(" '" + finalState + "'");
		}
		sb.append("\n");
		
		sb.append(this.stringifyTransitionFunction());
		
		sb.append("\n===\n");
		
		return sb.toString();
	}
	
	public abstract String stringifyTransitionFunction();
	
}
