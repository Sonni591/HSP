package de.oth.hsp.hpplan.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.view.IPageController;

public class Page7Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> tableKzk; // verfügbare Kapazität an der Station
                                          // j
                                          // in Periode t

    private PaginationController paginationController;
    private RootLayoutController root;

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
        initTable(tableKzk);

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

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void inEvent() {

        tableKzk.getItems().clear();
        tableKzk.getColumns().clear();

        // add a column with row numbers
        addColumnWithRowNumber(tableKzk, "j: ");

        // get the data from the model and add it to the TableView
        Decimals decimals = new Decimals(2);
        // addTableViewContent(root.getClspModel().getB(), tableB, decimals,
        // "t: ");
    }
}
