package de.oth.hsp.clsp.ilog;

import java.util.List;

import de.oth.hsp.clsp.model.ClspDatFile;

// import de.oth.hsp.clsp.model.ClspDatFile;

public class CLSPRequest {

    private List<Product> products;
    private float epgap;
    private int planningHorizon;
    private int bigNumber;
    private float[][] capacitiesPerResource;

    public CLSPRequest(List<Product> products, float epgap, int planningHorizon, int bigNumber,
            float[][] capacitiesPerResource) {
        super();
        this.products = products;
        this.epgap = epgap;
        this.planningHorizon = planningHorizon;
        this.bigNumber = bigNumber;
        this.capacitiesPerResource = capacitiesPerResource;
    }

    public CLSPRequest(ClspDatFile clspModel) {
        this.epgap = (float) clspModel.getEpgap();
        this.planningHorizon = clspModel.getT();
        this.bigNumber = clspModel.getM();
        // TODO: restliche Werte setzen!
    }

    public List<Product> getProducts() {
        return products;
    }

    public float getEpgap() {
        return epgap;
    }

    public int getPlanningHorizon() {
        return planningHorizon;
    }

    public int getBigNumber() {
        return bigNumber;
    }

    public float[][] getCapacitiesPerResource() {
        return capacitiesPerResource;
    }

}
