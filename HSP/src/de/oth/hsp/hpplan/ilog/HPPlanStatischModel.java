package de.oth.hsp.hpplan.ilog;

import ilog.lip.framework.IloLipInputField;
import ilog.lip.framework.IloLipModell;
import ilog.lip.framework.IloLipOutputField;

import java.util.List;

/**
 * Used to describe the HPPlan .mod file with the input and output parameters,
 * which are passed to ilog
 *
 */
public class HPPlanStatischModel extends IloLipModell {

    /**
     * Input parameters
     */
    @IloLipInputField(name = "CPLEX_EPGAP")
    private float epgap;
    @IloLipInputField(name = "J")
    private int productionSegments;
    @IloLipInputField(name = "K")
    private int numberOfProducts;
    @IloLipInputField(name = "T")
    private int planningHorizon;
    @IloLipInputField(name = "ZMax")
    private int ZMax;
    @IloLipInputField(name = "b", benoetigt = "productionSegments,planningHorizon")
    private int[][] productCapacityPerPeriod;
    @IloLipInputField(name = "f", benoetigt = "productionSegments,numberOfProducts,ZMax")
    private float[][][] capacityUtilizationOfProductPerPeriod;
    @IloLipInputField(name = "d", benoetigt = "numberOfProducts,planningHorizon")
    private int[][] demandPerPeriod;
    @IloLipInputField(name = "h", benoetigt = "numberOfProducts")
    private float[] storageCostsPerPeriod;
    @IloLipInputField(name = "Umax", benoetigt = "productionSegments,planningHorizon")
    private int[][] maxAdditionalCapacityPerPeriod;
    @IloLipInputField(name = "u", benoetigt = "productionSegments,planningHorizon")
    private float[][] additionalCapacityCostsPerPeriod;
    @IloLipInputField(name = "Iinit", benoetigt = "numberOfProducts")
    private int[] initialInventory;

    /**
     * Output parameters
     */
    @IloLipOutputField(name = "U")
    private float[][] usedAdditionalCapacityPerPeriod;
    @IloLipOutputField(name = "x")
    private float[][] lotSizePerPeriod;
    @IloLipOutputField(name = "I")
    private float[][] stockAtEndOfPeriod;

    /**
     * This constructor creates a new HPPlan model
     *
     * @param modelName
     *            The name of the model (usually this will be Modell)
     */
    public HPPlanStatischModel(String modelName, List<Product> products, List<Productionsegment> segments, float epgap,
            int planningHorizon, int ZMax, float[][][] capacityUtilizationOfProductPerPeriod) {
        super(modelName);
        initializeModelVariables(products, segments, epgap, planningHorizon, ZMax,
                capacityUtilizationOfProductPerPeriod);

    }

    public HPPlanStatischModel(String modelName) {
        super(modelName);
    }

    /**
     * This method sets the input fields of the model to the parameters of the
     * method. It also initializes the output fields
     * 
     * @param products
     * @param segments
     * @param epgap
     * @param planningHorizon
     * @param ZMax
     * @param capacityUtilizationOfProductPerPeriod
     */
    private void initializeModelVariables(List<Product> products, List<Productionsegment> segments, float epgap,
            int planningHorizon, int ZMax, float[][][] capacityUtilizationOfProductPerPeriod) {
        this.epgap = epgap;
        this.productionSegments = segments.size();
        this.numberOfProducts = products.size();
        this.planningHorizon = planningHorizon;
        this.ZMax = ZMax;

        // Initialize the input fields
        this.capacityUtilizationOfProductPerPeriod = capacityUtilizationOfProductPerPeriod;

        this.productCapacityPerPeriod = new int[this.productionSegments][this.planningHorizon];
        this.maxAdditionalCapacityPerPeriod = new int[this.productionSegments][this.planningHorizon];
        this.additionalCapacityCostsPerPeriod = new float[this.productionSegments][this.planningHorizon];

        for (int segIndex = 0; segIndex < this.productionSegments; segIndex++) {
            Productionsegment segment = segments.get(segIndex);
            for (int planIndex = 0; planIndex < this.planningHorizon; planIndex++) {
                this.productCapacityPerPeriod[segIndex][planIndex] = segment.getProductCapacityPerPeriod(planIndex);
                this.maxAdditionalCapacityPerPeriod[segIndex][planIndex] = segment
                        .getMaxAdditionalCapacityPerPeriod(planIndex);
                this.additionalCapacityCostsPerPeriod[segIndex][planIndex] = segment
                        .getAdditionalCapacityCostsPerPeriod(planIndex);
            }
        }

        this.initialInventory = new int[this.numberOfProducts];
        this.storageCostsPerPeriod = new float[this.numberOfProducts];
        this.demandPerPeriod = new int[this.numberOfProducts][this.planningHorizon];
        for (int prodIndex = 0; prodIndex < this.numberOfProducts; prodIndex++) {
            Product product = products.get(prodIndex);
            this.initialInventory[prodIndex] = product.getInitialInventory();
            this.storageCostsPerPeriod[prodIndex] = product.getStorageCosts();
            for (int planIndex = 0; planIndex < this.planningHorizon; planIndex++) {
                this.demandPerPeriod[prodIndex][planIndex] = product.getDemandPerPeriod(planIndex);
            }
        }

        // Initialize OutputFields
        usedAdditionalCapacityPerPeriod = new float[productionSegments][planningHorizon];
        lotSizePerPeriod = new float[numberOfProducts][planningHorizon];
        stockAtEndOfPeriod = new float[numberOfProducts][planningHorizon];
    }

    /**
     * Getter for the output parameters
     */
    public float[][] getUsedAdditionalCapacityPerPeriod() {
        return usedAdditionalCapacityPerPeriod;
    }

    public float[][] getLotSizePerPeriod() {
        return lotSizePerPeriod;
    }

    public float[][] getStockAtEndOfPeriod() {
        return stockAtEndOfPeriod;
    }

}
