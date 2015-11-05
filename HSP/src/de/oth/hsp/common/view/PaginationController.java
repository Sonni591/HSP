package de.oth.hsp.common.view;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class PaginationController {

	@FXML
	private TextField T;
	@FXML
	private TextField K;
	
    private RootLayoutController root;
    
    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public PaginationController() {

    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    		

    		System.out.println("pagination controller");
    		
    		
//	    		// Handle TextField text changes.
//	    		T.textProperty().addListener((observable, oldValue, newValue) -> {
//	    		    System.out.println("TextField Text Changed (newValue: " + newValue + ")");
//	    		});
    		
    	
    }
   
	/**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
    	root = rootLayoutController;
    }


    
}
