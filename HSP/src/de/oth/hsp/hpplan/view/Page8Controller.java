package de.oth.hsp.hpplan.view;

import de.oth.hsp.common.view.IPageController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Page8Controller extends AbstractTableViewPage implements IPageController {

    private PaginationController paginationController;
    private RootLayoutController root;

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

    }

    @Override
    public void inEvent() {
    }

    public void calculateEvent() {
        System.out.println("Page7 berechnen Button");
    }

    @Override
    public boolean checkInput() {
        return true;
    }
}
