package de.oth.hsp.common.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Page2Controller {

    // References to elements of the FXML Layout of Page2
    @FXML
    private TableView<String> table; // Nettobedarfsmenge des Produkts k in
                                     // Periode t

    private RootLayoutController root;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page2Controller() {

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
