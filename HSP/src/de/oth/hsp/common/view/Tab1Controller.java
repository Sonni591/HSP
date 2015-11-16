package de.oth.hsp.common.view;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class Tab1Controller {

    // References for the FXML layout
    @FXML
    private Pagination pagination;

    // References to FXML controllers
    private RootLayoutController root;
    private PaginationController paginationController;

    // Pages of the Pagination
    private AnchorPane pagePane1;
    private AnchorPane pagePane2;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Tab1Controller() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        customizePagination();

    }

    /**
	 * 
	 */
    private void customizePagination() {
        // create the PageFactory to handle the Pages of the Pagination-Control
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer pageIndex) {
                return createPage(pageIndex);
            }
        });

        // add a Listener on the Pagination-Control to get to know changed
        // indexes
        pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // System.out.println("Oldvalue: " + oldValue.intValue()
                // + " NewValue: " + newValue.intValue());

                // Test Output
                // System.out.println("T: " +
                // paginationController.getT().getText());
                // System.out.println("K: " +
                // paginationController.getK().getText());

            }

        });
    }

    /**
     * Method returns an AnchorPane containing the layout for the current page
     * index. If the pane does not exist yet, it is loaded from the fxml-Layout.
     * Otherwise the existing pane is used.
     * 
     * @param index
     *            of the current Page
     * @return AnchorPane of the current Page
     */
    public AnchorPane createPage(int index) {

        AnchorPane myPane = new AnchorPane();

        // Change on Page-Index
        switch (index) {
        case 0:
            // load pane from fxml-definiton
            if (pagePane1 == null) {
                pagePane1 = loadPaneFromFXML("Page1.fxml");
            }
            // set the pane
            myPane = pagePane1;
            break;
        case 1:
            // load pane from fxml-definiton
            if (pagePane2 == null) {
                pagePane2 = loadPaneFromFXML("Page2.fxml");
            }
            // set the pane
            myPane = pagePane2;

            break;
        case 2:

            break;
        default:
            break;
        }

        return myPane;

    }

    /**
     * Returns an AnchorPane with the Layout of the given FXML-Layout and saves
     * a reference to the Pagination-Controller
     * 
     * @param fxmlLayout
     *            - String of the FXML-Layout to use
     * @return AnchorPane containing the layout
     */
    private AnchorPane loadPaneFromFXML(String fxmlLayout) {

        // load the pane from the fxml-layout
        AnchorPane pane = new AnchorPane();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlLayout));
        try {
            pane = fxmlLoader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // also save a reference of the controller to PaginationController
        // if (paginationController == null && fxmlLoader != null) {
        savePageControllers(fxmlLoader, fxmlLayout);

        return pane;
    }

    /**
     * Save references of the Page Controllers in the Class PaginationController
     * 
     * @param fxmlLoader
     * @param fxmlLayout
     */
    private void savePageControllers(FXMLLoader fxmlLoader, String fxmlLayout) {
        if (fxmlLoader != null) {
            switch (fxmlLayout) {
            case "Page1.fxml":
                paginationController.setPage1Controller(fxmlLoader.getController());
                break;
            case "Page2.fxml":
                paginationController.setPage2Controller(fxmlLoader.getController());
                break;

            default:
                break;
            }

        }
    }

    /**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
        root = rootLayoutController;
        paginationController = new PaginationController();
    }
}
