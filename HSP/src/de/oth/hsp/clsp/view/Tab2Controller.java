package de.oth.hsp.clsp.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import de.oth.hsp.common.utils.Decimals;

public class Tab2Controller extends AbstractTableViewPage {

    // References for the FXML layout
    @FXML
    private TableView<Number[]> tableBackorders;
    @FXML
    private TableView<Number[]> tableLotsPerPeriod;
    @FXML
    private TableView<Number[]> tableStockAtEndOfPeriod;
    @FXML
    private TableView<Number[]> tableBinaryDecisionVariable;

    private RootLayoutController root;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Tab2Controller() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        initTable(tableBackorders, false);
        initTable(tableLotsPerPeriod, false);
        initTable(tableStockAtEndOfPeriod, false);
        initTable(tableBinaryDecisionVariable, false);
    }

    /**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
        root = rootLayoutController;
    }

    public void setResultData() {
        // prüfe ob ein ClspResponse vorhanden ist und ob eine gültige Lösung
        // vorliegt
        if (root.getClspResponse() != null && root.getClspResponse().isSolvable()) {

            // Clear the tables and columns
            tableBackorders.getItems().clear();
            tableBackorders.getColumns().clear();

            tableLotsPerPeriod.getItems().clear();
            tableLotsPerPeriod.getColumns().clear();

            tableStockAtEndOfPeriod.getItems().clear();
            tableStockAtEndOfPeriod.getColumns().clear();

            tableBinaryDecisionVariable.getItems().clear();
            tableBinaryDecisionVariable.getColumns().clear();

            // add a column with row numbers
            addColumnWithRowNumber(tableBackorders, "");
            addColumnWithRowNumber(tableLotsPerPeriod, "");
            addColumnWithRowNumber(tableStockAtEndOfPeriod, "");
            addColumnWithRowNumber(tableBinaryDecisionVariable, "");

            // set the table data
            Decimals decimals = new Decimals(0);
            addTableViewContent(root.getClspResponse().getBackordersNumberArr(), tableBackorders, decimals, "");
            addTableViewContent(root.getClspResponse().getLotsPerPeriodNumberArr(), tableLotsPerPeriod, decimals, "");
            addTableViewContent(root.getClspResponse().getStockAtEndOfPeriodNumberArr(), tableStockAtEndOfPeriod,
                    decimals, "");
            addTableViewContent(root.getClspResponse().getBinaryDecisionVariableNumberArr(),
                    tableBinaryDecisionVariable, decimals, "");

        } else {
            // TODO: Error description
        }

    }

}
