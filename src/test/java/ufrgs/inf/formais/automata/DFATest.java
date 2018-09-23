package ufrgs.inf.formais.automata;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ufrgs.inf.formais.helper.Tuple;
import ufrgs.inf.formais.helper.Word;

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
		Word word = new Word(new String[] {"a", "b", "a", "b", "a", "b", "a"});
		
		assertTrue(automaton.recognize(word));
	}
	
	@Test
	public void doesNotRecognizeWordsThatDoNotEndWithA() {
		DFA automaton = dfaEndsWithA();
		Word word = new Word(new String[] {"a", "a", "b", "a", "b"});
		
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
		HashMap<Tuple, String> transitionFunction = new HashMap<Tuple, String>();
		transitionFunction.put(new Tuple("q0", "a"), "q1");
		transitionFunction.put(new Tuple("q0", "b"), "q0");
		transitionFunction.put(new Tuple("q1", "a"), "q1");
		transitionFunction.put(new Tuple("q1", "b"), "q0");
		
		return new DFA(name, alphabet, states, initialState, finalStates, transitionFunction);
	}
}