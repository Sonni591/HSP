package de.oth.hsp.hpplan.ilog;

import java.util.List;

import de.oth.hsp.hpplan.model.HpplanStatDatFile;

public class HPPlanStatischRequest {

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
        // TODO Auto-generated constructor stub
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
