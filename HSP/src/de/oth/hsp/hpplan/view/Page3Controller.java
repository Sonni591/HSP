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

/**
 * Class for a page containing variables for the lot scheduling problem
 */
public class Page3Controller extends AbstractTableViewPage implements IPageController {

    /**
     * References to elements of the FXML Layout of Page2
     */
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

    /**
     * @see de.oth.hsp.common.view.IPageController#outEvent()
     */
    @Override
    public void outEvent() {
        Number[][][] f = root.getHpplanModel().getF();
        Number[][] temp = TableUtils.convertOListTo2DArray(table1.getItems());
        f[boxJ.getValue()] = temp;
        root.getHpplanModel().setF(f);
    }

    /**
     * @see de.oth.hsp.common.view.IPageController#inEvent()
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void inEvent() {

        table1.getItems().clear();
        table1.getColumns().clear();

        // add a column with row numbers
        addColumnWithRowNumber(table1, "");

        Number[][][] f = root.getHpplanModel().getF();
        Number[][] temp = f[0];
        setTableData(table1, temp, "j: ", "t: ", new Decimals(2));

        boxJ.getSelectionModel().selectFirst();
    }

    /**
     * does init the choice box, which defines the third dimension of the 3-dim
     * HPPLAN parameter
     */
    public void initChoiceBox() {

        boxJ.getItems().clear();
        for (int i = 0; i < root.getHpplanModel().getJ().intValue(); i++) {
            boxJ.getItems().add(i);
        }

        boxJ.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue ov, Integer t, Integer t1) {
                if (boxJ.getItems() != null && table1.getItems() != null && t1 != null && t != null) {
                    Number[][][] f = root.getHpplanModel().getF();
                    Number[][] temp = TableUtils.convertOListTo2DArray(table1.getItems());
                    f[t] = temp;
                    root.getHpplanModel().setF(f);
                    Number[][][] f1 = root.getHpplanModel().getF();
                    Number[][] temp1 = f1[t1];
                    setTableData(table1, temp1, "j: ", "t: ", new Decimals(2));
                }
            }
        });
    }

    /**
     * @see de.oth.hsp.common.view.IPageController#checkInput()
     */
    @Override
    public boolean checkInput() {
        return true;
    }

}
