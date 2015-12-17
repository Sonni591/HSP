package de.oth.hsp.hpplan.ilog;

public class HPPlanStatischResponse {

    private boolean solvable;
    private float[][] usedAdditionalCapacityPerPeriod;
    private float[][] lotSizePerPeriod;
    private float[][] stockAtEndOfPeriod;

    public HPPlanStatischResponse(boolean solvable, float[][] usedAdditionalCapacityPerPeriod,
            float[][] lotSizePerPeriod, float[][] stockAtEndOfPeriod) {
        super();
        this.solvable = solvable;
        this.usedAdditionalCapacityPerPeriod = usedAdditionalCapacityPerPeriod;
        this.lotSizePerPeriod = lotSizePerPeriod;
        this.stockAtEndOfPeriod = stockAtEndOfPeriod;
    }

    public boolean isSolvable() {
        return solvable;
    }

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
