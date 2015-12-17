package de.oth.hsp.clsp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.utils.TableUtils;
import de.oth.hsp.common.view.AbstractTableViewPage;
import de.oth.hsp.common.view.IPageController;

public class Page4Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> tableY0; // Anfangslagerbestaende für ein
                                         // Produkt K

    @FXML
    private TableView<Number[]> tableYT; // Endlagerbestaende für ein Produkt K

    private ObservableList<Number[]> dataListY0 = FXCollections.observableArrayList();
    private ObservableList<Number[]> dataListYT = FXCollections.observableArrayList();

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
        initTable(tableY0, true);
        initTable(tableYT, true);
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
        root.getClspModel().setY0(TableUtils.convertOListToArray(tableY0.getItems()));
        root.getClspModel().setYT(TableUtils.convertOListToArray(tableYT.getItems()));
    }

    @Override
    public void inEvent() {

        Decimals decimals = new Decimals(2);

        setTableData(tableY0, root.getClspModel().getY0(), "", "k: ", decimals, dataListY0);
        setTableData(tableYT, root.getClspModel().getYT(), "", "k: ", decimals, dataListYT);

    }

}
