package de.oth.hsp.hpplan.ilog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.oth.hsp.common.ilog.ILogRequest;
import de.oth.hsp.hpplan.model.HpplanStatDatFile;

/**
 * This Class is used as a request to the HPPlanStatisch model; it contains the
 * data which is necessary for the computation of the model
 *
 */
public class HPPlanStatischRequest implements ILogRequest {

    private float epgap;
    private List<Product> products;
    private List<Productionsegment> segments;
    private int planningHorizon;
    private int ZMax;
    private float[][][] capacityUtilizationOfProductPerPeriod;

    /**
     * This constructor creates a new HPPlanStatischRequest from the given
     * parameters
     * 
     * @param epgap
     * @param products
     * @param segments
     * @param planningHorizon
     * @param zMax
     * @param capacityUtilizationOfProductPerPeriod
     */
    public HPPlanStatischRequest(float epgap, List<Product> products, List<Productionsegment> segments,
            int planningHorizon, int zMax, float[][][] capacityUtilizationOfProductPerPeriod) {
        super();
        this.epgap = epgap;
        this.products = products;
        this.segments = segments;
        this.planningHorizon = planningHorizon;
        this.ZMax = zMax;
        this.capacityUtilizationOfProductPerPeriod = capacityUtilizationOfProductPerPeriod;
    }

    /**
     * This constructor creates a new HPPlanStatischRequest from the fields of a
     * given HpplanStatDatFile
     * 
     * @param hpplanModel
     */
    public HPPlanStatischRequest(HpplanStatDatFile hpplanModel) {
        this.epgap = (float) hpplanModel.getEpgap();
        this.planningHorizon = hpplanModel.getTAsIntValue();
        this.ZMax = hpplanModel.getzMaxAsIntValue();
        this.capacityUtilizationOfProductPerPeriod = hpplanModel.getFAsFloatArray();

        int[][] demandPerPeriod = hpplanModel.getDAsIntArray();
        int[] initialInventory = hpplanModel.getiInitAsIntArray();
        float[] storageCostsPerPeriod = hpplanModel.getHAsFloatArray();

        List<Product> products = new ArrayList<>();
        for (int numberOfProd = 0; numberOfProd < hpplanModel.getK().intValue(); numberOfProd++) {
            Map<Integer, Integer> demand = new HashMap<>();
            for (int i = 0; i < demandPerPeriod[numberOfProd].length; i++) {
                demand.put(i, demandPerPeriod[numberOfProd][i]);
            }
            Product product = new Product(demand, storageCostsPerPeriod[numberOfProd], initialInventory[numberOfProd]);
            products.add(product);
        }
        this.products = products;

        int[][] productCapacityPerPeriod = hpplanModel.getBAsIntArray();
        int[][] maxAdditionalCapacityPerPeriod = hpplanModel.getuMaxAsIntArray();
        float[][] additionalCapacityCostsPerPeriod = hpplanModel.getUAsFloatArray();

        List<Productionsegment> segments = new ArrayList<>();
        for (int numberOfSeg = 0; numberOfSeg < hpplanModel.getJ().intValue(); numberOfSeg++) {
            Productionsegment segment = new Productionsegment(productCapacityPerPeriod[numberOfSeg],
                    maxAdditionalCapacityPerPeriod[numberOfSeg], additionalCapacityCostsPerPeriod[numberOfSeg]);
            segments.add(segment);
        }

        this.segments = segments;

    }

    public List<Product> getProducts() {
        return products;
    }

    public int getPlanningHorizon() {
        return planningHorizon;
    }

    public int getZMax() {
        return ZMax;
    }

    public float[][][] getCapacityUtilizationOfProductPerPeriod() {
        return capacityUtilizationOfProductPerPeriod;
    }

    public List<Productionsegment> getSegments() {
        return segments;
    }

    public float getEpgap() {
        return epgap;
    }

}
