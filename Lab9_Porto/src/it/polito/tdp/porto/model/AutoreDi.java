package it.polito.tdp.porto.model;

public class AutoreDi {

	private int codicAutore;
	private long codiceArticolo;
	public AutoreDi(int codicAutore, long codiceArticolo) {
		super();
		this.codicAutore = codicAutore;
		this.codiceArticolo = codiceArticolo;
	}
	public int getCodicAutore() {
		return codicAutore;
	}
	public void setCodicAutore(int codicAutore) {
		this.codicAutore = codicAutore;
	}
	public long getCodiceArticolo() {
		return codiceArticolo;
	}
	public void setCodiceArticolo(int codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codicAutore;
		result = prime * result + (int) (codiceArticolo ^ (codiceArticolo >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AutoreDi other = (AutoreDi) obj;
		if (codicAutore != other.codicAutore)
			return false;
		if (codiceArticolo != other.codiceArticolo)
			return false;
		return true;
	}
	
	
}
