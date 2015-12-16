package de.oth.hsp.hpplan.ilog;

import de.oth.hsp.common.ilog.exception.ILogSolvingException;
import de.oth.hsp.common.ilog.exception.NotSolvableException;

public class HPPlanStatischSolvingAlgorithm {

    private HPPlanStatischModel model;
    private boolean isSolvable = false;

    private static String getModelName() {
        return "HPPLAN-Statisch";
    }

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

    public HPPlanStatischResponse solve(String pathToDatFile) throws NotSolvableException {

        try {
            model = new HPPlanStatischModel(getModelName());
            model.setUseDatFile(true);
            isSolvable = model.solve(getModelName(), pathToDatFile);

        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            throw new NotSolvableException();
        }

        // Build the response object
        HPPlanStatischResponse response = prepareResponse();

        return response;

    }

    private HPPlanStatischResponse prepareResponse() {
        if (model.getLotSizePerPeriod() == null || model.getStockAtEndOfPeriod() == null
                || model.getUsedAdditionalCapacityPerPeriod() == null) {
            throw new IllegalArgumentException("Das gel�ste Modell enth�lt 'null'-Werte. ");
        }

        HPPlanStatischResponse response = new HPPlanStatischResponse(isSolvable,
                model.getUsedAdditionalCapacityPerPeriod(), model.getLotSizePerPeriod(), model.getStockAtEndOfPeriod());

        return response;
    }

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

}
