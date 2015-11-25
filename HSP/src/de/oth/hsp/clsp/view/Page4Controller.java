package de.oth.hsp.clsp.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import de.oth.hsp.clsp.model.CLSPModel;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.view.IPageController;

public class Page4Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> tableTb; // Bearbeitungszeit für eine Einheit
                                         // von Produkt K auf Station J
    @FXML
    private TableView<Number[]> tableTr; // Rüstzeit für ein Prudukt K an
                                         // Station J
    @FXML
    private Label labelTb; // Label: Stueckbearbeitungszeiten
    @FXML
    private Label labelTr; // Label: Rüstzeiten

    private CLSPModel clspModel;

    private PaginationController paginationController;
    private RootLayoutController root;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page4Controller() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        initTable(tableTb);
        initTable(tableTr);
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

        tableTr.getItems().clear();
        tableTr.getColumns().clear();

        tableTb.getItems().clear();
        tableTb.getColumns().clear();

        // add a column with row numbers
        addColumnWithRowNumber(tableTb, "j: ");
        addColumnWithRowNumber(tableTr, "j: ");

        // get the data from the model and add it to the TableView
        Number[][] d = clspModel.getD();

        Number[][] tr = { { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9 } };

        Decimals decimals = new Decimals(2);
        addTableViewContent(tr, tableTb, decimals);
        addTableViewContent(d, tableTr, decimals);

    }
}
