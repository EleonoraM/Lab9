package it.polito.tdp.porto.model;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Articolo articolo;
	public Edge(Articolo articolo) {
		super();
		this.articolo = articolo;
	}
	public Articolo getArticolo() {
		return articolo;
	}
	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}
	@Override
	public String toString() {
		return "Edge [articolo=" + articolo + "]";
	}
	
	
}
