package it.polito.tdp.porto.controller;

import java.net.URL;
import java.util.ResourceBundle;
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
    private ComboBox<?> combo1;

    @FXML
    private ComboBox<?> combo2;

    @FXML
    private TextArea testoOutput;

    @FXML
    void doVizArticoli(ActionEvent event) {

    }

    @FXML
    void doVizCluster(ActionEvent event) {

    }

    @FXML
    void doVizCoautori(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert combo1 != null : "fx:id=\"combo1\" was not injected: check your FXML file 'Porto.fxml'.";
        assert combo2 != null : "fx:id=\"combo2\" was not injected: check your FXML file 'Porto.fxml'.";
        assert testoOutput != null : "fx:id=\"testoOutput\" was not injected: check your FXML file 'Porto.fxml'.";

    }
}

