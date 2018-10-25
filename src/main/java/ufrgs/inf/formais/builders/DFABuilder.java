package ufrgs.inf.formais.builders;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import ufrgs.inf.formais.automata.DFA;
import ufrgs.inf.formais.helper.State;
import ufrgs.inf.formais.helper.StateSymbolTuple;
import ufrgs.inf.formais.helper.Symbol;

public class DFABuilder extends AutomatonBuilder {
	
	private HashMap<StateSymbolTuple, State> transitionFunction;
	
	public DFABuilder() {
		this.alphabet = new HashSet<Symbol>();
		this.states = new HashSet<State>();
		this.finalStates = new HashSet<State>();
		this.transitionFunction = new HashMap<StateSymbolTuple, State>();
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
