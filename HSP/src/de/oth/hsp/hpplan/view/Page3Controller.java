package de.oth.hsp.hpplan.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.utils.TableUtils;
import de.oth.hsp.common.view.AbstractTableViewPage;
import de.oth.hsp.common.view.IPageController;

public class Page3Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> table1; // Lagerkostensätze für ein Prudukt K

    @FXML
    private ChoiceBox<Integer> boxJ;

    private PaginationController paginationController;
    private RootLayoutController root;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page3Controller() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        initTable(table1, true);

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

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void inEvent() {

        table1.getItems().clear();
        table1.getColumns().clear();

        // add a column with row numbers
        addColumnWithRowNumber(table1, "");

        // get the data from the model and add it to the TableView
        for (int i = 0; i < root.getHpplanModel().getJ().intValue(); i++) {
            boxJ.getItems().add(i);
        }

        boxJ.getSelectionModel().selectFirst();

        Number[][][] f = root.getHpplanModel().getF();
        Number[][] temp = f[0];
        setTableData(table1, temp, "j: ", "t: ", new Decimals(2));

        boxJ.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue ov, Integer t, Integer t1) {
                System.out.println(t);
                System.out.println(t1);
                Number[][][] f = root.getHpplanModel().getF();
                Number[][] temp = TableUtils.convertOListTo2DArray(table1.getItems());
                f[t] = temp;
                root.getHpplanModel().setF(f);
                Number[][][] f1 = root.getHpplanModel().getF();
                Number[][] temp1 = f[t1];
                setTableData(table1, temp1, "j: ", "t: ", new Decimals(2));

            }
        });
    }

    @Override
    public boolean checkInput() {
        return true;
    }

}
