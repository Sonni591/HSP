package de.oth.hsp.hpplan.view;

import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.view.IPageController;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Page2Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2
    @FXML
    private TableView<Number[]> table; // Nettobedarfsmenge des Produkts k in
                                       // Periode t

    private PaginationController paginationController;
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
        initTable(table);
    }

    /**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
        root = rootLayoutController;
    }

    @Override
    public void outEvent() {

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void inEvent() {

        table.getItems().clear();
        table.getColumns().clear();

        // add a column with row numbers
        addColumnWithRowNumber(table, "t: ");

        // get the data from the model and add it to the TableView

        // Number[][] d = root.getClspModel().getD();
        Decimals decimals = new Decimals(2);
        // addTableViewContent(d, table, decimals, "k: ");

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
        return true;
    }
}
