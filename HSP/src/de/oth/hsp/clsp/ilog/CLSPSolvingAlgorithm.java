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

        if (request.getCapacity() == 0) {
            throw new IllegalArgumentException("Periodenanzahl ist 0.");
        }
        if (request.getEpgap() == 0) {
            throw new IllegalArgumentException("EPGAP ist 0.");
        }
        if (request.getPlanningHorizon() == 0) {
            throw new IllegalArgumentException("Planungshorizont ist 0.");
        }
        if (request.getAdditionalCapacity().length == 0) {
            throw new IllegalArgumentException("Zusatzkapazit�t muss angelegt werden.");
        }
        if (request.getProducts() == null || request.getProducts().size() == 0) {
            throw new IllegalArgumentException("Mindestens ein Produkt muss angelegt werden.");
        }

        model = new CLSPModel(getModelName(), request.getProducts(), request.getEpgap(), request.getPlanningHorizon(),
                request.getCapacity(), request.getAdditionalCapacity());

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

            if (model.getLotsPerPeriod() == null || model.getBackorders() == null
                    || model.getStockAtEndOfPeriod() == null || model.getBinaryDecisionVariable() == null) {
                throw new ILogSolvingException("Fehler beim L�sen des PLSP-Problems. Die L�sung enth�lt 'null'-Werte. ");
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
        if (model.getBackorders() == null || model.getBinaryDecisionVariable() == null
                || model.getStockAtEndOfPeriod() == null || model.getLotsPerPeriod() == null) {
            throw new IllegalArgumentException("Das gel�ste Modell enth�lt 'null'-Werte. ");
        }

        // List<Product> products = request.getProducts();
        // for(int i =0; i< products.size(); i++){
        // Product product = products.get(i);
        // product.setBackorders(model.getBackorders()[i]);
        // product.setBinaryDecisionVariable(model.getBinaryDecisionVariable()[i]);
        // product.setStockAtEndOfPeriod(model.getStockAtEndOfPeriod()[i]);
        // product.setLotsPerPeriod(model.getLotsPerPeriod()[i]);
        // }
        CLSPResponse response = new CLSPResponse(isSolvable, model.getBackorders(), model.getLotsPerPeriod(),
                model.getStockAtEndOfPeriod(), model.getBinaryDecisionVariable());

        return response;
    }

    public void printResult() {
        if (model == null || model.getLotsPerPeriod() == null || model.getBackorders() == null
                || model.getStockAtEndOfPeriod() == null || model.getBinaryDecisionVariable() == null) {
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
