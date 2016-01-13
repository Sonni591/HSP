package de.oth.hsp.hpplan.view;

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
    private TableView<Number[]> tableU; // verfügbare Kapazität an der Station
                                        // j
                                        // in Periode t
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
        initTable(tableU, true);

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
        root.getHpplanModel().setU(TableUtils.convertOListTo2DArray(tableU.getItems()));
    }

    @Override
    public boolean checkInput() {
        return true;
    }

    @Override
    public void inEvent() {

        Decimals decimals = new Decimals(2);
        setTableData(tableU, root.getHpplanModel().getU(), "j: ", "t: ", decimals);

    }

    public void insertTableValues() {
        Number value = Integer.valueOf(tableValue.getText());

        Number[][] u = root.getHpplanModel().getU();
        for (int i = 0; i < u.length; i++) {
            for (int j = 0; j < u[0].length; j++) {
                u[i][j] = value;
            }
        }

        setTableData(tableU, u, "j: ", "t: ", new Decimals(2));

    }

}
