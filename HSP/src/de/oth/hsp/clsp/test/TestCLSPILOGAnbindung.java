package de.oth.hsp.clsp.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.oth.hsp.clsp.ilog.CLSPRequest;
import de.oth.hsp.clsp.ilog.CLSPResponse;
import de.oth.hsp.clsp.ilog.CLSPSolvingAlgorithm;
import de.oth.hsp.clsp.ilog.Product;
import de.oth.hsp.common.ilog.exception.NotSolvableException;

public class TestCLSPILOGAnbindung {

    public static void main(String[] args) {

        // Fall 1: Verwendung der .dat-Datei
        // computeCLSPWithDATFile();

        // Fall 2: Verwenden der Daten aus Eingabe
        computeCLSPWithParameters();

    }

    public static void computeCLSPWithDATFile() {

        try {

            // Aufruf des Algoritmus
            CLSPSolvingAlgorithm alg = new CLSPSolvingAlgorithm();
            CLSPResponse response = alg.solve("ParameterCLSP", "ModellCLSP");
            alg.printResult();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void computeCLSPWithParameters() {

        float epgap = 0.0001f;
        int planningHorizon = 2;
        int bigNumber = 5768668;
        float[][] capacitiesPerResource = { { 20, 30 }, { 20, 50 } };
        int[][] demandPerPeriod = { { 20, 30 }, { 20, 50 } };
        float[] storageCosts = { 2, 2 };
        float[] setUpCosts = { 200, 200 };
        float[][] pieceProcessingTime = { { 20, 30 }, { 20, 50 } };
        float[][] setUpTimePerPeriod = { { 20, 30 }, { 20, 50 } };
        int[] minLeadTime = { 0, 0 };
        int[] initialInventory = { 0, 0 };
        int[] stockAtEndOfProcess = { 99, 99 };

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Map<Integer, Integer> demandPerPeriodMap = new HashMap<Integer, Integer>();
            for (int j = 0; j < demandPerPeriod[i].length; j++) {
                demandPerPeriodMap.put(j, demandPerPeriod[i][j]);
            }
            Map<Integer, Float> pieceProcessingTimeMap = new HashMap<Integer, Float>();
            for (int j = 0; j < pieceProcessingTime[i].length; j++) {
                pieceProcessingTimeMap.put(j, pieceProcessingTime[i][j]);
            }
            Map<Integer, Float> setUpTimePerPeriodMap = new HashMap<Integer, Float>();
            for (int j = 0; j < setUpTimePerPeriod[i].length; j++) {
                setUpTimePerPeriodMap.put(j, setUpTimePerPeriod[i][j]);
            }

            Product product = new Product(demandPerPeriodMap, storageCosts[i], setUpCosts[i], pieceProcessingTimeMap,
                    setUpTimePerPeriodMap, initialInventory[i], minLeadTime[i], stockAtEndOfProcess[i]);
            products.add(product);
        }

        try {
            CLSPSolvingAlgorithm alg = new CLSPSolvingAlgorithm();
            CLSPRequest request = new CLSPRequest(products, epgap, planningHorizon, bigNumber, capacitiesPerResource);
            CLSPResponse response = alg.solve(request);
            alg.printResult();
        } catch (NotSolvableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
