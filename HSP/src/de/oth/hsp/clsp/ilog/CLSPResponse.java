package de.oth.hsp.clsp.ilog;

import de.oth.hsp.common.ilog.ILogResponse;

public class CLSPResponse implements ILogResponse {

    private boolean solvable;
    private Number result;
    private Number[][] lotsPerPeriod;
    private Number[][] stockAtEndOfPeriod;
    private Number[][] setUpVariables;

    public CLSPResponse(boolean solvable, Number[][] lotsPerPeriod, Number[][] stockAtEndOfPeriod,
            Number[][] setUpVariables, Number result) {
        super();
        this.solvable = solvable;
        this.lotsPerPeriod = lotsPerPeriod;
        this.stockAtEndOfPeriod = stockAtEndOfPeriod;
        this.setUpVariables = setUpVariables;
        this.result = result;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public Number[][] getLotsPerPeriodNumberArr() {
        return lotsPerPeriod;
    }

    public Number[][] getStockAtEndOfPeriodNumberArr() {
        return stockAtEndOfPeriod;
    }

    public Number[][] getSetUpVariablesNumberArr() {
        return setUpVariables;
    }

    public Number getResult() {
        return result;
    }

}
