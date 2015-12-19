package de.oth.hsp.clsp.ilog;

import de.oth.hsp.common.ilog.exception.ILogSolvingException;
import de.oth.hsp.common.ilog.exception.NotSolvableException;

public class CLSPSolvingAlgorithmFloat implements ICLSPSolvingAlgorithm{

    private CLSPModelFloat model;
    private boolean isSolvable = false;

    private static String getModelName() {
        return "CLSPFloat";
    }

    public CLSPResponse solve(CLSPRequest request) throws NotSolvableException {

        if (request.getBigNumber() == 0) {
            throw new IllegalArgumentException("Big Number ist 0.");
        }
        if (request.getProducts() == null || request.getProducts().size() == 0) {
            throw new IllegalArgumentException("Mindestens ein Produkt muss angelegt werden.");
        }

        model = new CLSPModelFloat(getModelName(), request.getProducts(), request.getEpgap(), request.getPlanningHorizon(),
                request.getBigNumber(), request.getCapacitiesPerResource());
        
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
                throw new ILogSolvingException("Fehler beim Lösen des CLSP-Problems. Die Lösung enthält 'null'-Werte. ");
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

    private CLSPResponse prepareResponse() {
        if (model.getLotsPerPeriod() == null || model.getSetUpVariables() == null || model.getStock() == null) {
            throw new IllegalArgumentException("Das gelöste Modell enthält 'null'-Werte. ");
        }
        
        int[][] setUp = model.getSetUpVariables();
        boolean[][] setUpVariables = new boolean[model.getNumberOfProducts()][model.getPlanningHorizon()];
        for(int i = 0; i < model.getNumberOfProducts(); i++){
        	for(int j = 0; j < model.getPlanningHorizon(); j++){
        		if(setUp[i][j] == 1){
        			setUpVariables[i][j] = true;
        		}else{
        			setUpVariables[i][j] = false;
        		}
        	}
        }
        CLSPResponse response = new CLSPResponse(isSolvable, model.getLotsPerPeriod(), model.getStock(),
        		setUpVariables);

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
