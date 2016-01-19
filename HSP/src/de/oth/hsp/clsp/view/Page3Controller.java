package de.oth.hsp.clsp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.utils.TableUtils;
import de.oth.hsp.common.view.AbstractTableViewPage;
import de.oth.hsp.common.view.IPageController;

/**
 * Class for a page containing variables for the lot scheduling problem
 */
public class Page3Controller extends AbstractTableViewPage implements IPageController {

    /**
     * References to elements of the FXML Layout of Page2
     */
    @FXML
    private TableView<Number[]> tableH; // Lagerkostensätze für ein Produkt K
    @FXML
    private TableView<Number[]> tableS; // Rüstkostensätze für ein Produkt K
    @FXML
    private TableView<Number[]> tableZ; // Mindestvorlaufzeiten für ein
                                        // Produkt K

    private ObservableList<Number[]> dataListH = FXCollections.observableArrayList();
    private ObservableList<Number[]> dataListS = FXCollections.observableArrayList();
    private ObservableList<Number[]> dataListZ = FXCollections.observableArrayList();

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
        initTable(tableZ, true);
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

    /**
     * @see de.oth.hsp.common.view.IPageController#checkInput()
     */
    @Override
    public boolean checkInput() {
        return true;
    }

    /**
     * @see de.oth.hsp.common.view.IPageController#outEvent()
     */
    @Override
    public void outEvent() {
        root.getClspModel().setH(TableUtils.convertOListToArray(tableH.getItems()));
        root.getClspModel().setS(TableUtils.convertOListToArray(tableS.getItems()));
        root.getClspModel().setZ(TableUtils.convertOListToArray(tableZ.getItems()));
    }

    /**
     * @see de.oth.hsp.common.view.IPageController#inEvent()
     */
    @Override
    public void inEvent() {

        Decimals decimals = new Decimals(2);

        setTableData(tableS, root.getClspModel().getS(), "", "k: ", decimals, dataListH);
        setTableData(tableH, root.getClspModel().getH(), "", "k: ", decimals, dataListS);
        setTableData(tableZ, root.getClspModel().getZ(), "", "k: ", decimals, dataListZ);

    }

}
