package it.polito.tdp.poweroutages;

import java.net.URL;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {

	private Model model;
	List<Nerc> nerc = new ArrayList<Nerc>();
	int anni;
	int ore;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Nerc> bxNerc;

    @FXML
    private TextField txtAnni;

    @FXML
    private TextField txtOre;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcola(ActionEvent event) {
    	
    	txtResult.clear();
    	Nerc nercScelto = bxNerc.getValue();
    	try {
    		anni = Integer.parseInt(txtAnni.getText());
    		ore = Integer.parseInt(txtOre.getText());
    	}catch(NumberFormatException e) {
    		txtResult.setText("Inserire numeri interi!");
    		return;
    	}
    	
    	if(nercScelto == null) {
    		txtResult.setText("Selezionare NERC");
    	}
    	
    	txtResult.setText(model.getRisultato(nercScelto, anni, ore));
    }

    @FXML
    void initialize() {
        assert bxNerc != null : "fx:id=\"bxNerc\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtAnni != null : "fx:id=\"txtAnni\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtOre != null : "fx:id=\"txtOre\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        
    }

    public void setModel(Model model) {
    	this.model= model;
        nerc = model.getNercList();
        bxNerc.getItems().addAll(nerc);
    	}
}