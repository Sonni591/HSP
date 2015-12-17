package de.oth.hsp.clsp.ilog;

import ilog.lip.framework.IloLipInputField;
import ilog.lip.framework.IloLipModell;
import ilog.lip.framework.IloLipOutputField;

import java.util.List;

public class CLSPModel extends IloLipModell {

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
    @IloLipInputField(name = "b", benoetigt = "J,T")
    private float[][] capacitiesPerResource;
    @IloLipInputField(name = "d", benoetigt = "K,T")
    private int[][] demandPerPeriod;
    @IloLipInputField(name = "h", benoetigt = "K")
    private float[] storageCosts;
    @IloLipInputField(name = "s", benoetigt = "K")
    private float[] setUpCosts;
    @IloLipInputField(name = "tb", benoetigt = "K,J")
    private float[][] pieceProcessingTime;
    @IloLipInputField(name = "tr", benoetigt = "K,J")
    private float[][] setUpTimePerPeriod;
    @IloLipInputField(name = "z", benoetigt = "K")
    private int[] minLeadTime;
    @IloLipInputField(name = "y0", benoetigt = "K")
    private int[] initialInventory;
    @IloLipInputField(name = "yT", benoetigt = "K")
    private int[] stockAtEndOfProcess;

    /**
     * Output parameters
     */
    @IloLipOutputField(name = "q")
    private float[][] lotsPerPeriod;
    @IloLipOutputField(name = "y")
    private float[][] stock;
    @IloLipOutputField(name = "r")
    private boolean[][] setUpVariables;

    /**
     * This constructor creates a new CLSP model
     *
     * @param modelName
     *            The name of the model (usually this will be Modell)
     */
    public CLSPModel(String modelName) {
        super(modelName);
    }

    public CLSPModel(String modelName, List<Product> products, float epgap, int planningHorizon, int bigNumber,
            float[][] capacitiesPerResource) {
        super(modelName);
        initializeModelVariables(products, epgap, planningHorizon, bigNumber, capacitiesPerResource);

    }

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

        // Initialize OutputFields
        this.lotsPerPeriod = new float[numberOfProducts][planningHorizon];
        this.stock = new float[numberOfProducts][planningHorizon + 1];
        this.setUpVariables = new boolean[numberOfProducts][planningHorizon];

    }

    /**
     * Getter for the output parameters
     */
    public float[][] getLotsPerPeriod() {
        return lotsPerPeriod;
    }

    public float[][] getStock() {
        return stock;
    }

    public boolean[][] getSetUpVariables() {
        return setUpVariables;
    }

}
