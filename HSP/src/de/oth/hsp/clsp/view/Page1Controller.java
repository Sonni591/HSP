package de.oth.hsp.clsp.view;

import de.oth.hsp.common.dat.ConstraintException;
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
    public boolean checkInput() {
        try {
            int kHelp = (int) Double.parseDouble(K.getText());
            int tHelp = (int) Double.parseDouble(T.getText());
            int jHelp = (int) Double.parseDouble(J.getText());
            int mHelp = (int) Double.parseDouble(M.getText());
            if ((kHelp != 0) && (tHelp != 0) && (jHelp != 0) && (mHelp != 0)) {
                return true;
            }
        } catch (Exception e) {
        }
        openFalseInputAlert();
        return false;
    }

    @Override
    public void outEvent() {
        if (checkInput()) {
            int kHelp = (int) Double.parseDouble(K.getText());
            int tHelp = (int) Double.parseDouble(T.getText());
            int jHelp = (int) Double.parseDouble(J.getText());
            int mHelp = (int) Double.parseDouble(M.getText());
            try {
                root.getClspModel().setK(kHelp);
                root.getClspModel().setT(tHelp);
                root.getClspModel().setJ(jHelp);
                root.getClspModel().setM(mHelp);
                root.getClspModel().ensureConstraints();
            } catch (ConstraintException e) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Falsche Werte");
                alert.setHeaderText("Werte nicht korrekt");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
                root.getTab1Controller().getPagination().setCurrentPageIndex(0);
            }

        }
    }

    private void openFalseInputAlert() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Falsche Werte");
        alert.setHeaderText("Werte nicht korrekt");
        alert.setContentText(
                "Die Eingabewerte sind nicht zulässig. Es dürfen nur ganze Zahlen eingegeben werden, die größer 0 sind. Außerdem darf keines der Felder leer sein!");

        alert.showAndWait();
        root.getTab1Controller().getPagination().setCurrentPageIndex(0);
    }

    @Override
    public void inEvent() {

        K.setText(Integer.toString(root.getClspModel().getK()));
        T.setText(Integer.toString(root.getClspModel().getT()));
        J.setText(Integer.toString(root.getClspModel().getJ()));
        M.setText(Integer.toString(root.getClspModel().getM()));
    }

}
