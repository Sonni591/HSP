package de.oth.hsp.hpplan.view;

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
public class Page4Controller extends AbstractTableViewPage implements IPageController {

    /**
     * References to elements of the FXML Layout of Page2
     */
    @FXML
    private TableView<Number[]> tableH; // Lagerkostensatz für eine Einheit
                                        // von Produkt k
    @FXML
    private TableView<Number[]> tableiInit; // Anfangsbestand für eine Einheit
                                            // von
                                            // Produkt k

    private ObservableList<Number[]> dataListH = FXCollections.observableArrayList();
    private ObservableList<Number[]> dataListiIinit = FXCollections.observableArrayList();

    private PaginationController paginationController;
    private RootLayoutController root;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page4Controller() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        initTable(tableH, true);
        initTable(tableiInit, true);
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
        root.getHpplanModel().setH(TableUtils.convertOListToArray(tableH.getItems()));
        root.getHpplanModel().setiInit(TableUtils.convertOListToArray(tableiInit.getItems()));

    }

    /**
     * @see de.oth.hsp.common.view.IPageController#inEvent()
     */
    @Override
    public void inEvent() {

        Decimals decimals = new Decimals(2);

        setTableData(tableH, root.getHpplanModel().getH(), "", "k: ", decimals, dataListH);
        setTableData(tableiInit, root.getHpplanModel().getiInit(), "", "k: ", decimals, dataListiIinit);

    }

}
