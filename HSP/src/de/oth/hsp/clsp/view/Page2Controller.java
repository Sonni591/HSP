package de.oth.hsp.clsp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import de.oth.hsp.clsp.model.CLSPModel;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.view.IPageController;

public class Page2Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2
    @FXML
    private TableView<Number[]> table; // Nettobedarfsmenge des Produkts k in
                                       // Periode t
    @FXML
    private Button testButton;

    private CLSPModel clspModel;

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
        table.getColumns().clear();
        table.getItems().clear();
        table.setEditable(true);

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

    @FXML
    public void fireTestEvent() {

    }

    @Override
    public void outEvent() {
        System.out.println("OutEvent von Page2Ctrl");

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void inEvent() {

        table.getColumns().clear();
        table.setEditable(true);

        // add a column with row numbers
        addColumnWithRowNumber(table, "t: ");

        // get the data from the model and add it to the TableView
        Number[][] d = clspModel.getD();
        Decimals decimals = new Decimals(2);
        addTableViewContent(d, table, decimals);

    }
}
