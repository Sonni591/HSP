package de.oth.hsp.hpplan.view;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import de.oth.hsp.common.view.IPageController;

public class PaginationController {

    // Pages of the Pagination
    private AnchorPane pagePane1;
    private AnchorPane pagePane2;
    private AnchorPane pagePane3;
    private AnchorPane pagePane4;
    private AnchorPane pagePane5;
    private AnchorPane pagePane6;
    private AnchorPane pagePane7;
    private AnchorPane pagePane8;

    // References to FXML controllers
    private RootLayoutController root;

    private Page1Controller page1Controller;
    private Page2Controller page2Controller;
    private Page3Controller page3Controller;
    private Page4Controller page4Controller;
    private Page5Controller page5Controller;
    private Page6Controller page6Controller;
    private Page7Controller page7Controller;
    private Page8Controller page8Controller;

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
        // load pane from fxml-definiton
        if (pagePane3 == null) {
            pagePane3 = loadPaneFromFXML("Page3.fxml");
            pageMap.put(2, pagePane3);
        }
        // load pane from fxml-definiton
        if (pagePane4 == null) {
            pagePane4 = loadPaneFromFXML("Page4.fxml");
            pageMap.put(3, pagePane4);
        }
        // load pane from fxml-definiton
        if (pagePane5 == null) {
            pagePane5 = loadPaneFromFXML("Page5.fxml");
            pageMap.put(4, pagePane5);
        }
        // load pane from fxml-definiton
        if (pagePane6 == null) {
            pagePane6 = loadPaneFromFXML("Page6.fxml");
            pageMap.put(5, pagePane6);
        }
        // load pane from fxml-definiton
        if (pagePane7 == null) {
            pagePane7 = loadPaneFromFXML("Page7.fxml");
            pageMap.put(6, pagePane7);
        }
        // load pane from fxml-definiton
        if (pagePane8 == null) {
            pagePane8 = loadPaneFromFXML("Page8.fxml");
            pageMap.put(7, pagePane8);
        }

    }

    public void initializePageControllers() {
        page1Controller.setPaginationController(this);
        page1Controller.init(root);
        page2Controller.setPaginationController(this);
        page2Controller.init(root);
        page3Controller.setPaginationController(this);
        page3Controller.init(root);
        page4Controller.setPaginationController(this);
        page4Controller.init(root);
        page4Controller.setPaginationController(this);
        page4Controller.init(root);
        page5Controller.setPaginationController(this);
        page5Controller.init(root);
        page6Controller.setPaginationController(this);
        page6Controller.init(root);
        page7Controller.setPaginationController(this);
        page7Controller.init(root);
        page8Controller.setPaginationController(this);
        page8Controller.init(root);
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
            case "Page3.fxml":
                setPage3Controller(fxmlLoader.getController());
                pageControllerMap.put(2, getPage3Controller());
                break;
            case "Page4.fxml":
                setPage4Controller(fxmlLoader.getController());
                pageControllerMap.put(3, getPage4Controller());
                break;
            case "Page5.fxml":
                setPage5Controller(fxmlLoader.getController());
                pageControllerMap.put(4, getPage5Controller());
                break;
            case "Page6.fxml":
                setPage6Controller(fxmlLoader.getController());
                pageControllerMap.put(5, getPage6Controller());
                break;
            case "Page7.fxml":
                setPage7Controller(fxmlLoader.getController());
                pageControllerMap.put(6, getPage7Controller());
                break;
            case "Page8.fxml":
                setPage8Controller(fxmlLoader.getController());
                pageControllerMap.put(7, getPage8Controller());
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

    /**
     * @return the pagePane1
     */
    public AnchorPane getPagePane1() {
        return pagePane1;
    }

    /**
     * @return the pagePane2
     */
    public AnchorPane getPagePane2() {
        return pagePane2;
    }

    /**
     * @return the pagePane3
     */
    public AnchorPane getPagePane3() {
        return pagePane3;
    }

    /**
     * @return the pagePane4
     */
    public AnchorPane getPagePane4() {
        return pagePane4;
    }

    /**
     * @return the pagePane5
     */
    public AnchorPane getPagePane5() {
        return pagePane5;
    }

    /**
     * @return the pagePane6
     */
    public AnchorPane getPagePane6() {
        return pagePane6;
    }

    /**
     * @return the pagePane7
     */
    public AnchorPane getPagePane7() {
        return pagePane7;
    }

    /**
     * @return the pagePane8
     */
    public AnchorPane getPagePane8() {
        return pagePane8;
    }

    /**
     * @return the page1Controller
     */
    public Page1Controller getPage1Controller() {
        return page1Controller;
    }

    /**
     * @return the page2Controller
     */
    public Page2Controller getPage2Controller() {
        return page2Controller;
    }

    /**
     * @return the page3Controller
     */
    public Page3Controller getPage3Controller() {
        return page3Controller;
    }

    /**
     * @return the page4Controller
     */
    public Page4Controller getPage4Controller() {
        return page4Controller;
    }

    /**
     * @return the page5Controller
     */
    public Page5Controller getPage5Controller() {
        return page5Controller;
    }

    /**
     * @return the page6Controller
     */
    public Page6Controller getPage6Controller() {
        return page6Controller;
    }

    /**
     * @return the page7Controller
     */
    public Page7Controller getPage7Controller() {
        return page7Controller;
    }

    /**
     * @return the page8Controller
     */
    public Page8Controller getPage8Controller() {
        return page8Controller;
    }

    /**
     * @param page1Controller
     *            the page1Controller to set
     */
    public void setPage1Controller(Page1Controller page1Controller) {
        this.page1Controller = page1Controller;
    }

    /**
     * @param page2Controller
     *            the page2Controller to set
     */
    public void setPage2Controller(Page2Controller page2Controller) {
        this.page2Controller = page2Controller;
    }

    /**
     * @param page3Controller
     *            the page3Controller to set
     */
    public void setPage3Controller(Page3Controller page3Controller) {
        this.page3Controller = page3Controller;
    }

    /**
     * @param page4Controller
     *            the page4Controller to set
     */
    public void setPage4Controller(Page4Controller page4Controller) {
        this.page4Controller = page4Controller;
    }

    /**
     * @param page5Controller
     *            the page5Controller to set
     */
    public void setPage5Controller(Page5Controller page5Controller) {
        this.page5Controller = page5Controller;
    }

    /**
     * @param page6Controller
     *            the page6Controller to set
     */
    public void setPage6Controller(Page6Controller page6Controller) {
        this.page6Controller = page6Controller;
    }

    /**
     * @param page7Controller
     *            the page8Controller to set
     */
    public void setPage7Controller(Page7Controller page7Controller) {
        this.page7Controller = page7Controller;
    }

    /**
     * @param page8Controller
     *            the page8Controller to set
     */
    public void setPage8Controller(Page8Controller page8Controller) {
        this.page8Controller = page8Controller;
    }

}
