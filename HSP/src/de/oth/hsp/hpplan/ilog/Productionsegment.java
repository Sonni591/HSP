package de.oth.hsp.hpplan.ilog;

public class Productionsegment {

    private int[] productCapacityPerPeriod;
    private int[] maxAdditionalCapacityPerPeriod;
    private float[] additionalCapacityCostsPerPeriod;

    public Productionsegment(int[] productCapacityPerPeriod, int[] maxAdditionalCapacityPerPeriod,
            float[] additionalCapacityCostsPerPeriod) {
        this.productCapacityPerPeriod = productCapacityPerPeriod;
        this.maxAdditionalCapacityPerPeriod = maxAdditionalCapacityPerPeriod;
        this.additionalCapacityCostsPerPeriod = additionalCapacityCostsPerPeriod;
    }

    public int getProductCapacityPerPeriod(int index) {
        return productCapacityPerPeriod[index];
    }

    public int getMaxAdditionalCapacityPerPeriod(int index) {
        return maxAdditionalCapacityPerPeriod[index];
    }

    public float getAdditionalCapacityCostsPerPeriod(int index) {
        return additionalCapacityCostsPerPeriod[index];
    }
}
