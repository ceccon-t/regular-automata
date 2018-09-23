package ufrgs.inf.formais.helper;

import java.util.ArrayList;
import java.util.List;

public class Word {
	
	private List<String> sequence;
	
	public Word(String[] symbols) {
		sequence = new ArrayList<String>(symbols.length);
		for (String symbol : symbols) {
			sequence.add(symbol);
		}
	}

	public List<String> getSequence() {
		return sequence;
	}
	
	

}
