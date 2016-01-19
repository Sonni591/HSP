package de.oth.hsp.clsp.ilog;

import ilog.lip.framework.IloLipInputField;
import ilog.lip.framework.IloLipModell;
import ilog.lip.framework.IloLipOutputField;

import java.util.List;

/**
 * Used to describe the CLSP .mod file with the input and output parameters,
 * which are passed to ilog The output parameters are of the type float
 *
 */
public class CLSPModelFloat extends IloLipModell {

    /**
     * Input parameters
     */
    @IloLipInputField(name = "CPLEX_EPGAP")
    private float epgap;
    @IloLipInputField(name = "T")
    private int planningHorizon;
    @IloLipInputField(name = "K")
    private int numberOfProducts;
    @IloLipInputField(name = "M")
    private int bigNumber;
    @IloLipInputField(name = "J")
    private int numberOfResources;
    @IloLipInputField(name = "b", benoetigt = "numberOfResources,planningHorizon")
    private float[][] capacitiesPerResource;
    @IloLipInputField(name = "d", benoetigt = "numberOfProducts,planningHorizon")
    private int[][] demandPerPeriod;
    @IloLipInputField(name = "h", benoetigt = "numberOfProducts")
    private float[] storageCosts;
    @IloLipInputField(name = "s", benoetigt = "numberOfProducts")
    private float[] setUpCosts;
    @IloLipInputField(name = "tb", benoetigt = "numberOfProducts,numberOfResources")
    private float[][] pieceProcessingTime;
    @IloLipInputField(name = "tr", benoetigt = "numberOfProducts,numberOfResources")
    private float[][] setUpTimePerPeriod;
    @IloLipInputField(name = "z", benoetigt = "numberOfProducts")
    private int[] minLeadTime;
    @IloLipInputField(name = "y0", benoetigt = "numberOfProducts")
    private int[] initialInventory;
    @IloLipInputField(name = "yT", benoetigt = "numberOfProducts")
    private int[] stockAtEndOfProcess;

    /**
     * Output parameters
     */
    @IloLipOutputField(name = "q")
    private float[][] lotsPerPeriod;
    @IloLipOutputField(name = "y")
    private float[][] stock;
    @IloLipOutputField(name = "r")
    private int[][] setUpVariables;

    /**
     * This constructor creates a new CLSP model
     *
     * @param modelName
     *            The name of the model (usually this will be Modell)
     */
    public CLSPModelFloat(String modelName) {
        super(modelName);
        initializeOutputFields();
    }

    /**
     * This constructor creates a new CLSP model
     * 
     * @param modelName
     *            The name of the model (usually this will be Modell)
     * @param products
     * @param epgap
     * @param planningHorizon
     * @param bigNumber
     * @param capacitiesPerResource
     */
    public CLSPModelFloat(String modelName, List<Product> products, float epgap, int planningHorizon, int bigNumber,
            float[][] capacitiesPerResource) {
        super(modelName);
        initializeModelVariables(products, epgap, planningHorizon, bigNumber, capacitiesPerResource);

    }

    /**
     * 
     * This method sets the input fields of the model to the parameters of the
     * method it also initializes the output fields
     * 
     * @param products
     * @param epgap
     * @param planningHorizon
     * @param bigNumber
     * @param capacitiesPerResource
     */
    private void initializeModelVariables(List<Product> products, float epgap, int planningHorizon, int bigNumber,
            float[][] capacitiesPerResource) {
        this.epgap = epgap;
        this.planningHorizon = planningHorizon;
        this.bigNumber = bigNumber;
        this.numberOfProducts = products.size();
        this.numberOfResources = capacitiesPerResource.length;

        // Initialize the input fields
        this.capacitiesPerResource = capacitiesPerResource;

        demandPerPeriod = new int[numberOfProducts][planningHorizon];
        pieceProcessingTime = new float[numberOfProducts][numberOfResources];
        setUpTimePerPeriod = new float[numberOfProducts][numberOfResources];
        for (int prodIndex = 0; prodIndex < numberOfProducts; prodIndex++) {
            for (int period = 0; period < planningHorizon; period++) {
                demandPerPeriod[prodIndex][period] = products.get(prodIndex).getDemandPerPeriod(period);
            }
            for (int resIndex = 0; resIndex < numberOfResources; resIndex++) {
                pieceProcessingTime[prodIndex][resIndex] = products.get(prodIndex).getPieceProcessingTime(resIndex);
                setUpTimePerPeriod[prodIndex][resIndex] = products.get(prodIndex).getSetUpTimePerPeriod(resIndex);
            }
        }

        storageCosts = new float[numberOfProducts];
        setUpCosts = new float[numberOfProducts];
        initialInventory = new int[numberOfProducts];
        stockAtEndOfProcess = new int[numberOfProducts];
        minLeadTime = new int[numberOfProducts];

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            storageCosts[i] = product.getStorageCosts();
            setUpCosts[i] = product.getSetUpCosts();
            initialInventory[i] = product.getInitialInventory();
            stockAtEndOfProcess[i] = product.getStockAtEndOfProcess();
            minLeadTime[i] = product.getMinLeadTime();
        }

        initializeOutputFields();

    }

    /**
     * This method initializes the output fields The output fields must be
     * correctly initialized; otherwise ilog cannot fill in the values
     * 
     */
    private void initializeOutputFields() {
        // Initialize OutputFields
        this.lotsPerPeriod = new float[numberOfProducts][planningHorizon];
        this.stock = new float[numberOfProducts][planningHorizon + 1];
        this.setUpVariables = new int[numberOfProducts][planningHorizon];
    }

    /**
     * Getter for the output parameters
     */
    public int[][] getSetUpVariables() {
        return setUpVariables;
    }

    public float[][] getLotsPerPeriod() {
        return lotsPerPeriod;
    }

    public float[][] getStock() {
        return stock;
    }

    /**
     * Getter for numberOfProducts and planningHorizon
     */

    public int getPlanningHorizon() {
        return planningHorizon;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }
}
