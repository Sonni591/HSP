package de.oth.hsp.clsp.ilog;

import de.oth.hsp.common.ilog.exception.ILogSolvingException;
import de.oth.hsp.common.ilog.exception.NotSolvableException;

public class CLSPSolvingAlgorithmFloat implements ICLSPSolvingAlgorithm {

    private CLSPModelFloat model;
    private boolean isSolvable = false;

    private static String getModelName() {
        return "CLSPFloat";
    }

    @Override
    public CLSPResponse solve(CLSPRequest request) throws NotSolvableException {

        if (request.getBigNumber() == 0) {
            throw new IllegalArgumentException("Big Number ist 0.");
        }
        if (request.getProducts() == null || request.getProducts().size() == 0) {
            throw new IllegalArgumentException("Mindestens ein Produkt muss angelegt werden.");
        }

        model = new CLSPModelFloat(getModelName(), request.getProducts(), request.getEpgap(),
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

    @Override
    public CLSPResponse solve(String pathToDatFile, String pathToDatDir) throws NotSolvableException {

        model = new CLSPModelFloat(getModelName());
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

    @Override
    public CLSPResponse solve(String pathToDatFile, String pathToDatDir, String pathExcelExport)
            throws NotSolvableException {

        model = new CLSPModelFloat(getModelName());
        try {
            model.setUseDatFile(true);
            isSolvable = model.solve(getModelName(), pathToDatFile, pathToDatDir);
            model.setAllowExcelExport(true);
            model.exportExcel(pathExcelExport);

        } catch (Exception e) {
            e.printStackTrace();
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

        Number[][] lotsPerPeriodAsNumber = floatArrayToNumberArray(model.getLotsPerPeriod());
        Number[][] stock = floatArrayToNumberArray(model.getStock());
        Number[][] setUpVariables = intArrayToNumberArray(model.getSetUpVariables());

        CLSPResponse response = new CLSPResponse(isSolvable, lotsPerPeriodAsNumber, stock, setUpVariables,
                model.getResult());

        return response;
    }

    private Number[][] floatArrayToNumberArray(float[][] floatArray) {
        Number[][] numberArray = new Number[floatArray.length][floatArray[1].length];
        for (int i = 0; i < floatArray.length; i++) {
            for (int j = 0; j < floatArray[i].length; j++) {
                numberArray[i][j] = floatArray[i][j];
            }
        }
        return numberArray;
    }

    private Number[][] intArrayToNumberArray(int[][] intArray) {
        Number[][] numberArray = new Number[intArray.length][intArray[1].length];
        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < intArray[0].length; j++) {
                numberArray[i][j] = intArray[i][j];
            }
        }
        return numberArray;
    }

    @Override
    public void printResult() {
        if (model.getLotsPerPeriod() == null || model.getSetUpVariables() == null || model.getStock() == null) {
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
