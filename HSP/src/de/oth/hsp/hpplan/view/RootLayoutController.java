package de.oth.hsp.hpplan.view;

import java.io.File;

import de.oth.hsp.clsp.model.CLSPModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class RootLayoutController {

    // reference to the BorderPane of the application
    @FXML
    private BorderPane rootBorderPane;

    // References to all Controllers
    @FXML
    private Tab1Controller tab1Controller;
    @FXML
    private Tab2Controller tab2Controller;

    // References all GUI elements
    @FXML
    private Label lblLeftStatus;
    @FXML
    private Label lblZoom;
    @FXML
    private Button btnZoomPlus;
    @FXML
    private Button btnZoomMinus;
    @FXML
    private Menu menuZoom;
    @FXML
    private TabPane tabPane;
    @FXML
    private MenuBar menuBar;

    // References the CLSPModel
    private CLSPModel clspModel = new CLSPModel();

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public RootLayoutController() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        // Init all controller first
        tab1Controller.init(this);
        tab2Controller.init(this);

    }

    /**
     * Calls a method to open and load a file with input values and displays its
     * content in the Tab1. Method is called in the menu bar.
     */
    @FXML
    private void onActionFileOpen() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Datei laden");

        fileChooser.getExtensionFilters().add(new ExtensionFilter("Dat", "*.dat"));

        File selectedDatFile = fileChooser.showOpenDialog(null);

        // TODO load data from loaded file.
    }

    /**
     * Calls a method to save the current data in a file. Method is called in
     * the menu bar.
     */
    @FXML
    private void onActionFileSave() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Datei speichern");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Dat Datei", "*.dat"));

        File selectedFile = fileChooser.showSaveDialog(null);

        // Create the new file. If there is already one, delete it.
        try {
            if (!selectedFile.createNewFile()) {
                if (selectedFile.delete()) {
                    selectedFile.createNewFile();
                } else {
                    throw new Exception(
                            "Die Datei konnte nicht gespeichert werden. Es existiert bereits eine Datei mit diesem Namen, welche sich nicht l�schen l�sst.");
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Fehler beim Speichern der Datei");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }

        // TODO Save data in created file.
    }

    /**
     * Calls a method to run the calculation of the algorithms. Method is called
     * in the menu bar.
     */
    @FXML
    private void onActionCalculate() {
        // TODO 1. ask user if he wants to save the changes
        // 2. ask user, where he wants to save the result
        // 3. call ILog Framework
        System.out.println("Calculate");
    }

    /**
     * Exit the application. Method is called in the menu bar.
     */
    @FXML
    private void onActionFileExit() {
        Platform.exit();
    }

    /**
     * Show a popup with an about information of the application. Method is
     * called in the menu bar.
     */
    @FXML
    private void onActionHelpAbout() {
        System.out.println("About");
    }

    /**
     * Returns a reference of the Tab1Controller
     *
     * @return Tab1Controller
     */
    public Tab1Controller getTab1Controller() {
        return tab1Controller;
    }

    /**
     * Returns a reference of the Tab2Controller
     *
     * @return Tab2Controller
     */
    public Tab2Controller getTab2Controller() {
        return tab2Controller;
    }

    /**
     * @return tabPane
     */
    public TabPane getTabPane() {
        return tabPane;

    }

    /**
     * Method to examine if the selection of a tab from the tab bar has changed.
     */
    @FXML
    private void onTabSelectionChanged() {
        // System.out.println("on tab selection changed");
    }

    public CLSPModel getClspModel() {
        return clspModel;
    }

    public void setClspModel(CLSPModel clspModel) {
        this.clspModel = clspModel;
    }

}
