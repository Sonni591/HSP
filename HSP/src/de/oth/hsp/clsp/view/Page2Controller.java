package de.oth.hsp.clsp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.utils.TableUtils;
import de.oth.hsp.common.view.AbstractTableViewPage;
import de.oth.hsp.common.view.IPageController;

/**
 * Class for a page containing variables for the lot scheduling problem
 */
public class Page2Controller extends AbstractTableViewPage implements IPageController {

    /**
     * References to elements of the FXML Layout of Page2
     */
    @FXML
    private TextField tableValue;
    @FXML
    private TableView<Number[]> tableD; // Nettobedarfsmenge des Produkts k in
                                        // Periode t

    private ObservableList<Number[]> dataListD = FXCollections.observableArrayList();

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
        initTable(tableD, true);
    }

    /**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
        root = rootLayoutController;
    }

    /**
     * @see de.oth.hsp.common.view.IPageController#outEvent()
     */
    @Override
    public void outEvent() {
        if (!tableD.getItems().isEmpty()) {
            root.getClspModel().setD(TableUtils.convertOListTo2DArray(tableD.getItems()));
        }
    }

    /**
     * @see de.oth.hsp.common.view.IPageController#checkInput()
     */
    @Override
    public boolean checkInput() {
        return true;
    }

    /**
     * @see de.oth.hsp.common.view.IPageController#inEvent()
     */
    @Override
    public void inEvent() {
        setTableData(tableD, root.getClspModel().getD(), "t: ", "k: ", new Decimals(2));
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

    /**
     * method to insert the same value into every cell of the displayed table
     */
    public void insertTableValues() {
        Number value = Integer.valueOf(tableValue.getText());

        Number[][] d = root.getClspModel().getD();
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                d[i][j] = value;
            }
        }

        setTableData(tableD, d, "t: ", "k: ", new Decimals(2));

    }
}
