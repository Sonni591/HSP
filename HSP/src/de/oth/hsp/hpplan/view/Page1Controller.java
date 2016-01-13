package de.oth.hsp.hpplan.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import de.oth.hsp.common.dat.ConstraintException;
import de.oth.hsp.common.view.IPageController;

public class Page1Controller implements IPageController {

    // References to elements of the FXML Layout of Page1
    @FXML
    private TextField T; // Länge des Planungszeitraums
    @FXML
    private TextField K; // Anzahl an Produkten
    @FXML
    private TextField J; // Anzahl Produktionssegmente
    @FXML
    private TextField Z; // max. Vorlaufzeit

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
     * @return the j
     */
    public TextField getJ() {
        return J;
    }

    /**
     * @param j
     *            the j to set
     */
    public void setJ(TextField j) {
        J = j;
    }

    /**
     * @return the z
     */
    public TextField getZ() {
        return Z;
    }

    /**
     * @param z
     *            the z to set
     */
    public void setZ(TextField z) {
        Z = z;
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
            int zHelp = (int) Double.parseDouble(Z.getText());
            if ((kHelp != 0) && (tHelp != 0) && (jHelp != 0) && (zHelp != 0)) {
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
            int zHelp = (int) Double.parseDouble(Z.getText());
            try {
                root.getHpplanModel().setK(kHelp);
                root.getHpplanModel().setT(tHelp);
                root.getHpplanModel().setJ(jHelp);
                root.getHpplanModel().setzMax(zHelp);
                root.getHpplanModel().ensureConstraints();

                root.getTab1Controller().getPaginationController().getPage3Controller().initChoiceBox();

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
        alert.setContentText("Die Eingabewerte sind nicht zulässig. Es dürfen nur ganze Zahlen eingegeben werden, die größer 0 sind. Außerdem darf keines der Felder leer sein!");

        alert.showAndWait();
        root.getTab1Controller().getPagination().setCurrentPageIndex(0);
    }

    @Override
    public void inEvent() {

        K.setText(Integer.toString(root.getHpplanModel().getK().intValue()));
        T.setText(Integer.toString(root.getHpplanModel().getT().intValue()));
        J.setText(Integer.toString(root.getHpplanModel().getJ().intValue()));
        Z.setText(Integer.toString(root.getHpplanModel().getzMax().intValue()));
    }
}
