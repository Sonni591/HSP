package de.oth.hsp.clsp.view;

import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.utils.TableUtils;
import de.oth.hsp.common.view.IPageController;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class Page3Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> tableH; // Lagerkostensätze für ein Prudukt K

    @FXML
    private TableView<Number[]> tableS; // Rüstkostensätze für ein Prudukt K

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
        initTable(tableH, true);
        initTable(tableS, true);
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
        return true;
    }

    @Override
    public void outEvent() {
        root.getClspModel().setH(TableUtils.convertOListToArray(tableH.getItems()));
        root.getClspModel().setS(TableUtils.convertOListToArray(tableS.getItems()));
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

        Decimals decimals = new Decimals(2);
        addTableViewContent(root.getClspModel().getH(), tableH, decimals, "k: ");
        addTableViewContent(root.getClspModel().getS(), tableS, decimals, "k: ");

    }
}
