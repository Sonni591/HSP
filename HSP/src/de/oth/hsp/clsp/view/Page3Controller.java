package de.oth.hsp.clsp.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import de.oth.hsp.clsp.model.CLSPModel;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.view.IPageController;

public class Page3Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> tableH; // Lagerkostensätze für ein Prudukt K

    @FXML
    private TableView<Number[]> tableS; // Rüstkostensätze für ein Prudukt K

    private CLSPModel clspModel;

    private PaginationController paginationController;
    private RootLayoutController root;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page3Controller() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        initTable(tableH);
        initTable(tableS);
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

        tableS.getItems().clear();
        tableS.getColumns().clear();

        tableH.getItems().clear();
        tableH.getColumns().clear();

        // add a column with row numbers
        addColumnWithRowNumber(tableH, "");
        addColumnWithRowNumber(tableS, "");

        // get the data from the model and add it to the TableView
        Number[][] d = clspModel.getD();

        Number[][] tr = { { 1, 2, 3, 4, 5, 6, 7, 8, 9 } };

        Decimals decimals = new Decimals(2);
        addTableViewContent(tr, tableH, decimals);
        addTableViewContent(tr, tableS, decimals);

    }
}
