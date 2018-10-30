package ufrgs.inf.formais.builders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import ufrgs.inf.formais.automata.DFA;
import ufrgs.inf.formais.automata.NFA;
import ufrgs.inf.formais.helper.CompositeState;
import ufrgs.inf.formais.helper.State;
import ufrgs.inf.formais.helper.StateSymbolTuple;
import ufrgs.inf.formais.helper.Symbol;

public class AutomataConverter {
	
	public static DFA nfaToDfa(NFA nfa)  {
		
		if (nfa.isDeterministic()) return convertDeterministicNfa(nfa);
		
		DFABuilder builder = new DFABuilder();
		builder.setName(nfa.getName() + " | Converted to DFA");
		HashSet<Symbol> alphabet = nfa.getAlphabet(); 
		builder.setAlphabet(alphabet);
		
		HashMap<StateSymbolTuple, HashSet<State>> oldTransitionFunction = nfa.getTransitionFunction();
		
		HashSet<State> newStates = new HashSet<State>();
		HashSet<State> newFinalStates = new HashSet<State>();
		
		// Maps from the set of states of nfa to the corresponding state to be put in dfa
		HashMap<CompositeState, State> collapsedStates = new HashMap<CompositeState, State>();
		int countCollapsed = 0;
		
		// Queue to control traversal over nfa transition function
		LinkedList<CompositeState> statesToVisit = new LinkedList<CompositeState>();
		
		// Transition function of final dfa
		HashMap<StateSymbolTuple, State> newTransitionFunction = new HashMap<StateSymbolTuple, State>();
		
		// Sets up traversal
		CompositeState newInitialState = new CompositeState(new HashSet<State>(Arrays.asList(nfa.getInitialState())));
		collapsedStates.put(newInitialState, newInitialState.collapse(countCollapsed++));
		newStates.add(collapsedStates.get(newInitialState));
		
		statesToVisit.add(newInitialState);
		
		// breadth-first search, seeing transition function as a graph, starting from initial state
		// and checking all possible destination states from current context, generating the properties
		// to be put on final dfa along the way
		while (!statesToVisit.isEmpty()) {
			CompositeState current = statesToVisit.removeFirst();
			for (Symbol symbol : alphabet) {
				HashSet<State> allReachable = new HashSet<State>();
				for (State state : current.getStates()) {
					StateSymbolTuple tuple = new StateSymbolTuple(state, symbol);
					if (oldTransitionFunction.containsKey(tuple)) {
						allReachable.addAll(oldTransitionFunction.get(tuple));
					}
				}
				if (allReachable.size() > 0) {
					CompositeState newComposite = new CompositeState(allReachable);
					if (!collapsedStates.containsKey(newComposite)) { // first time found, should be added everywhere
						State collapsed = newComposite.collapse(countCollapsed++);
						collapsedStates.put(newComposite, collapsed);
						newStates.add(collapsed);
						if (compositeStateContainsFinalState(newComposite, nfa)) newFinalStates.add(collapsed);
						statesToVisit.addLast(newComposite); // adds to the end of the queue only if not found yet
					}
					newTransitionFunction.put(new StateSymbolTuple(collapsedStates.get(current), symbol), collapsedStates.get(newComposite)); 
				}
			}
		}
		
		// traversal over, properties already set
		builder.setStates(newStates);
		builder.setInitialState(collapsedStates.get(newInitialState));
		builder.setFinalStates(newFinalStates);
		
		// goes through builder method to add transition just to double check that no invalid
		// transition has been generated during the creation of new transition function
		for (Map.Entry<StateSymbolTuple, State> transition : newTransitionFunction.entrySet()) {
			StateSymbolTuple tuple = transition.getKey();
			State origin = tuple.getState();
			Symbol symbol = tuple.getSymbol();
			State destiny = transition.getValue();
			builder.addTransition(origin, symbol, destiny);
		}
		
		return builder.build();
		
	}
	
	private static DFA convertDeterministicNfa(NFA nfa) {
		DFABuilder builder = new DFABuilder();
		builder.setName(nfa.getName() + " | Converted to DFA");
		builder.setAlphabet(nfa.getAlphabet());
		builder.setStates(nfa.getStates());
		builder.setInitialState(nfa.getInitialState());
		builder.setFinalStates(nfa.getFinalStates());
		
		for (Map.Entry<StateSymbolTuple, HashSet<State>> transition : nfa.getTransitionFunction().entrySet()) {
			StateSymbolTuple computation = transition.getKey();
			State destiny = transition.getValue().stream().collect(Collectors.toList()).get(0);
			builder.addTransition(computation.getState(), computation.getSymbol(), destiny);
		}
		
		return builder.build();
	}
	
	private static Boolean compositeStateContainsFinalState(CompositeState cs, NFA nfa) {
		Boolean containsFinalState = false;
		for (State state : cs.getStates()) {
			if (nfa.getFinalStates().contains(state)) {
				containsFinalState = true;
			}
		}
		return containsFinalState;
	}

}
