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
}
