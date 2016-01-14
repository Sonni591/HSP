package de.oth.hsp.hpplan.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.view.AbstractTableViewPage;
import de.oth.hsp.hpplan.ilog.HPPlanStatischResponse;

public class Tab2Controller extends AbstractTableViewPage {

    // References for the FXML layout
    @FXML
    private TableView<Number[]> usedAdditionalCapacityPerPeriod;
    @FXML
    private TableView<Number[]> lotSizePerPeriod;
    @FXML
    private TableView<Number[]> stockAtEndOfPeriod;
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
        initTable(usedAdditionalCapacityPerPeriod, false);
        initTable(lotSizePerPeriod, false);
        initTable(stockAtEndOfPeriod, false);
    }

    /**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
        root = rootLayoutController;
    }

    public void setResultData(HPPlanStatischResponse hpplanResponse) {
        // prüfe ob ein HpplanResponse vorhanden ist und ob eine gültige Lösung
        // vorliegt
        if (hpplanResponse != null && hpplanResponse.isSolvable()) {

            setTableData(usedAdditionalCapacityPerPeriod, hpplanResponse.getUsedAdditionalCapacityPerPeriodNumberArr(),
                    "t", "k", new Decimals(2));
            setTableData(lotSizePerPeriod, hpplanResponse.getLotSizePerPeriodNumberArr(), "t", "k", new Decimals(2));
            setTableData(stockAtEndOfPeriod, hpplanResponse.getStockAtEndOfPeriodNumberArr(), "t", "k", new Decimals(2));

            root.tabPaneSwitchToTab(1); // switch to Tab 2

        } else {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Keine Lösung");
            alert.setHeaderText("Dieses Problem ist nicht lösbar!");
            alert.setContentText("Bitte überprüfen sie die Eingabewerte");

            alert.showAndWait();
        }

    }

    public void setDummyData() {

        HPPlanStatischResponse hpplanResponse = new HPPlanStatischResponse(true, createdDummyMatrix(3, 3),
                createdDummyMatrix(4, 4), createdDummyMatrix(5, 5));
        setResultData(hpplanResponse);

    }

    public float[][] createdDummyMatrix(int dim1, int dim2) {
        float[][] matrix = new float[dim1][dim2];
        int t = 0;
        for (int i = 0; i < dim1; i++) {
            for (int j = 0; j < dim2; j++) {
                matrix[i][j] = t;
                t++;
            }
        }
        return matrix;
    }

    public int[][] createdDummyMatrixInt(int dim1, int dim2) {
        int[][] matrix = new int[dim1][dim2];
        int t = 0;
        for (int i = 0; i < dim1; i++) {
            for (int j = 0; j < dim2; j++) {
                matrix[i][j] = t;
            }
        }
        return matrix;
    }
}
