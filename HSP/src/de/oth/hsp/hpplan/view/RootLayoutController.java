package de.oth.hsp.hpplan.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import de.oth.hsp.common.dat.DatFileParser;
import de.oth.hsp.common.dat.parser.DatParseException;
import de.oth.hsp.common.ilog.exception.NotSolvableException;
import de.oth.hsp.common.io.WorkspaceManager;
import de.oth.hsp.hpplan.ilog.HPPlanStatischRequest;
import de.oth.hsp.hpplan.ilog.HPPlanStatischResponse;
import de.oth.hsp.hpplan.ilog.HPPlanStatischSolvingAlgorithm;
import de.oth.hsp.hpplan.model.HpplanStatDatFile;

/**
 * class does define the root layout controller of the application
 */
public class RootLayoutController {

    /**
     * reference to the BorderPane of the application
     */
    @FXML
    private BorderPane rootBorderPane;

    /**
     * References to all Controllers
     */
    @FXML
    private Tab1Controller tab1Controller;
    @FXML
    private Tab2Controller tab2Controller;

    /**
     * References all GUI elements
     */
    @FXML
    private TabPane tabPane;
    @FXML
    private MenuBar menuBar;

    /**
     * References the HPPLANModel
     */
    private HpplanStatDatFile hpplanModel = new HpplanStatDatFile();

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
        try {
            File selectedFile;
            if ((selectedFile = openFileChooserDialog()) != null) {
                hpplanModel = loadDataFromFile(selectedFile);

                // update choicebox for 3-dim array
                getTab1Controller().getPaginationController().getPage3Controller().initChoiceBox();

                // call inevent of current tab to show the actual data from the
                // model
                int curPIndex = tab1Controller.getPagination().getCurrentPageIndex();
                getTab1Controller().getPaginationController().getPageControllerMap().get(curPIndex).inEvent();
            }
        } catch (DatParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the data from selechtedFile to the GUI
     *
     * @param selectedFile
     * @throws DatParseException
     */
    private HpplanStatDatFile loadDataFromFile(File selectedFile) throws DatParseException {
        return DatFileParser.parseHpplanStat(selectedFile.toPath());
    }

    /**
     * Calls a method to save the current data in a file. Method is called in
     * the menu bar. Opens a FileChooser.
     */
    @FXML
    private void onActionFileSave() {
        saveDataToFile(createNewFile(openSaveDialog()));

    }

    /**
     * Saves the data from the GUI to the selectedFile.
     *
     * @param selectedFile
     */
    private void saveDataToFile(File selectedFile) {
        if (selectedFile != null && selectedFile.exists()) {
            try {
                FileWriter fw = new FileWriter(selectedFile);
                int curPIndex = tab1Controller.getPagination().getCurrentPageIndex();
                // call out Event of current page to save the data from the GUI
                // to the hpplanModel
                if (tab1Controller.getPaginationController().getPageControllerMap().get(curPIndex).checkInput()) {
                    tab1Controller.getPaginationController().getPageControllerMap().get(curPIndex).outEvent();
                    fw.write(hpplanModel.toString());
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Opens a SaveDifialog for dat files.
     *
     * @return selected File
     */
    private File openSaveDialog() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Datei speichern");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Dat Datei", "*.dat"));

        // set the last used path or the default path, or none if there is no
        // defaultPath
        if (WorkspaceManager.getWorkspace() != null) {
            fileChooser.setInitialDirectory(WorkspaceManager.getWorkspace().toFile());
        }

        File choosenFile = fileChooser.showSaveDialog(null);
        // set the path to the WorkspaceManager
        if (choosenFile != null) {
            Path workspace = choosenFile.toPath().getParent();
            WorkspaceManager.setWorkspace(workspace);
        }
        return choosenFile;

    }

    /**
     * Opens a FileChooser Dialog for dat files.
     *
     * @return selected File
     */
    private File openFileChooserDialog() {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Datei speichern");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Dat Datei", "*.dat"));

        // set the last used path or the default path, or none if there is no
        // defaultPath
        if (WorkspaceManager.getWorkspace() != null) {
            fileChooser.setInitialDirectory(WorkspaceManager.getWorkspace().toFile());
        }
        File choosenFile = fileChooser.showOpenDialog(null);
        // set the path to the WorkspaceManager
        if (choosenFile != null) {
            Path workspace = choosenFile.toPath().getParent();
            WorkspaceManager.setWorkspace(workspace);
        }
        return choosenFile;
    }

    /**
     * Create the new file. If there is already one, it will be deleted.
     *
     * @param selectedFile
     */
    private File createNewFile(File selectedFile) {
        File file = null;
        try {
            if (selectedFile != null && !selectedFile.createNewFile()) {
                if (selectedFile.delete() && selectedFile.createNewFile()) {
                    file = selectedFile;
                } else {
                    throw new Exception(
                            "Die Datei konnte nicht gespeichert werden. Es existiert bereits eine Datei mit diesem Namen, welche sich nicht löschen lasst.");
                }
            }
            file = selectedFile;
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Fehler beim Speichern der Datei");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
        return file;
    }

    /**
     * Calls a method to run the calculation of the algorithms. Method is called
     * in the menu bar.
     */
    @FXML
    private void onActionCalculate() {
        calculateHPPLAN();
    }

    /**
     * @param clspResponse
     *            triggers a method to show the results of the algorithm in the
     *            tab 2
     */
    private void showResult(HPPlanStatischResponse hpplanResponse) {
        tab2Controller.setResultData(hpplanResponse);
    }

    /**
     * calculates and shows the result in tab2
     *
     * @return
     * @throws NotSolvableException
     */
    public void calculateHPPLAN() {

        rootBorderPane.setCursor(Cursor.WAIT);

        // ensure correct data and save them to clspModel
        int curPIndex = tab1Controller.getPagination().getCurrentPageIndex();
        // call out Event of current page to save the data from the GUI
        // to the hpplanModel
        if (tab1Controller.getPaginationController().getPageControllerMap().get(curPIndex).checkInput()) {
            tab1Controller.getPaginationController().getPageControllerMap().get(curPIndex).outEvent();
        }

        HPPlanStatischSolvingAlgorithm alg = new HPPlanStatischSolvingAlgorithm();
        HPPlanStatischRequest request = new HPPlanStatischRequest(hpplanModel);

        try {
            // response.
            showResult(alg.solve(request));
        } catch (NotSolvableException e) {
            showAlertAlgorithmNotSolvable(e);
        }

        rootBorderPane.setCursor(Cursor.DEFAULT);

    }

    /**
     * Shows an error dialog, that indicates that the algorithm isn't solvable
     *
     * @param e
     */
    private void showAlertAlgorithmNotSolvable(NotSolvableException e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(e.getTitle());
        alert.setHeaderText("Nicht lösbar!");
        alert.setContentText(e.getText());
        alert.showAndWait();
    }

    /**
     * Switch to the tab using the index
     */
    public void tabPaneSwitchToTab(int index) {
        tabPane.getSelectionModel().select(index);
    }

    /**
     * show a dialog with the batch processing options
     */
    @FXML
    private void onActionBatchProcessing() {
        BatchProcessingDialog dia = new BatchProcessingDialog(this);
        dia.showAndWait();
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
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("OTH Regensburg\nLabor Informationstechnik und Produktionslogistik\nWintersemester 2015/16");
        alert.setContentText("Arnold Christiane\nButz Thomas\nDenzin Timo\nEichinger Tobias\nGais Dominik\nLiebich Johannes\nSchertler Sascha\nSonnleitner Daniel\nWagner Pilar");
        alert.showAndWait();
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

    public HpplanStatDatFile getHpplanModel() {
        return hpplanModel;
    }

    public void setHpplanModel(HpplanStatDatFile hpplanModel) {
        this.hpplanModel = hpplanModel;
    }

}
