package de.oth.hsp.hpplan.view;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import de.oth.hsp.common.view.AbstractTableViewPage;
import de.oth.hsp.common.view.IPageController;

/**
 * Class for a page containing variables for the lot scheduling problem
 */
public class Page8Controller extends AbstractTableViewPage implements IPageController {

    private PaginationController paginationController;
    private RootLayoutController root;

    @FXML
    private TextField epgap; // CPLEX_EPGAP - relative Optimalitätslücke
    @FXML
    private Button calculateButton;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public Page8Controller() {

    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
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

    /**
     * @see de.oth.hsp.common.view.IPageController#outEvent()
     */
    @Override
    public void outEvent() {

        // save the given epgap number into the model by converting the German
        // number format into a double

        // to have correct format: replace dots with commas
        epgap.setText(epgap.getText().replaceAll("\\.", ","));

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(epgap.getText()).useLocale(Locale.GERMAN);
        double epgapHelp = scanner.nextDouble();

        // double epgapHelp = Double.parseDouble(epgap.getText());
        root.getHpplanModel().setEpgap(epgapHelp);

    }

    /**
     * @see de.oth.hsp.common.view.IPageController#checkInput()
     */
    @Override
    public boolean checkInput() {
        return true;
    }

    /**
     * @see de.oth.hsp.common.view.IPageController#inEvent()
     */
    @Override
    public void inEvent() {

        // display the epgap variable in the German number format

        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.GERMAN));
        df.setMaximumFractionDigits(340); // 340=
                                          // DecimalFormat.DOUBLE_FRACTION_DIGITS

        epgap.setText(df.format(root.getHpplanModel().getEpgap()));

    }

    /**
     * trigger the calculation of the CLSP algorithm
     */
    public void onActionCalculate() {

        outEvent(); // write all Data from this page into the clspModel

        // System.out.println(root.getHpplanModel());

        // start calculation
        root.calculateHPPLAN();
    }

}
