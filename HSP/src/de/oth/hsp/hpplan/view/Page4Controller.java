package de.oth.hsp.hpplan.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.view.IPageController;

public class Page4Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> tableLks; // Lagerkostensatz für eine Einheit
                                          // von Produkt k

    @FXML
    private TableView<Number[]> tableAb; // Anfangsbestand für eine Einheit von
                                         // Produkt k

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
        initTable(tableLks);
        initTable(tableAb);
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

        tableLks.getItems().clear();
        tableLks.getColumns().clear();

        // add a column with row numbers
        addColumnWithRowNumber(tableLks, "k: ");

        // get the data from the model and add it to the TableView
        Decimals decimals = new Decimals(2);
        // addTableViewContent(root.getClspModel().getTb(), tableTb, decimals,
        // "k: ");

        tableAb.getItems().clear();
        tableAb.getColumns().clear();
    }
}
