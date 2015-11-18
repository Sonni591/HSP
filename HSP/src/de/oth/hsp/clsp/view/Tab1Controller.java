package de.oth.hsp.clsp.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
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
                System.out.println("Oldvalue: " + oldValue.intValue() + " NewValue: " + newValue.intValue());

                if (paginationController.getPageControllerMap().containsKey(oldValue)) {
                    paginationController.getPageControllerMap().get(oldValue).outEvent();
                }
                if (paginationController.getPageControllerMap().containsKey(newValue)) {
                    paginationController.getPageControllerMap().get(newValue).inEvent();
                }

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

        if (paginationController.getPageMap().containsKey(index)) {
            return paginationController.getPageMap().get(index);
        } else {
            // default page - empty AnchorPane
            return new AnchorPane();
        }

    }

    /**
     * @param rootLayoutController
     */
    public void init(RootLayoutController rootLayoutController) {
        root = rootLayoutController;
        paginationController = new PaginationController(root);
    }
}
