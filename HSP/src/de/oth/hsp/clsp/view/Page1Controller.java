package de.oth.hsp.clsp.view;

import de.oth.hsp.clsp.model.ClspDatFile;
import de.oth.hsp.common.view.IPageController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class Page1Controller implements IPageController {

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

    private ClspDatFile clspModel;

    private PaginationController paginationController;
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
        clspModel = root.getClspModel();
    }

    /**
     * @return the paginationController
     */
    public PaginationController getPaginationController() {
        return paginationController;
    }

    /**
     * @param paginationController
     *            the paginationController to set
     */
    public void setPaginationController(PaginationController paginationController) {
        this.paginationController = paginationController;
    }

    @Override
    public void outEvent() {
        try {
            if (Integer.valueOf(K.getText()) != 0 && Integer.valueOf(K.getText()) != 0
                    && Integer.valueOf(T.getText()) != 0 && Integer.valueOf(M.getText()) != 0) {
                clspModel.setK(Integer.valueOf(K.getText()));
                clspModel.setT(Integer.valueOf(T.getText()));
                clspModel.setJ(Integer.valueOf(J.getText()));
                clspModel.setM(Integer.valueOf(M.getText()));
            } else {
                throw new Exception();
            }
            clspModel.ensureConstraints();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Fehlende Werte");
            alert.setHeaderText("Werte nicht korrekt");
            alert.setContentText("Es dürfen keine Felder leer oder mit 0 belegt sein!");

            alert.showAndWait();
            root.getTab1Controller().getPagination().setCurrentPageIndex(0);
        }
    }

    @Override
    public void inEvent() {
    }

}
