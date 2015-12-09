package de.oth.hsp.clsp.ilog;

import java.util.List;

public class CLSPRequest {

    private List<Product> products;
    private float epgap;
    private int planningHorizon;
    private int capacity;
    private float[] additionalCapacity;

    public CLSPRequest(List<Product> products, float epgap, int planningHorizon, int capacity,
            float[] additionalCapacity) {
        super();
        this.products = products;
        this.epgap = epgap;
        this.planningHorizon = planningHorizon;
        this.capacity = capacity;
        this.additionalCapacity = additionalCapacity;
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

    public int getCapacity() {
        return capacity;
    }

    public float[] getAdditionalCapacity() {
        return additionalCapacity;
    }
}
