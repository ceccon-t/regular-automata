package ufrgs.inf.formais.builders;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import ufrgs.inf.formais.automata.DFA;
import ufrgs.inf.formais.helper.State;
import ufrgs.inf.formais.helper.StateSymbolTuple;
import ufrgs.inf.formais.helper.Symbol;

public class DFABuilder {
	
	private String name;
	
	private HashSet<Symbol> alphabet;
	
	private HashSet<State> states;
	
	private State initialState;
	
	private HashSet<State> finalStates;
	
	private HashMap<StateSymbolTuple, State> transitionFunction;
	
	public DFABuilder() {
		this.alphabet = new HashSet<Symbol>();
		this.states = new HashSet<State>();
		this.finalStates = new HashSet<State>();
		this.transitionFunction = new HashMap<StateSymbolTuple, State>();
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addSymbolToAlphabetFromString(String symbol) {
		this.alphabet.add(new Symbol(symbol));
	}
	
	public void addSymbolToAlphabet(Symbol symbol) {
		this.alphabet.add(symbol);
	}
	
	public void addSymbolListToAlphabetFromString(List<String> symbols) {
		for (String symbol : symbols) {
			this.alphabet.add(new Symbol(symbol));
		}
	}
	
	public void addSymbolListToAlphabet(List<Symbol> symbols) {
		for (Symbol symbol : symbols) {
			this.alphabet.add(symbol);
		}
	}
	
	public void addStateFromString(String state) {
		this.states.add(new State(state));
	}
	
	public void addState(State state) {
		this.states.add(state);
	}
	
	public void addStateListFromString(List<String> states) {
		for (String state : states) {
			this.states.add(new State(state));
		}
	}
	
	public void addStateList(List<State> states) {
		for (State state : states) {
			this.states.add(state);
		}
	}
	
	public void setInitialStateFromString(String initialStateName) {
		State initialState = new State(initialStateName);
		if (!this.states.contains(initialState)) {
			// TODO: implement exceptions 
			//throw new Exception("Tried to add a non-existing state as initial state during DFA creation");
		} else {
			this.initialState = initialState;
		}
	}
	
	public void setInitialState(State initialState) {
		if (!this.states.contains(initialState)) {
			// TODO: implement exceptions 
			//throw new Exception("Tried to add a non-existing state as initial state during DFA creation");
		} else {
			this.initialState = initialState;
		}
	}
	
	public void addFinalStateFromString(String finalStateString) {
		State finalState = new State(finalStateString);
		if (!this.states.contains(finalState)) {
			// TODO: implement exceptions 
			//throw new Exception("Tried to add a non-existing state as initial state during DFA creation");
		} else {
			this.finalStates.add(finalState);
		}
	}
	
	public void addFinalState(State finalState) {
		if (!this.states.contains(finalState)) {
			// TODO: implement exceptions 
			//throw new Exception("Tried to add a non-existing state as initial state during DFA creation");
		} else {
			this.finalStates.add(finalState);
		}
	}
	
	public void addFinalStateListFromString(List<String> finalStateStrings) {
		for (String finalStateString : finalStateStrings) {
			State finalState = new State(finalStateString);
			if (!this.states.contains(finalState)) {
				// TODO: implement exceptions 
				//throw new Exception("Tried to add a non-existing state as initial state during DFA creation");
			} else {
				this.finalStates.add(finalState);
			}
		}
	}
	
	public void addFinalStateList(List<State> finalStates) {
		for (State finalState : finalStates) {
			if (!this.states.contains(finalState)) {
				// TODO: implement exceptions 
				//throw new Exception("Tried to add a non-existing state as initial state during DFA creation");
			} else {
				this.finalStates.add(finalState);
			}
		}
	}
	
	public void addTransitionFromStrings(String stateString, String symbolString, String destinyStateString) {
		State state = new State(stateString);
		Symbol symbol = new Symbol(symbolString);
		State destinyState = new State(destinyStateString);
		if (!this.alphabet.contains(symbol) || !this.states.contains(state) || !this.states.contains(destinyState)) {
			// TODO: implement exceptions 
			//throw new Exception("Tried to add a non-existing state as initial state during DFA creation");
		} else {
			StateSymbolTuple stateSymbol = new StateSymbolTuple(state, symbol);
			this.transitionFunction.put(stateSymbol, destinyState);
		}
	}
	
	public void addTransition(State state, Symbol symbol, State destinyState) {
		StateSymbolTuple stateSymbol = new StateSymbolTuple(state, symbol);
		if (!this.alphabet.contains(symbol) || !this.states.contains(state) 
				|| !this.states.contains(destinyState) || this.transitionFunction.containsKey(stateSymbol)) {
			// TODO: implement exceptions 
			//throw new Exception("Tried to add a non-existing state as initial state during DFA creation");
		} else {
			this.transitionFunction.put(stateSymbol, destinyState);
		}
	}
	
	public DFA build() {
		return new DFA(name, alphabet, states, initialState, finalStates, transitionFunction);
	}
	

}
