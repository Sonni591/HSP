package de.oth.hsp.clsp.ilog;

public class CLSPResponse {

    private boolean solvable;
    private int[][] backorders;
    private int[][] lotsPerPeriod;
    private int[][] stockAtEndOfPeriod;
    private int[][] binaryDecisionVariable;

    // private List<Product> products;

    public CLSPResponse(boolean solvable, int[][] backorders, int[][] lotsPerPeriod, int[][] stockAtEndOfPeriod,
            int[][] binaryDecisionVariable) {
        super();
        this.solvable = solvable;
        // this.products = products;
        this.backorders = backorders;
        this.lotsPerPeriod = lotsPerPeriod;
        this.stockAtEndOfPeriod = stockAtEndOfPeriod;
        this.binaryDecisionVariable = binaryDecisionVariable;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public int[][] getBackorders() {
        return backorders;
    }

    public int[][] getLotsPerPeriod() {
        return lotsPerPeriod;
    }

    public int[][] getStockAtEndOfPeriod() {
        return stockAtEndOfPeriod;
    }

    public int[][] getBinaryDecisionVariable() {
        return binaryDecisionVariable;
    }

    public Number[][] getBackordersNumberArr() {
        Number[][] arr = new Number[backorders.length][backorders[1].length];
        for (int i = 0; i < backorders.length; i++) {
            for (int j = 0; j < backorders[i].length; j++) {
                arr[i][j] = backorders[i][j];
            }
        }
        return arr;
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

    public Number[][] getBinaryDecisionVariableNumberArr() {
        Number[][] arr = new Number[binaryDecisionVariable.length][binaryDecisionVariable[1].length];
        for (int i = 0; i < binaryDecisionVariable.length; i++) {
            for (int j = 0; j < binaryDecisionVariable[i].length; j++) {
                arr[i][j] = binaryDecisionVariable[i][j];
            }
        }
        return arr;
    }
}
