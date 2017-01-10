package it.polito.tdp.porto.model;

public class Articolo {

	private long codice;
	private int anno;
	private String titolo;
	public Articolo(long codice, int anno, String titolo) {
		super();
		this.codice = codice;
		this.anno = anno;
		this.titolo = titolo;
	}
	public long getCodice() {
		return codice;
	}
	public void setCodice(long codice) {
		this.codice = codice;
	}
	public int getAnno() {
		return anno;
	}
	public void setAnno(int anno) {
		this.anno = anno;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codice ^ (codice >>> 32));
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
		Articolo other = (Articolo) obj;
		if (codice != other.codice)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Articolo [codice=" + codice + ", anno=" + anno + ", titolo=" + titolo + "]";
	}
	
	

}
