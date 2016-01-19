package de.oth.hsp.clsp.ilog;

import de.oth.hsp.common.ilog.ILogResponse;

/**
 * This class is used as a container for the output values of ilog and whether
 * the model was solvable or not
 *
 */
public class CLSPResponse implements ILogResponse {

    private boolean solvable;
    private Number result;
    private Number[][] lotsPerPeriod;
    private Number[][] stockAtEndOfPeriod;
    private Number[][] setUpVariables;

    /**
     * This method creates a new CLSPResponse from the given parameter
     * 
     * @param solvable
     *            true if the problem was solvable; false if not
     * @param lotsPerPeriod
     * @param stockAtEndOfPeriod
     * @param setUpVariables
     * @param result
     *            the result of the computation of ilog
     */
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
