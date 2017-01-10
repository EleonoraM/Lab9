package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Autore implements Comparable<Autore> {

	private int codice;
	private String cognome;
	private String nome;
	private Set<Autore> coautori;
	private Set<AutoreDi> coautoriDi;
	private List<AutoreDi> coas;

	public Autore(int codice, String cognome, String nome) {
		super();
		this.codice = codice;
		this.cognome = cognome;
		this.nome = nome;
		coautori=new HashSet<>();
		coautoriDi= new HashSet<>();
		coas= new ArrayList<>();
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codice;
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
		Autore other = (Autore) obj;
		if (codice != other.codice)
			return false;
		return true;
	}
	@Override
	public int compareTo(Autore arg0) {
		return this.cognome.compareTo(arg0.getCognome());
	}
	@Override
	public String toString() {
		return cognome + " " + nome ;
	}
	public Set<Autore> getCoautori() {
		return coautori;
	}
	public void setCoautori(Set<Autore> coautori) {
		this.coautori = coautori;
	}
	public void aggiungiCoautore(Autore autore) {

		coautori.add(autore);
	}
	public void aggiungiAutoreDi(AutoreDi b) {
		coautoriDi.add(b);
	}
	public Set<AutoreDi> getCoautoriDi() {
		return coautoriDi;
	}
	public void setCoautoriDi(Set<AutoreDi> coautoriDi) {
		coas=new LinkedList<>(coautoriDi);
		this.coautoriDi = coautoriDi;
	}
	public List<AutoreDi> getCoas() {
		return coas;
	}
	public void setCoas() {
		coas=new LinkedList<>(coautoriDi); 
	}
	
	
	

}
