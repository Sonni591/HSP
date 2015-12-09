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
    @IloLipInputField(name = "d", benoetigt = "K,T")
    private int[][] demandPerPeriod;
    @IloLipInputField(name = "h", benoetigt = "K")
    private float[] storageCosts;
    @IloLipInputField(name = "s", benoetigt = "K")
    private float[] setUpCosts;
    @IloLipInputField(name = "tb", benoetigt = "K")
    private float[] pieceProcessingTime;
    @IloLipInputField(name = "tr", benoetigt = "K")
    private float[] setUpTimePerPeriod;
    @IloLipInputField(name = "C")
    private int capacity;
    @IloLipInputField(name = "E", benoetigt = "T")
    private float[] additionalCapacity;
    @IloLipInputField(name = "p", benoetigt = "K")
    private float[] backorderCostsPerProduct;
    @IloLipInputField(name = "qMax", benoetigt = "K")
    private int[] maxLotSizePerProduct;
    @IloLipInputField(name = "B0", benoetigt = "K")
    private int[] initialBackorders;
    @IloLipInputField(name = "qIn", benoetigt = "K,T")
    private int[][] completeLotsOfEarlierPlanningRuns;
    @IloLipInputField(name = "y0", benoetigt = "K")
    private int[] initialInventory;

    /**
     * Output parameters
     */
    @IloLipOutputField(name = "B")
    private int[][] backorders;
    @IloLipOutputField(name = "q")
    private int[][] lotsPerPeriod;
    @IloLipOutputField(name = "y")
    private int[][] stockAtEndOfPeriod;
    @IloLipOutputField(name = "V")
    private int[][] binaryDecisionVariable;

    /**
     * This constructor creates a new CLSP model
     *
     * @param modelName
     *            The name of the model (usually this will be Modell)
     */
    public CLSPModel(String modelName) {
        super(modelName);
    }

    public CLSPModel(String modelName, List<Product> products, float epgap, int planningHorizon, int capacity,
            float[] additionalCapacity) {
        super(modelName);
        initializeModelVariables(products, epgap, planningHorizon, capacity, additionalCapacity);

    }

    private void initializeModelVariables(List<Product> products, float epgap, int planningHorizon, int capacity,
            float[] additionalCapacity) {
        this.epgap = epgap;
        this.planningHorizon = planningHorizon;
        this.numberOfProducts = products.size();
        this.additionalCapacity = additionalCapacity;
        this.capacity = capacity;

        // Initialize the input fields
        demandPerPeriod = new int[numberOfProducts][planningHorizon];
        completeLotsOfEarlierPlanningRuns = new int[numberOfProducts][planningHorizon];
        for (int prodIndex = 0; prodIndex < numberOfProducts; prodIndex++) {
            for (int period = 0; period < planningHorizon; period++) {
                demandPerPeriod[prodIndex][period] = products.get(prodIndex).getDemandPerPeriod(period);
                completeLotsOfEarlierPlanningRuns[prodIndex][period] = products.get(prodIndex)
                        .getCompleteLotsOfEarlierPlanningRuns(period);
            }
        }

        storageCosts = new float[numberOfProducts];
        setUpCosts = new float[numberOfProducts];
        pieceProcessingTime = new float[numberOfProducts];
        setUpTimePerPeriod = new float[numberOfProducts];
        backorderCostsPerProduct = new float[numberOfProducts];
        maxLotSizePerProduct = new int[numberOfProducts];
        initialBackorders = new int[numberOfProducts];
        initialInventory = new int[numberOfProducts];

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            storageCosts[i] = product.getStorageCosts();
            setUpCosts[i] = product.getSetUpCosts();
            pieceProcessingTime[i] = product.getProcessingTime();
            setUpTimePerPeriod[i] = product.getSetUpTimePerPeriod();
            backorderCostsPerProduct[i] = product.getBackorderCosts();
            maxLotSizePerProduct[i] = product.getMaxLotSize();
            initialBackorders[i] = product.getInitialBackorder();
            initialInventory[i] = product.getInitialInventory();
        }

        // Initialize OutputFields

        this.backorders = new int[numberOfProducts][planningHorizon];
        this.lotsPerPeriod = new int[numberOfProducts][planningHorizon];
        this.stockAtEndOfPeriod = new int[numberOfProducts][planningHorizon];
        this.binaryDecisionVariable = new int[numberOfProducts][planningHorizon];

    }

    /**
     * Getter for the output parameters
     */
    public int[][] getBackorders() {
        return backorders;
    }

    public int[][] getLotsPerPeriod() {
        return lotsPerPeriod;
    }

    public int[][] getStockAtEndOfPeriod() {
        return stockAtEndOfPeriod;
    }

    public int[][] getBinaryDecisionVariable() {
        return binaryDecisionVariable;
    }

}
