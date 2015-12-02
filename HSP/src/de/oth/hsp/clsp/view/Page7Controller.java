package de.oth.hsp.clsp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import de.oth.hsp.clsp.model.CLSPModel;
import de.oth.hsp.common.view.IPageController;

public class Page7Controller extends AbstractTableViewPage implements IPageController {

    private CLSPModel clspModel;

    private PaginationController paginationController;
    private RootLayoutController root;

    @FXML
    private Button calculateButton;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page7Controller() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        calculateButton.setText("Berechnen");
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

    }

    @Override
    public void inEvent() {
    }

    public void calculateEvent() {
        System.out.println("Page7 berechnen Button");
    }
}
