package ufrgs.inf.formais.helper;

import java.util.ArrayList;
import java.util.List;

public class Word {
	
	private List<Symbol> sequence;
	
	public Word(Symbol[] symbols) {
		sequence = new ArrayList<Symbol>(symbols.length);
		for (Symbol symbol : symbols) {
			sequence.add(symbol);
		}
	}

	public List<Symbol> getSequence() {
		return sequence;
	}
	
	

}
