package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.Multigraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	private List<Articolo> articoli;

	private List<Autore> autori;
	private Map<Integer,Autore> autoriMap;
	private Map<Long,Articolo> articoliMap;

	private Graph <Autore,Edge> graph ;
	public Model (){
		PortoDAO dao = new PortoDAO();
		autori=dao.getAllAutori();
		autoriMap= new HashMap<>();
		articoliMap= new HashMap<>();

		for(Autore a:autori){
			autoriMap.put(a.getCodice(), a);
		}
		
		articoli=dao.getAllArticoli();
		for(Articolo a:articoli){
			articoliMap.put(a.getCodice(), a);
		}
		for(Autore a :autori){
			for(AutoreDi b: dao.getAllCoautori(a)){
				if(b.getCodicAutore()!=a.getCodice()){
					a.aggiungiCoautore(autoriMap.get(b.getCodicAutore()));
					a.aggiungiAutoreDi(b);}
			}
			a.setCoas();
		}
	componentiConnesse= new HashSet<>();
	}
	public List<Autore> getAutori() {
			Collections.sort(autori);
		return autori;
	}
	public Set<List<Autore>> getCluster() {

		creaGrafo();
		
		List<Autore> autoriConnessiFinoAdOra= new ArrayList<>();
		int numeroComponenti=0;
		int passo=0;
		List<Autore>pesca = new ArrayList<>(autori);
	
		 componentiConnesse(autoriConnessiFinoAdOra,  numeroComponenti,  passo,pesca);
			
		 return componentiConnesse;
		
		
	}
	Set<List<Autore>> componentiConnesse;
	
	public Set<Autore> getAutoriNeighbours(Autore autore) {
		return new LinkedHashSet<Autore>(Graphs.neighborListOf(graph, autore));
	}
	/**
	 * 
	 * @param autoriConnessiFinoAdOra sequenza di Autori connessi 
	 * @param numeroComponenti dimensione del set che contiene tutte le sequenze di autori
	 * @param passo il passo corrisponde ad un singolo autore 
	 * @param pesca lista di autori dalla quale si può pescare gli autori non ancora "usati"
	 */
	
	private void componentiConnesse(List<Autore> autoriConnessiFinoAdOra, int numeroComponenti, int passo,List<Autore> pesca) {

		// se l'autore di indice "passo" non ha alcun vicino nel grafo, lo aggiungo alla combo autoriConnessi 
		//lo tolgo dagli autori "pescabili" e incremento il numero di componenti connesse totali
		//aggiungo la mia combo di autori alle componenti connesse totali, poi richiamo ricorsione per un generico passo+1
		
		if(getAutoriNeighbours(pesca.get(passo)).isEmpty()){
			
			List <Autore> nuova= new ArrayList<>();
			Autore g= new Autore(0,null,null);
			autoriConnessiFinoAdOra.add(pesca.get(passo));
			pesca.remove(passo);
			numeroComponenti+=1;
			for(Autore a :autoriConnessiFinoAdOra){
				g=a;
				nuova.add(g);
			}
			componentiConnesse.add(nuova);
			
			componentiConnesse(new ArrayList<>(),numeroComponenti,passo+1,pesca);
			}
			//terminazione :non ci sono piu autori da pescare
		if(passo>=pesca.size()){
			return;
		} 
		//se invece la lista vicini dell'autore "passo" e piena ,

        //aggiungo il vicino e alla mia combinazione,memorizzo la sua posizione e poi lo rimuovo dai pescabili
        // richiamo ricorsione per il passo=poszione del vicino 
		for(Autore e:getAutoriNeighbours(pesca.get(passo))){

			 if(!autoriConnessiFinoAdOra.contains(e)&&pesca.contains(e)){
			 autoriConnessiFinoAdOra.add(e);
			int passo1=pesca.indexOf(e);
			pesca.remove(passo1);
			componentiConnesse( autoriConnessiFinoAdOra, numeroComponenti, passo1,pesca);
			autoriConnessiFinoAdOra.remove(e);
			
			}


		 }
		 for(List<Autore> list: componentiConnesse){
			 
			 System.out.println(list.toString());
			 }
	}
	private void creaGrafo() {

		graph=new Multigraph <Autore,Edge>(Edge.class);
		Graphs.addAllVertices(graph, autori);
		for(Autore a : graph.vertexSet()){
			for(Autore b:graph.vertexSet()){
				if (a.getCoautori().contains(b))
				{
					for(int i =0;i<a.getCoautoriDi().size();i++){
						if(a.getCoas().get(i).getCodicAutore()==b.getCodice())								
							graph.addEdge(a, b, new Edge(articoliMap.get(a.getCoas().get(i).getCodiceArticolo())));
					}				}
			}
		}
	}
	
	public List<Edge> getArticoli(Autore a, Autore b) {
		creaGrafo();
		if ( a==null|| b==null)
			return null;

			DijkstraShortestPath<Autore, Edge> djstra= new DijkstraShortestPath<Autore, Edge>(graph	,a,b);
			GraphPath <Autore,Edge> path = djstra.getPath();

			if ( path ==null)
			return null;

			
			return path.getEdgeList();
	}

}
