package de.oth.hsp.hpplan.ilog;

import de.oth.hsp.common.ilog.ILogSolvingAlgorithm;
import de.oth.hsp.common.ilog.exception.ILogSolvingException;
import de.oth.hsp.common.ilog.exception.NotSolvableException;

/**
 * This class is used to request the computation of a model from ilog.
 *
 */
public class HPPlanStatischSolvingAlgorithm implements
        ILogSolvingAlgorithm<HPPlanStatischRequest, HPPlanStatischResponse> {

    private HPPlanStatischModel model;
    private boolean isSolvable = false;

    private static String getModelName() {
        return "HPPLAN-Statisch";
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.oth.hsp.common.ilog.ILogSolvingAlgorithm#solve(de.oth.hsp.common.ilog
     * .ILogRequest)
     */
    @Override
    public HPPlanStatischResponse solve(HPPlanStatischRequest request) throws NotSolvableException {

        if (request.getEpgap() == 0) {
            throw new IllegalArgumentException("CPLEX_EPGAP ist 0.");
        }
        if (request.getPlanningHorizon() == 0) {
            throw new IllegalArgumentException("Planungshorizont ist 0.");
        }
        if (request.getSegments() == null || request.getSegments().size() == 0) {
            throw new IllegalArgumentException("Produktionssegmente m�ssen angelegt werden.");
        }
        if (request.getCapacityUtilizationOfProductPerPeriod() == null
                || request.getCapacityUtilizationOfProductPerPeriod().length == 0) {
            throw new IllegalArgumentException("Kapazit�tsauslastung muss angelegt werden.");
        }
        if (request.getZMax() == 0) {
            throw new IllegalArgumentException("ZMax sind 0.");
        }
        if (request.getProducts() == null || request.getProducts().size() == 0) {
            throw new IllegalArgumentException("Mindestens ein Produkt muss angelegt werden.");
        }

        model = new HPPlanStatischModel(getModelName(), request.getProducts(), request.getSegments(),
                request.getEpgap(), request.getPlanningHorizon(), request.getZMax(),
                request.getCapacityUtilizationOfProductPerPeriod());

        // Try to solve the model
        try {

            isSolvable = model.solve();
            model.printResult();
            System.out.println("[INFO] Solved: " + isSolvable);

            // If the problem is not solvable, throw an exception
            if (!isSolvable) {
                throw new NotSolvableException();
            }

            if (model.getLotSizePerPeriod() == null || model.getStockAtEndOfPeriod() == null
                    || model.getUsedAdditionalCapacityPerPeriod() == null) {
                throw new ILogSolvingException(
                        "Fehler beim L�sen des HPPlan-Problems. Die L�sung enth�lt 'null'-Werte. ");
            }

            // Build the response object
            HPPlanStatischResponse response = prepareResponse();

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
     * @see de.oth.hsp.common.ilog.ILogSolvingAlgorithm#solve(java.lang.String,
     * java.lang.String)
     */
    @Override
    public HPPlanStatischResponse solve(String pathToDatFile, String pathToDatDir) throws NotSolvableException {

        try {
            model = new HPPlanStatischModel(getModelName());
            model.setUseDatFile(true);
            isSolvable = model.solve(getModelName(), pathToDatFile, pathToDatDir);
            model.printResult();

        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            throw new NotSolvableException();
        }

        // Build the response object
        HPPlanStatischResponse response = prepareResponse();

        return response;

    }

    /**
     * This method creates a new HPPlanStatischResponse and initializes it with
     * the output values from ilog
     * 
     * @return returns a HPPlanStatischResponse containing the output values of
     *         the ilog computation
     */
    private HPPlanStatischResponse prepareResponse() {
        if (model.getLotSizePerPeriod() == null || model.getStockAtEndOfPeriod() == null
                || model.getUsedAdditionalCapacityPerPeriod() == null) {
            throw new IllegalArgumentException("Das gel�ste Modell enth�lt 'null'-Werte. ");
        }

        HPPlanStatischResponse response = new HPPlanStatischResponse(isSolvable,
                model.getUsedAdditionalCapacityPerPeriod(), model.getLotSizePerPeriod(), model.getStockAtEndOfPeriod(),
                model.getResult());

        return response;
    }

    /**
     * This method prints the results of the ilog computation
     */
    public void printResult() {
        if (model.getLotSizePerPeriod() == null || model.getStockAtEndOfPeriod() == null
                || model.getUsedAdditionalCapacityPerPeriod() == null) {
            throw new IllegalArgumentException("Das gel�ste Modell enth�lt 'null'-Werte. ");
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

    /*
     * (non-Javadoc)
     * 
     * @see de.oth.hsp.common.ilog.ILogSolvingAlgorithm#solve(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public HPPlanStatischResponse solve(String pathToDatFile, String pathToDatDir, String pathExcelExport)
            throws NotSolvableException {
        try {
            model = new HPPlanStatischModel(getModelName());
            model.setUseDatFile(true);
            isSolvable = model.solve(getModelName(), pathToDatFile, pathToDatDir);
            model.exportExcel(pathExcelExport);
            model.printResult();

        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            throw new NotSolvableException();
        }

        // Build the response object
        HPPlanStatischResponse response = prepareResponse();

        return response;

    }

}
