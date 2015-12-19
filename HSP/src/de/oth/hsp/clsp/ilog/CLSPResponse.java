package de.oth.hsp.clsp.ilog;

public class CLSPResponse {

    private boolean solvable;
    private float[][] lotsPerPeriodFloat;
    private float[][] stockAtEndOfPeriodFloat;
    private int[][] lotsPerPeriodInt;
    private int[][] stockAtEndOfPeriodInt;
    private boolean[][] setUpVariables;

    public CLSPResponse(boolean solvable, float[][] lotsPerPeriod, float[][] stockAtEndOfPeriod,
            boolean[][] setUpVariables) {
        super();
        this.solvable = solvable;

        this.lotsPerPeriodFloat = lotsPerPeriod;
        this.stockAtEndOfPeriodFloat = stockAtEndOfPeriod;
        this.setUpVariables = setUpVariables;
    }
    public CLSPResponse(boolean solvable, int[][] lotsPerPeriod, int[][] stockAtEndOfPeriod,
            boolean[][] setUpVariables) {
        super();
        this.solvable = solvable;

        this.lotsPerPeriodInt = lotsPerPeriod;
        this.lotsPerPeriodInt = stockAtEndOfPeriod;
        this.setUpVariables = setUpVariables;
    }

    public boolean isSolvable() {
        return solvable;
    }

	public boolean[][] getSetUpVariables() {
        return setUpVariables;
    }

    public Number[][] getLotsPerPeriodNumberArr() {
    	Number[][] arr;
    	if(stockAtEndOfPeriodFloat != null && lotsPerPeriodFloat != null){
	        arr = new Number[lotsPerPeriodFloat.length][lotsPerPeriodFloat[1].length];
	        for (int i = 0; i < lotsPerPeriodFloat.length; i++) {
	            for (int j = 0; j < lotsPerPeriodFloat[i].length; j++) {
	                arr[i][j] = lotsPerPeriodFloat[i][j];
	            }
	        }
    	}
        else{
        	arr = new Number[lotsPerPeriodInt.length][lotsPerPeriodInt[1].length];
	        for (int i = 0; i < lotsPerPeriodInt.length; i++) {
	            for (int j = 0; j < lotsPerPeriodInt[i].length; j++) {
	                arr[i][j] = lotsPerPeriodInt[i][j];
	            }
	        }
        }
        return arr;
    }

    public Number[][] getStockAtEndOfPeriodNumberArr() {
    	Number[][] arr;
    	if(stockAtEndOfPeriodFloat != null && lotsPerPeriodFloat != null){
    		arr = new Number[stockAtEndOfPeriodFloat.length][stockAtEndOfPeriodFloat[1].length];
	        for (int i = 0; i < stockAtEndOfPeriodFloat.length; i++) {
	            for (int j = 0; j < stockAtEndOfPeriodFloat[i].length; j++) {
	                arr[i][j] = stockAtEndOfPeriodFloat[i][j];
	            }
	        }
    	}
    	else{
    		arr = new Number[stockAtEndOfPeriodInt.length][stockAtEndOfPeriodInt[1].length];
	        for (int i = 0; i < stockAtEndOfPeriodInt.length; i++) {
	            for (int j = 0; j < stockAtEndOfPeriodInt[i].length; j++) {
	                arr[i][j] = stockAtEndOfPeriodInt[i][j];
	            }
	        }
    	}	
        return arr;
    }
}
