package de.oth.hsp.clsp.ilog;

import de.oth.hsp.common.ilog.exception.ILogSolvingException;
import de.oth.hsp.common.ilog.exception.NotSolvableException;
import de.oth.hsp.common.utils.ArrayConverter;

/**
 * This class is used to request the computation of a model from ilog; the
 * output fields are of the type int, corresponding to the underlaying model
 *
 */
public class CLSPSolvingAlgorithmInt implements ICLSPSolvingAlgorithm {

    private CLSPModelInt model;
    private boolean isSolvable = false;

    private static String getModelName() {
        return "CLSPInt";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.oth.hsp.clsp.ilog.ICLSPSolvingAlgorithm#solve(de.oth.hsp.clsp.ilog
     * .CLSPRequest)
     */
    @Override
    public CLSPResponse solve(CLSPRequest request) throws NotSolvableException {

        if (request.getBigNumber() == 0) {
            throw new IllegalArgumentException("Big Number ist 0.");
        }
        if (request.getProducts() == null || request.getProducts().size() == 0) {
            throw new IllegalArgumentException("Mindestens ein Produkt muss angelegt werden.");
        }

        model = new CLSPModelInt(getModelName(), request.getProducts(), request.getEpgap(),
                request.getPlanningHorizon(), request.getBigNumber(), request.getCapacitiesPerResource());

        // Try to solve the model
        try {
            model.setUseDatFile(false);
            isSolvable = model.solve();
            System.out.println("[INFO] Solved: " + isSolvable);

            // If the problem is not solvable, throw an exception
            if (!isSolvable) {
                throw new NotSolvableException();
            }

            if (model.getLotsPerPeriod() == null || model.getSetUpVariables() == null || model.getStock() == null) {
                throw new ILogSolvingException("Fehler beim L�sen des CLSP-Problems. Die L�sung enth�lt 'null'-Werte. ");
            }

            // Build the response object
            CLSPResponse response = prepareResponse();

            return response;

        }

        // solve() method of ILOG-Framework throws an exception;
        catch (Exception e) {

            System.out.println("[ERROR] " + e.getMessage());
            throw new NotSolvableException();

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.oth.hsp.clsp.ilog.ICLSPSolvingAlgorithm#solve(java.lang.String,
     * java.lang.String)
     */
    @Override
    public CLSPResponse solve(String pathToDatFile, String pathToDatDir) throws NotSolvableException {

        model = new CLSPModelInt(getModelName());
        try {
            model.setUseDatFile(true);
            isSolvable = model.solve(getModelName(), pathToDatFile, pathToDatDir);
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            throw new NotSolvableException();
        }

        // Build the response object
        CLSPResponse response = prepareResponse();

        return response;

    }

    /*
     * (non-Javadoc)
     * 
     * @see de.oth.hsp.clsp.ilog.ICLSPSolvingAlgorithm#solve(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public CLSPResponse solve(String pathToDatFile, String pathToDatDir, String pathExcelExport)
            throws NotSolvableException {

        model = new CLSPModelInt(getModelName());
        try {
            model.setUseDatFile(true);
            isSolvable = model.solve(getModelName(), pathToDatFile, pathToDatDir);
            model.exportExcel(pathExcelExport);

        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            throw new NotSolvableException();
        }

        // Build the response object
        CLSPResponse response = prepareResponse();

        return response;
    }

    /**
     * This method creates a new CLSPResponse and initializes it with the output
     * values from ilog
     * 
     * @return returns a CLSPResponse containing the output values of the ilog
     *         computation
     */
    private CLSPResponse prepareResponse() {
        if (model.getLotsPerPeriod() == null || model.getSetUpVariables() == null || model.getStock() == null) {
            throw new IllegalArgumentException("Das gelöste Modell enthält 'null'-Werte. ");
        }

        Number[][] lotsPerPeriodAsNumber = ArrayConverter.intArrayToNumberArray(model.getLotsPerPeriod());
        Number[][] stock = ArrayConverter.intArrayToNumberArray(model.getStock());
        Number[][] setUpVariables = ArrayConverter.intArrayToNumberArray(model.getSetUpVariables());

        CLSPResponse response = new CLSPResponse(isSolvable, lotsPerPeriodAsNumber, stock, setUpVariables,
                model.getResult());

        return response;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.oth.hsp.clsp.ilog.ICLSPSolvingAlgorithm#printResult()
     */
    @Override
    public void printResult() {
        if (model.getLotsPerPeriod() == null || model.getSetUpVariables() == null || model.getStock() == null) {
            throw new IllegalArgumentException("Das gelöste Modell enth�lt 'null'-Werte. ");
        }
        try {
            model.printResult();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Das gel�ste Modell enth�lt 'null'-Werte. ");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Das gel�ste Modell enth�lt 'null'-Werte. ");
        } catch (Exception e) {
            throw new IllegalArgumentException("Das gel�ste Modell enth�lt 'null'-Werte. ");
        }
    }
}
