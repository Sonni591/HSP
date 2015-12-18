package de.oth.hsp.clsp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import de.oth.hsp.common.view.AbstractTableViewPage;
import de.oth.hsp.common.view.IPageController;

public class Page8Controller extends AbstractTableViewPage implements IPageController {

    private PaginationController paginationController;
    private RootLayoutController root;

    @FXML
    private TextField epgap; // CPLEX_EPGAP - relative Optimalitätslücke
    @FXML
    private Button calculateButton;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page8Controller() {

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
    public void outEvent() {
        double epgapHelp = Double.parseDouble(epgap.getText());
        root.getClspModel().setEpgap(epgapHelp);
    }

    @Override
    public boolean checkInput() {
        return true;
    }

    @Override
    public void inEvent() {
        epgap.setText(Double.toString(root.getClspModel().getEpgap()));

    }

    public void onActionCalculate() {
        System.out.println("Page7 berechnen Button");

        outEvent(); // write all Data from this page into the clspModel

        System.out.println(root.getClspModel());

        // TODO: ensure correct data and start calculation

        root.calculateCLSP();

    }
}
