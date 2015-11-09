package de.oth.hsp.common.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PaginationController {

	// References to elements of the FXML Layouts of all Paginaton-Pages
	@FXML
	private TextField T;
	@FXML
	private TextField K;
	// -> TODO: add all fields of the pageX.fxml files
	
    private RootLayoutController root;
    
    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public PaginationController() {

    }
    
    /**
	 * @return the t
	 */
	public TextField getT() {
		return T;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(TextField t) {
		T = t;
	}

	/**
	 * @return the k
	 */
	public TextField getK() {
		return K;
	}

	/**
	 * @param k the k to set
	 */
	public void setK(TextField k) {
		K = k;
	}

	/**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    		
    }
   
	/**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
    	root = rootLayoutController;
    }
    
}
