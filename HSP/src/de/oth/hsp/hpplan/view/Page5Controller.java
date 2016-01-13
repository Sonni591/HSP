package de.oth.hsp.hpplan.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.utils.TableUtils;
import de.oth.hsp.common.view.AbstractTableViewPage;
import de.oth.hsp.common.view.IPageController;

public class Page5Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> tableD; // Nachfrage

    @FXML
    private TextField tableValue;

    private PaginationController paginationController;
    private RootLayoutController root;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page5Controller() {

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
        root.getHpplanModel().setD(TableUtils.convertOListTo2DArray(tableD.getItems()));
    }

    @Override
    public boolean checkInput() {
        return true;
    }

    @Override
    public void inEvent() {

        Decimals decimals = new Decimals(2);
        setTableData(tableD, root.getHpplanModel().getD(), "j: ", "k: ", decimals);

    }

    public void insertTableValues() {

        Number value = Integer.valueOf(tableValue.getText());

        Number[][] d = root.getHpplanModel().getD();
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                d[i][j] = value;
            }
        }

        setTableData(tableD, d, "t: ", "k: ", new Decimals(2));

    }

}
