package ufrgs.inf.formais.automata;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class DFATest {
	
	@Before
	public void setup() {
		
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void recognizeWordsThatEndWithA() {
		DFA automaton = dfaEndsWithA();
		List<String> word = new ArrayList<String>();
		word.add("a");
		word.add("b");
		word.add("a");
		word.add("b");
		word.add("a");
		word.add("b");
		word.add("a");
		
		assertTrue(automaton.recognize(word));
	}
	
	@Test
	public void doesNotRecognizeWordsThatDoNotEndWithA() {
		DFA automaton = dfaEndsWithA();
		List<String> word = new ArrayList<String>();
		word.add("a");
		word.add("a");
		word.add("b");
		word.add("a");
		word.add("b");
		
		assertFalse(automaton.recognize(word));
	}
	
	private DFA dfaEndsWithA() {
		String name = "Ends With A";
		HashSet<String> alphabet = new HashSet<String>();
		alphabet.add("a");
		alphabet.add("b");
		HashSet<String> states = new HashSet<String>();
		states.add("q0");
		states.add("q1");
		String initialState = "q0";
		HashSet<String> finalStates = new HashSet<String>();
		finalStates.add("q1");
		HashMap<ImmutablePair<String, String>, String> transitionFunction = new HashMap<ImmutablePair<String, String>, String>();
		transitionFunction.put(new ImmutablePair("q0", "a"), "q1");
		transitionFunction.put(new ImmutablePair("q0", "b"), "q0");
		transitionFunction.put(new ImmutablePair("q1", "a"), "q1");
		transitionFunction.put(new ImmutablePair("q1", "b"), "q0");
		
		return new DFA(name, alphabet, states, initialState, finalStates, transitionFunction);
	}
}