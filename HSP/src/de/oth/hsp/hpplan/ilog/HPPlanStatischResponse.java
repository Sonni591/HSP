package de.oth.hsp.hpplan.ilog;

import de.oth.hsp.common.ilog.ILogResponse;

/**
 * This class is used as a container for the output values of ilog and whether
 * the model was solvable or not
 *
 */
public class HPPlanStatischResponse implements ILogResponse {

    private boolean solvable;
    private float[][] usedAdditionalCapacityPerPeriod;
    private float[][] lotSizePerPeriod;
    private float[][] stockAtEndOfPeriod;
    private Number result;

    /**
     * 
     * This method creates a new HPPlanStatischResponse from the given parameter
     * 
     * @param solvable
     * @param usedAdditionalCapacityPerPeriod
     * @param lotSizePerPeriod
     * @param stockAtEndOfPeriod
     * @param result
     */
    public HPPlanStatischResponse(boolean solvable, float[][] usedAdditionalCapacityPerPeriod,
            float[][] lotSizePerPeriod, float[][] stockAtEndOfPeriod, Number result) {
        super();
        this.solvable = solvable;
        this.usedAdditionalCapacityPerPeriod = usedAdditionalCapacityPerPeriod;
        this.lotSizePerPeriod = lotSizePerPeriod;
        this.stockAtEndOfPeriod = stockAtEndOfPeriod;
        this.result = result;
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

    public Number[][] getUsedAdditionalCapacityPerPeriodNumberArr() {
        Number[][] arr = null;
        if (usedAdditionalCapacityPerPeriod != null) {
            arr = new Number[usedAdditionalCapacityPerPeriod.length][usedAdditionalCapacityPerPeriod[1].length];
            for (int i = 0; i < usedAdditionalCapacityPerPeriod.length; i++) {
                for (int j = 0; j < usedAdditionalCapacityPerPeriod[i].length; j++) {
                    arr[i][j] = usedAdditionalCapacityPerPeriod[i][j];
                }
            }
        }
        return arr;
    }

    public Number[][] getLotSizePerPeriodNumberArr() {
        Number[][] arr = null;
        if (lotSizePerPeriod != null) {
            arr = new Number[lotSizePerPeriod.length][lotSizePerPeriod[1].length];
            for (int i = 0; i < lotSizePerPeriod.length; i++) {
                for (int j = 0; j < lotSizePerPeriod[i].length; j++) {
                    arr[i][j] = lotSizePerPeriod[i][j];
                }
            }
        }
        return arr;
    }

    public Number[][] getStockAtEndOfPeriodNumberArr() {
        Number[][] arr = null;
        if (stockAtEndOfPeriod != null) {
            arr = new Number[stockAtEndOfPeriod.length][stockAtEndOfPeriod[1].length];
            for (int i = 0; i < stockAtEndOfPeriod.length; i++) {
                for (int j = 0; j < stockAtEndOfPeriod[i].length; j++) {
                    arr[i][j] = stockAtEndOfPeriod[i][j];
                }
            }
        }
        return arr;
    }

    public Number getResult() {
        return result;
    }

}
