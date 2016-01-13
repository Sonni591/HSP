package de.oth.hsp.clsp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableView<Number[]> tableTb; // Bearbeitungszeit für eine
    // Einheit
    // von Produkt K auf Station J

    private ObservableList<Number[]> dataListTb = FXCollections.observableArrayList();

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
        initTable(tableTb, true);
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
        root.getClspModel().setTb(TableUtils.convertOListTo2DArray(tableTb.getItems()));
    }

    @Override
    public boolean checkInput() {
        return true;
    }

    @Override
    public void inEvent() {

        Decimals decimals = new Decimals(2);
        setTableData(tableTb, root.getClspModel().getTb(), "j: ", "k: ", decimals);

    }

    public void insertTableValues() {

        Number value = Integer.valueOf(tableValue.getText());

        Number[][] tb = root.getClspModel().getTb();
        for (int i = 0; i < tb.length; i++) {
            for (int j = 0; j < tb[0].length; j++) {
                tb[i][j] = value;
            }
        }

        setTableData(tableTb, tb, "j: ", "k: ", new Decimals(2));

    }

    // public void testPrintout(ObservableList<Number[]> data) {
    //
    // for (int i = 0; i < data.size(); i++) {
    // Number n[] = data.get(i);
    // for (int j = 0; j < n.length; j++) {
    // Double d = n[j].doubleValue();
    // System.out.print(d + " ");
    // }
    // System.out.println("");
    // }
    //
    // }

}
