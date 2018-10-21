package ufrgs.inf.formais.helper;

public class Symbol {
	
	String representation;
	
	public Symbol(String representation) {
		this.representation = representation;
	}

	public String getRepresentation() {
		return representation;
	}

	public void setRepresentation(String representation) {
		this.representation = representation;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o instanceof Symbol) ? this.representation.equals( ((Symbol)o).getRepresentation() ) : false;
	}
	
	@Override
	public int hashCode() {
		return this.representation.hashCode();
	}
	
	public String toString() {
		return this.representation;
	}

}
