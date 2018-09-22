package ufrgs.inf.formais.helper;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class Tuple {
	
	private ImmutablePair<String, String> pair;
	
	public Tuple(String left, String right) {
		this.pair = new ImmutablePair<String, String>(left, right);
	}
	
	public ImmutablePair<String, String> getPair() {
		return pair;
	}

	public String getLeft() {
		return this.pair.getLeft();
	}
	
	public String getRight() {
		return this.pair.getRight();
	}
	
	@Override
	public boolean equals(Object o) {
		/*
		if (o instanceof Tuple) {
			return this.pair.equals( ((Tuple) o).getPair() );
		} else {
			return false;
		}
		*/
		
		return (o instanceof Tuple) ? this.pair.equals( ( (Tuple) o).getPair() ) : false ;
	}
	
	@Override
	public int hashCode() {
		return this.pair.hashCode();
	}

}
