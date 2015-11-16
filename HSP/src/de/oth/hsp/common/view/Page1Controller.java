package de.oth.hsp.common.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Page1Controller {

    // References to elements of the FXML Layout of Page1
    @FXML
    private TextField T; // Länge des Planungszeitraums
    @FXML
    private TextField K; // Anzahl an Produkten
    @FXML
    private TextField J; // Anzahl der Stationen
    @FXML
    private TextField M; // Große Zahl
    @FXML
    private TextField C; // Verfügbare Kapaziät der Ressource

    private RootLayoutController root;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page1Controller() {

    }

    /**
     * @return the t
     */
    public TextField getT() {
        return T;
    }

    /**
     * @param t
     *            the t to set
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
     * @param k
     *            the k to set
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
