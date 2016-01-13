package de.oth.hsp.hpplan.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.utils.TableUtils;
import de.oth.hsp.common.view.AbstractTableViewPage;
import de.oth.hsp.common.view.IPageController;

public class Page2Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2
    @FXML
    private TextField tableValue;
    @FXML
    private TableView<Number[]> tableB; // Nettobedarfsmenge des Produkts k in
                                        // Periode t

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
        initTable(tableB, true);
    }

    /**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
        root = rootLayoutController;
    }

    @Override
    public void outEvent() {
        root.getHpplanModel().setB(TableUtils.convertOListTo2DArray(tableB.getItems()));

    }

    @Override
    public boolean checkInput() {
        return true;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void inEvent() {
        setTableData(tableB, root.getHpplanModel().getB(), "j: ", "t: ", new Decimals(2));
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

    public void insertTableValues() {
        Number value = Integer.valueOf(tableValue.getText());

        Number[][] b = root.getHpplanModel().getB();
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                b[i][j] = value;
            }
        }

        setTableData(tableB, b, "j: ", "t: ", new Decimals(2));

    }

}
