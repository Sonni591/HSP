package de.oth.hsp.clsp.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import de.oth.hsp.clsp.ilog.CLSPResponse;
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
            Decimals decimals = new Decimals(2);
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

    public void setDummyData() {

        CLSPResponse clspResponse = new CLSPResponse(true, createdDummyMatrix(3, 3), createdDummyMatrix(4, 4),
                createdDummyMatrix(5, 5), createdDummyMatrix(5, 5));
        root.setClspResponse(clspResponse);
        setResultData();

    }

    public int[][] createdDummyMatrix(int dim1, int dim2) {
        int[][] matrix = new int[dim1][dim2];
        int t = 0;
        for (int i = 0; i < dim1; i++) {
            for (int j = 0; j < dim2; j++) {
                matrix[i][j] = t;
                t++;
            }
        }
        return matrix;
    }

}
