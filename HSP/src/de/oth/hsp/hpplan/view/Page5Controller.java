package de.oth.hsp.hpplan.view;

import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.view.IPageController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class Page5Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> tableN; // Rüstzeit für ein Prudukt K an
                                        // Station J

    @FXML
    private Label labelTr; // Label: Rüstzeiten

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

        initTable(tableN);
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

        tableN.getItems().clear();
        tableN.getColumns().clear();

        // add a column with row numbers

        addColumnWithRowNumber(tableN, "j: ");

        // get the data from the model and add it to the TableView
        Decimals decimals = new Decimals(2);
        // addTableViewContent(root.getClspModel().getTr(), tableTr, decimals,
        // "k: ");

    }

    @Override
    public boolean checkInput() {
        return true;
    }
}
