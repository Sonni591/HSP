package de.oth.hsp.clsp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import de.oth.hsp.clsp.ilog.CLSPResponse;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.view.AbstractTableViewPage;

/**
 * class to control the tab 2
 */
public class Tab2Controller extends AbstractTableViewPage {

    /**
     * References for the FXML layout
     */
    @FXML
    private TableView<Number[]> tableLotsPerPeriod;
    @FXML
    private TableView<Number[]> tableStockAtEndOfPeriod;
    @FXML
    private TableView<Number[]> tableSetUpVariables;
    @FXML
    private TextField totalCosts;

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
        initTable(tableLotsPerPeriod, false);
        initTable(tableStockAtEndOfPeriod, false);
        initTable(tableSetUpVariables, false);

    }

    /**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
        root = rootLayoutController;
    }

    /**
     * @param clspResponse
     *            dos set the values from the CLSPResponse in the GUI
     */
    public void setResultData(CLSPResponse clspResponse) {
        // check if there is a response and it is solvable
        if (clspResponse != null && clspResponse.isSolvable()) {

            totalCosts.setText(Double.toString(clspResponse.getResult().doubleValue()));

            setTableData(tableLotsPerPeriod, clspResponse.getLotsPerPeriodNumberArr(), "t", "k", new Decimals(2));
            setTableData(tableStockAtEndOfPeriod, clspResponse.getStockAtEndOfPeriodNumberArr(), "t", "k",
                    new Decimals(2));
            setTableData(tableSetUpVariables, clspResponse.getSetUpVariablesNumberArr(), "t", "k", new Decimals(2));

            root.tabPaneSwitchToTab(1); // switch to Tab 2

        } else {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Keine Lösung");
            alert.setHeaderText("Dieses Problem ist nicht lösbar!");
            alert.setContentText("Bitte überprüfen sie die Eingabewerte");

            alert.showAndWait();
        }

    }

    /**
     * set some dummy data
     */
    public void setDummyData() {

        CLSPResponse clspResponse = new CLSPResponse(true, createdDummyMatrix(3, 3), createdDummyMatrix(4, 4),
                createdDummyMatrix(5, 5), 0);
        setResultData(clspResponse);

    }

    /**
     * @param dim1
     * @param dim2
     * @return creates a dummy matrix with number values
     */
    public Number[][] createdDummyMatrix(int dim1, int dim2) {
        Number[][] matrix = new Number[dim1][dim2];
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
