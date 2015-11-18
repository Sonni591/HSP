package de.oth.hsp.clsp.view;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import de.oth.hsp.common.view.IPageController;

public class PaginationController {

    // Pages of the Pagination
    private AnchorPane pagePane1;
    private AnchorPane pagePane2;

    // References to FXML controllers
    private RootLayoutController root;

    private Page1Controller page1Controller;
    private Page2Controller page2Controller;

    private HashMap<Integer, IPageController> pageControllerMap = new HashMap<Integer, IPageController>();
    private HashMap<Integer, AnchorPane> pageMap = new HashMap<Integer, AnchorPane>();

    public PaginationController(RootLayoutController root) {
        this.root = root;
        initializePages();
        initializePageControllers();
    }

    public void initializePages() {
        // load pane from fxml-definiton
        if (pagePane1 == null) {
            pagePane1 = loadPaneFromFXML("Page1.fxml");
            pageMap.put(0, pagePane1);

        }
        // load pane from fxml-definiton
        if (pagePane2 == null) {
            pagePane2 = loadPaneFromFXML("Page2.fxml");
            pageMap.put(1, pagePane2);
        }

    }

    public void initializePageControllers() {
        page1Controller.setPaginationController(this);
        page1Controller.init(root);
        page2Controller.setPaginationController(this);
        page2Controller.init(root);
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
                setPage1Controller(fxmlLoader.getController());
                pageControllerMap.put(0, getPage1Controller());
                break;
            case "Page2.fxml":
                setPage2Controller(fxmlLoader.getController());
                pageControllerMap.put(1, getPage2Controller());
                break;

            default:
                break;
            }

        }
    }

    /**
     * @return the pageMap
     */
    public HashMap<Integer, AnchorPane> getPageMap() {
        return pageMap;
    }

    /**
     * @return the pageControllerMap
     */
    public HashMap<Integer, IPageController> getPageControllerMap() {
        return pageControllerMap;
    }

    /**
     * @return the page1Controller
     */
    public Page1Controller getPage1Controller() {
        return page1Controller;
    }

    /**
     * @param page1Controller
     *            the page1Controller to set
     */
    public void setPage1Controller(Page1Controller page1Controller) {
        this.page1Controller = page1Controller;
    }

    /**
     * @return the page2Controller
     */
    public Page2Controller getPage2Controller() {
        return page2Controller;
    }

    /**
     * @param page2Controller
     *            the page2Controller to set
     */
    public void setPage2Controller(Page2Controller page2Controller) {
        this.page2Controller = page2Controller;
    }

    /**
     * @return the pagePane1
     */
    public AnchorPane getPagePane1() {
        return pagePane1;
    }

    /**
     * @param pagePane1
     *            the pagePane1 to set
     */
    public void setPagePane1(AnchorPane pagePane1) {
        this.pagePane1 = pagePane1;
    }

    /**
     * @return the pagePane2
     */
    public AnchorPane getPagePane2() {
        return pagePane2;
    }

    /**
     * @param pagePane2
     *            the pagePane2 to set
     */
    public void setPagePane2(AnchorPane pagePane2) {
        this.pagePane2 = pagePane2;
    }

    /**
     * @return the root
     */
    public RootLayoutController getRoot() {
        return root;
    }

    /**
     * @param root
     *            the root to set
     */
    public void setRoot(RootLayoutController root) {
        this.root = root;
    }

}
