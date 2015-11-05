package de.oth.hsp.common.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Tab2Controller {

    // References for the FXML layout
    @FXML
    private Button btnAddRow;
    @FXML
    private Button btnRemoveRow;
    @FXML
    private Button btnRemoveAll;
    @FXML
    private Button btnLoad;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCalculate;

    private RootLayoutController root;
    
    public Tab2Controller() {

    }
    
    /**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
	root = rootLayoutController;
    }

    
}
