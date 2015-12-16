package de.oth.hsp.clsp.ilog;

import de.oth.hsp.common.ilog.exception.ILogSolvingException;
import de.oth.hsp.common.ilog.exception.NotSolvableException;

public class CLSPSolvingAlgorithm {

    private CLSPModel model;
    private boolean isSolvable = false;

    private static String getModelName() {
        return "ModellCLSP";
    }

    public CLSPResponse solve(CLSPRequest request) throws NotSolvableException {

        if (request.getBigNumber() == 0) {
            throw new IllegalArgumentException("Big Number ist 0.");
        }
        if (request.getEpgap() == 0) {
            throw new IllegalArgumentException("EPGAP ist 0.");
        }
        if (request.getPlanningHorizon() == 0) {
            throw new IllegalArgumentException("Planungshorizont ist 0.");
        }
        if (request.getCapacitiesPerResource() == null || request.getCapacitiesPerResource().length == 0) {
            throw new IllegalArgumentException("Kapazität pro Resource muss angelegt werden.");
        }
        if (request.getProducts() == null || request.getProducts().size() == 0) {
            throw new IllegalArgumentException("Mindestens ein Produkt muss angelegt werden.");
        }

        model = new CLSPModel(getModelName(), request.getProducts(), request.getEpgap(), request.getPlanningHorizon(),
                request.getBigNumber(), request.getCapacitiesPerResource());

        // Try to solve the model
        try {

            isSolvable = model.solve();
            System.out.println("[INFO] Solved: " + isSolvable);

            // If the problem is not solvable, throw an exception
            if (isSolvable) {
                model.printResult();
            } else {
                throw new NotSolvableException();
            }

            if (model.getLotsPerPeriod() == null || model.getSetUpVariables() == null || model.getStock() == null) {
                throw new ILogSolvingException("Fehler beim Lösen des PLSP-Problems. Die Lösung enthält 'null'-Werte. ");
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

    public CLSPResponse solve(String pathToDatFile, String pathToModFile) throws NotSolvableException {

        model = new CLSPModel(getModelName());
        try {
            isSolvable = model.solve(pathToModFile, pathToDatFile);

        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
            throw new NotSolvableException();
        }

        // Build the response object
        CLSPResponse response = prepareResponse();

        return response;

    }

    private CLSPResponse prepareResponse() {
        if (model.getLotsPerPeriod() == null || model.getSetUpVariables() == null || model.getStock() == null) {
            throw new IllegalArgumentException("Das gelöste Modell enthält 'null'-Werte. ");
        }
        CLSPResponse response = new CLSPResponse(isSolvable, model.getLotsPerPeriod(), model.getStock(),
                model.getSetUpVariables());

        return response;
    }

    public void printResult() {
        if (model.getLotsPerPeriod() == null || model.getSetUpVariables() == null || model.getStock() == null) {
            throw new IllegalArgumentException("Das gelöste Modell enthält 'null'-Werte. ");
        }
        try {
            model.printResult();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Das gelöste Modell enthält 'null'-Werte. ");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Das gelöste Modell enthält 'null'-Werte. ");
        } catch (Exception e) {
            throw new IllegalArgumentException("Das gelöste Modell enthält 'null'-Werte. ");
        }
    }
}
