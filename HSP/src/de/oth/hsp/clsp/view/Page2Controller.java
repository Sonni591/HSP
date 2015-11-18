package de.oth.hsp.clsp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import de.oth.hsp.clsp.model.CLSPModel;
import de.oth.hsp.common.view.IPageController;

public class Page2Controller implements IPageController {

    // References to elements of the FXML Layout of Page2
    @FXML
    private TableView<String[]> table; // Nettobedarfsmenge des Produkts k in
                                       // Periode t
    @FXML
    private Button testButton;

    private CLSPModel clspModel;

    private PaginationController paginationController;
    private RootLayoutController root;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page2Controller() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        table.getColumns().clear();
        table.getItems().clear();
        table.setEditable(true);
    }

    /**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
        root = rootLayoutController;
        clspModel = root.getClspModel();
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

    @FXML
    public void fireTestEvent() {

    }

    @Override
    public void outEvent() {
        System.out.println("OutEvent von Page2Ctrl");

    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void inEvent() {
        // String[][] d = clspModel.getD();
        //
        // // Dummy column to fake row header
        //
        // String[] staffArray = { "nice to ", "a", "d" };
        //
        // ObservableList<String[]> rowHeaderData =
        // FXCollections.observableArrayList();
        // rowHeaderData.add(staffArray);
        // // String[] row = new String[table.getColumns().size()];
        // // for (int i = 0; i <staffArray.length; i++) {
        // // for(int j = 0; j<table.getColumns().size();j++){
        // //
        // // }
        // // rowHeaderData.add(row);
        // // }
        // //
        // //
        // TableColumn rowHeader = new TableColumn("RowHeader");
        // //
        // rowHeader.prefWidthProperty().bind(table.widthProperty().divide(clspModel.getK()
        // // + 1));
        // table.getColumns().add(rowHeader);
        // for (int i = 0; i < staffArray.length; i++) {
        // final int colNo = i;
        // rowHeader.setCellValueFactory(new Callback<CellDataFeatures<String[],
        // String>, ObservableValue<String>>() {
        // @Override
        // public ObservableValue<String> call(CellDataFeatures<String[],
        // String> p) {
        // return new SimpleStringProperty((p.getValue()[colNo]));
        // }
        // });
        // }
        // table.setItems(rowHeaderData);
        // // End of Dummy column
        //
        // // ObservableList<String[]> data =
        // FXCollections.observableArrayList();
        // // data.addAll(Arrays.asList(d));
        // // for (int i = 0; i < d[0].length; i++) {
        // // TableColumn tc = new TableColumn("k: " + String.valueOf(i + 1));
        // // final int colNo = i;
        // // tc.setCellFactory(TextFieldTableCell.<String> forTableColumn());
        // // tc.setCellValueFactory(new Callback<CellDataFeatures<String[],
        // // String>, ObservableValue<String>>() {
        // // @Override
        // // public ObservableValue<String> call(CellDataFeatures<String[],
        // // String> p) {
        // // return new SimpleStringProperty((p.getValue()[colNo]));
        // // }
        // // });
        // //
        // tc.prefWidthProperty().bind(table.widthProperty().divide(clspModel.getK()));
        // // table.getColumns().add(tc);
        // // }
        // // table.setItems(data);
        // // table.setEditable(true);

    }
}
