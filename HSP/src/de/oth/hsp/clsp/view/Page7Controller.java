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

public class Page7Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> tableB; // verfügbare Kapazität an der Station j
                                        // in Periode t

    private ObservableList<Number[]> dataListB = FXCollections.observableArrayList();

    @FXML
    private TextField tableValue;

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
        initTable(tableB, true);

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
        root.getClspModel().setB(TableUtils.convertOListTo2DArray(tableB.getItems()));
    }

    @Override
    public boolean checkInput() {
        return true;
    }

    @Override
    public void inEvent() {

        Decimals decimals = new Decimals(2);
        setTableData(tableB, root.getClspModel().getB(), "j: ", "t: ", decimals);

    }

    public void insertTableValues() {
        Number value = Integer.valueOf(tableValue.getText());

        Number[][] b = root.getClspModel().getB();
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                b[i][j] = value;
            }
        }

        setTableData(tableB, b, "t: ", "k: ", new Decimals(2));

    }

}
