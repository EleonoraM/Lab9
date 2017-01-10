package it.polito.tdp.porto.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.porto.model.Articolo;
import it.polito.tdp.porto.model.Autore;
import it.polito.tdp.porto.model.Edge;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Autore> combo1;

    @FXML
    private ComboBox<Autore> combo2;

    @FXML
    private TextArea testoOutput;

	private Model model;

    @FXML
    void doVizArticoli(ActionEvent event) {
    	testoOutput.clear();
    	Autore a= combo1.getValue();
    	Autore b= combo2.getValue();

    	if(a==null||b==null){
    		testoOutput.appendText("seleziona due autori");
    		return;
    	}
    
    	List<Edge> articoli =model.getArticoli(a,b);
    	if(articoli==null){
    		testoOutput.appendText("i due autori non hanno alcun articolo comune");
    		return;
    	}
    	if(a.getCoautori().contains(b)){
    		testoOutput.appendText("i due autori sono coautori");
    		return;

    	}
    	testoOutput.appendText("gli articoli sono:\n");

    	for(Edge ed : articoli)
    		testoOutput.appendText(ed.toString()+"\n");
    }

    @FXML
    void doVizCluster(ActionEvent event) {
    	testoOutput.clear();
    	Set<List<Autore>> comp=model.getCluster();
		testoOutput.appendText(String.format("Trovate %d componenti connesse\n",comp.size() ));

    	for(List<Autore> a: model.getCluster()){
    		testoOutput.appendText(a.toString()+"\n");
    	}
    }

    @FXML
    void doVizCoautori(ActionEvent event) {

    	testoOutput.clear();
    	Autore a= combo1.getValue();
    	if(a==null){
    		testoOutput.appendText("seleziona un autore");
    	}
		testoOutput.appendText("Coautori di "+a.toString()+" : \n");

    	for(Autore b:a.getCoautori()){
    		testoOutput.appendText(b.toString()+"\n");
    	}
    }

    @FXML
    void initialize() {
        assert combo1 != null : "fx:id=\"combo1\" was not injected: check your FXML file 'Porto.fxml'.";
        assert combo2 != null : "fx:id=\"combo2\" was not injected: check your FXML file 'Porto.fxml'.";
        assert testoOutput != null : "fx:id=\"testoOutput\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(Model model) {

		this.model=model;
		combo1.getItems().addAll(model.getAutori());
		combo2.getItems().addAll(model.getAutori());

	}
}

