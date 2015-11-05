package de.oth.hsp.common.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

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
	private Menu menuEdit;
	@FXML
	private Menu menuZoom;
	@FXML
	private TabPane tabPane;
	@FXML
	private MenuBar menuBar;

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
		System.out.println("File open");
	}

	/**
	 * Calls a method to open and save the current data in a file. Method is
	 * called in the menu bar.
	 */
	@FXML
	private void onActionFileSave() {
		System.out.println("File save");
	}

	/**
	 * Calls a method to run the calculation of the algorithms. Method is called
	 * in the menu bar.
	 */
	@FXML
	private void onActionCalculate() {
		System.out.println("Calculate");
	}

	/**
	 * Calls a method to import and show the available test data. Method is
	 * called in the menu bar.
	 */
	@FXML
	private void onActionTestData() {
		System.out.println("Test Data");
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
     * When that is the case (1) the "edit"-Menu is enabled/disabled; (2) the
     * status lable in the south-left corner is changed and also the content of
     * the explanation component is updated
     */
    @FXML
    private void onTabSelectionChanged() {
    	System.out.println("on tab selection changed");
    }

	

}
