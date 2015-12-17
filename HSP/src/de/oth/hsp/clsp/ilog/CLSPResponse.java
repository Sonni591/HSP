package de.oth.hsp.clsp.ilog;

public class CLSPResponse {

    private boolean solvable;
    private float[][] lotsPerPeriod;
    private float[][] stockAtEndOfPeriod;
    private boolean[][] setUpVariables;

    public CLSPResponse(boolean solvable, float[][] lotsPerPeriod, float[][] stockAtEndOfPeriod,
            boolean[][] setUpVariables) {
        super();
        this.solvable = solvable;
        // this.products = products;

        this.lotsPerPeriod = lotsPerPeriod;
        this.stockAtEndOfPeriod = stockAtEndOfPeriod;
        this.setUpVariables = setUpVariables;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public float[][] getLotsPerPeriod() {
        return lotsPerPeriod;
    }

    public float[][] getStockAtEndOfPeriod() {
        return stockAtEndOfPeriod;
    }

    public boolean[][] getSetUpVariables() {
        return setUpVariables;
    }

    public Number[][] getLotsPerPeriodNumberArr() {
        Number[][] arr = new Number[lotsPerPeriod.length][lotsPerPeriod[1].length];
        for (int i = 0; i < lotsPerPeriod.length; i++) {
            for (int j = 0; j < lotsPerPeriod[i].length; j++) {
                arr[i][j] = lotsPerPeriod[i][j];
            }
        }
        return arr;
    }

    public Number[][] getStockAtEndOfPeriodNumberArr() {
        Number[][] arr = new Number[stockAtEndOfPeriod.length][stockAtEndOfPeriod[1].length];
        for (int i = 0; i < stockAtEndOfPeriod.length; i++) {
            for (int j = 0; j < stockAtEndOfPeriod[i].length; j++) {
                arr[i][j] = stockAtEndOfPeriod[i][j];
            }
        }
        return arr;
    }
}
