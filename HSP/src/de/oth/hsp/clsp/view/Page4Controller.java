package de.oth.hsp.clsp.view;

import javafx.fxml.FXML;
<<<<<<< Updated upstream
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
=======
import javafx.scene.control.TableView;
>>>>>>> Stashed changes
import de.oth.hsp.common.utils.Decimals;
import de.oth.hsp.common.utils.TableUtils;
import de.oth.hsp.common.view.IPageController;

public class Page4Controller extends AbstractTableViewPage implements IPageController {

    // References to elements of the FXML Layout of Page2

    @FXML
    private TableView<Number[]> tableY0; // Anfangslagerbestaende für ein
                                         // Produkt K
    @FXML
    private TableView<Number[]> tableYT; // Endlagerbestaende für ein Produkt K

    @FXML
    private TextField tableValue;

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
        initTable(tableY0, true);
        initTable(tableYT, true);
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
    public boolean checkInput() {
        return true;
    }

    @Override
    public void outEvent() {
        root.getClspModel().setY0(TableUtils.convertOListToArray(tableY0.getItems()));
        root.getClspModel().setYT(TableUtils.convertOListToArray(tableYT.getItems()));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void inEvent() {

        Decimals decimals = new Decimals(2);

        setTableData(tableY0, root.getClspModel().getY0(), "", "k: ", decimals);
        setTableData(tableYT, root.getClspModel().getYT(), "", "k: ", decimals);

    }

    public void insertTableValues() {
        Number value = Integer.valueOf(tableValue.getText());
        tableTb.getItems().clear();
        tableTb.getColumns().clear();
        addColumnWithRowNumber(tableTb, "t: ");

        Number[][] d = root.getClspModel().getD();
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                d[i][j] = value;
            }
        }
        addTableViewContent(d, tableTb, new Decimals(2), "k: ");
    }
}
