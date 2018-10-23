package ufrgs.inf.formais;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ufrgs.inf.formais.automata.DFA;
import ufrgs.inf.formais.helper.State;
import ufrgs.inf.formais.helper.StateSymbolTuple;
import ufrgs.inf.formais.helper.Symbol;
import ufrgs.inf.formais.storage.DFAStorage;

public class AppTest {
	
	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void dfaStorageFormat() {
		DFAStorage dfaStorage = new DFAStorage();
		/*
		dfaStorage.save(dfaEndsWithA());
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	
	private DFA dfaEndsWithA() {
		String name = "Ends With A";
		HashSet<Symbol> alphabet = new HashSet<Symbol>();
		Symbol a = new Symbol("a");
		Symbol b = new Symbol("b");
		alphabet.add(a);
		alphabet.add(b);
		HashSet<State> states = new HashSet<State>();
		State q0 = new State("q0");
		State q1 = new State("q1");
		states.add(q0);
		states.add(q1);
		State initialState = q0;
		HashSet<State> finalStates = new HashSet<State>();
		finalStates.add(q1);
		HashMap<StateSymbolTuple, State> transitionFunction = new HashMap<StateSymbolTuple, State>();
		transitionFunction.put(new StateSymbolTuple(q0, a), q1);
		transitionFunction.put(new StateSymbolTuple(q0, b), q0);
		transitionFunction.put(new StateSymbolTuple(q1, a), q1);
		transitionFunction.put(new StateSymbolTuple(q1, b), q0);
		
		return new DFA(name, alphabet, states, initialState, finalStates, transitionFunction);
	}
}
