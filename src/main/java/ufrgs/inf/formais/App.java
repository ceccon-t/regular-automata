package ufrgs.inf.formais;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ufrgs.inf.formais.automata.DFA;
import ufrgs.inf.formais.automata.NFA;
import ufrgs.inf.formais.builders.AutomataConverter;
import ufrgs.inf.formais.helper.State;
import ufrgs.inf.formais.helper.StateSymbolTuple;
import ufrgs.inf.formais.helper.Symbol;
import ufrgs.inf.formais.helper.Word;
import ufrgs.inf.formais.storage.NFAStorage;

public class App  {
	
	private static DFA automaton;
	
    public static void main( String[] args ) {
        //System.out.println( "Hello World!" );
    	
    	automaton = dfaEndsWithA();
    	
    	JFrame mainFrame = new JFrame();
    	JPanel mainPanel = new JPanel();
    	
    	JTextField userInputField = new JTextField(20);
    	
    	JButton addWordBtn = new JButton("Decide");
    	
    	JLabel decisionLabel = new JLabel();
    	decisionLabel.setText("Waiting...");
    	decisionLabel.setText(automaton.recognize(new Word("ba", ""))? "Yes":"No");
    	
    	addWordBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String userInput = userInputField.getText();
				decisionLabel.setText(automaton.recognize(new Word(userInput, "")) ? "YES" : "NO" );
			}
    		
    	});
    	mainPanel.add(userInputField);
    	mainPanel.add(addWordBtn);
    	mainPanel.add(decisionLabel);
    	
    	// Right panel
    	JPanel automatonPanel = new JPanel();
    	JLabel automatonNameLabel = new JLabel();
    	automatonNameLabel.setText(automaton.getName());
    	automatonPanel.add(automatonNameLabel);
    	
    	JFileChooser fileChooser = new JFileChooser();
    	JButton fileChooserBtn = new JButton("Load automaton");
    	fileChooserBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					NFAStorage nfas = new NFAStorage();
					try {
						NFA nfa = nfas.load(selectedFile);
						automaton = AutomataConverter.nfaToDfa(nfa); 
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String automatonName = automaton.getName();
					automatonNameLabel.setText(getAutomatonNameDisplay(automatonName));
					automatonNameLabel.setToolTipText(automatonName);
				}
			}
    		
    	});
    	
    	automatonPanel.add(fileChooserBtn);
    	
    	mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
    	mainFrame.getContentPane().add(BorderLayout.SOUTH, automatonPanel);
    	
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame.setSize(500, 300); 
    	mainFrame.setVisible(true);
    	mainFrame.setTitle("Regular Automata");
    	
    }
    
    private static String getAutomatonNameDisplay(String name) {
    	return (name.length() < 10 ) ? name : name.substring(0, 6) + "..." ;
    }
    
	public static DFA dfaEndsWithA() {
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
