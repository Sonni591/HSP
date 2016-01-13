package de.oth.hsp.hpplan.ilog;

import java.util.List;

import de.oth.hsp.common.ilog.ILogRequest;
import de.oth.hsp.hpplan.model.HpplanStatDatFile;

public class HPPlanStatischRequest implements ILogRequest {

    private float epgap;
    private List<Product> products;
    private List<Productionsegment> segments;
    private int planningHorizon;
    private int ZMax;
    private float[][][] capacityUtilizationOfProductPerPeriod;

    public HPPlanStatischRequest(float epgap, List<Product> products, List<Productionsegment> segments,
            int planningHorizon, int zMax, float[][][] capacityUtilizationOfProductPerPeriod) {
        super();
        this.epgap = epgap;
        this.products = products;
        this.segments = segments;
        this.planningHorizon = planningHorizon;
        ZMax = zMax;
        this.capacityUtilizationOfProductPerPeriod = capacityUtilizationOfProductPerPeriod;
    }

    public HPPlanStatischRequest(HpplanStatDatFile hpplanModel) {
        // // this.epgap = hpplanModel.g;
        // this.products = products;
        // this.segments = segments;
        // this.planningHorizon = planningHorizon;
        // ZMax = zMax;
        // this.capacityUtilizationOfProductPerPeriod =
        // capacityUtilizationOfProductPerPeriod;
        //
        // List<Product> products = new ArrayList<>();
        // for (int numberOfProd = 0; numberOfProd < 5; numberOfProd++) {
        // Map<Integer, Integer> demand = new HashMap<>();
        // for (int i = 0; i < d[numberOfProd].length; i++) {
        // demand.put(i, d[numberOfProd][i]);
        // }
        // Product product = new Product(demand, h[numberOfProd],
        // Iinit[numberOfProd]);
        // products.add(product);
        // }
        //
        // List<Productionsegment> segments = new ArrayList<>();
        // for (int numberOfSeg = 0; numberOfSeg < 4; numberOfSeg++) {
        // Productionsegment segment = new Productionsegment(b[numberOfSeg],
        // Umax[numberOfSeg], u[numberOfSeg]);
        // segments.add(segment);
        // }
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
