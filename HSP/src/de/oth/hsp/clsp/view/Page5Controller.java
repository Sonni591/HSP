package de.oth.hsp.clsp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.view.AbstractTableViewPage;
import de.oth.hsp.common.view.IPageController;

public class Page5Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    // @FXML
    // private TableView<DoubleProperty[]> tableTb; // Bearbeitungszeit für eine
    // // Einheit
    // // von Produkt K auf Station J
    //
    // private ObservableList<DoubleProperty[]> dataListTb =
    // FXCollections.observableArrayList();

    @FXML
    private TableView<Number[]> tableTb; // Bearbeitungszeit für eine
    // Einheit
    // von Produkt K auf Station J

    private ObservableList<Number[]> dataListTb = FXCollections.observableArrayList();

    @FXML
    private Label labelTb; // Label: Stueckbearbeitungszeiten

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
        // root.getClspModel().setTb(TableUtils.convertOListTo2DArray(tableTb.getItems()));
    }

    @Override
    public boolean checkInput() {
        return true;
    }

    @Override
    public void inEvent() {
        Decimals decimals = new Decimals(2);
        setTableData(tableTb, root.getClspModel().getTb(), "j: ", "k: ", decimals, dataListTb);

        // Number[][] tb = root.getClspModel().getTb();
        // DoubleProperty[][] dproparr = new
        // DoubleProperty[tb.length][tb[0].length];
        // for (int i = 0; i < tb.length; i++) {
        // for (int j = 0; j < tb[0].length; j++) {
        // dproparr[i][j] = new SimpleDoubleProperty((double) tb[i][j]);
        // }
        // }
        //
        // dataListTb.addAll(dproparr);
        //
        // // dataListTb.addAll(Arrays.asList(root.getClspModel().getTb()));
        // // tableTb.setItems(dataListTb);
        //
        // setTableData(tableTb, dproparr, "j: ", "k: ", decimals, dataListTb);

    }

    public void insertTableValues() {

        // System.out.println(tableTb.getItems());

        Number value = Integer.valueOf(tableValue.getText());

        Number[][] tb = root.getClspModel().getTb();
        for (int i = 0; i < tb.length; i++) {
            for (int j = 0; j < tb[0].length; j++) {
                tb[i][j] = value;
            }
        }

        setTableData(tableTb, tb, "t: ", "k: ", new Decimals(2), dataListTb);

    }

}
