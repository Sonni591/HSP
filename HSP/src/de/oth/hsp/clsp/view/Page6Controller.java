package de.oth.hsp.clsp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.utils.TableUtils;
import de.oth.hsp.common.view.AbstractTableViewPage;
import de.oth.hsp.common.view.IPageController;

public class Page6Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> tableTr; // Rüstzeit für ein Prudukt K an
                                         // Station J

    private ObservableList<Number[]> dataListTr = FXCollections.observableArrayList();

    @FXML
    private Label labelTr; // Label: Rüstzeiten

    @FXML
    private TextField tableValue;

    private PaginationController paginationController;
    private RootLayoutController root;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page6Controller() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        initTable(tableTr, true);
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
        root.getClspModel().setTr(TableUtils.convertOListTo2DArray(tableTr.getItems()));
    }

    @Override
    public boolean checkInput() {
        return true;
    }

    @Override
    public void inEvent() {
        Decimals decimals = new Decimals(2);
        setTableData(tableTr, root.getClspModel().getTr(), "j: ", "k: ", decimals);
    }

    public void insertTableValues() {
        Number value = Integer.valueOf(tableValue.getText());

        Number[][] tr = root.getClspModel().getTr();
        for (int i = 0; i < tr.length; i++) {
            for (int j = 0; j < tr[0].length; j++) {
                tr[i][j] = value;
            }
        }

        setTableData(tableTr, tr, "t: ", "k: ", new Decimals(2));

    }

}
