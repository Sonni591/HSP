package de.oth.hsp.clsp.view;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.utils.TableUtils;
import de.oth.hsp.common.view.IPageController;
<<<<<<< Updated upstream
=======
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
>>>>>>> Stashed changes

public class Page6Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> tableTr; // Rüstzeit für ein Prudukt K an
                                         // Station J

    @FXML
    private Label labelTr; // Label: Rüstzeiten

    @FXML
    private TextField tableValue;

    private PaginationController paginationController;
    private RootLayoutController root;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page6Controller() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        initTable(tableTr, true);
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
        root.getClspModel().setTb(TableUtils.convertOListTo2DArray(tableTr.getItems()));
    }

    @Override
    public boolean checkInput() {
        return true;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void inEvent() {

        tableTr.getItems().clear();
        tableTr.getColumns().clear();

        // add a column with row numbers

        addColumnWithRowNumber(tableTr, "j: ");

        // get the data from the model and add it to the TableView
        Decimals decimals = new Decimals(2);
        addTableViewContent(root.getClspModel().getTr(), tableTr, decimals, "k: ");

    }

    public void insertTableValues() {
        Number value = Integer.valueOf(tableValue.getText());
        tableB.getItems().clear();
        tableB.getColumns().clear();
        addColumnWithRowNumber(tableB, "t: ");

        Number[][] d = root.getClspModel().getD();
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                d[i][j] = value;
            }
        }
        addTableViewContent(d, tableB, new Decimals(2), "k: ");
    }
}
