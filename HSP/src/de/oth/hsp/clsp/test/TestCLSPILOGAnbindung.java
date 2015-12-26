package de.oth.hsp.clsp.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.oth.hsp.clsp.ilog.CLSPRequest;
import de.oth.hsp.clsp.ilog.CLSPResponse;
import de.oth.hsp.clsp.ilog.CLSPSolvingAlgorithmFloat;
import de.oth.hsp.clsp.ilog.CLSPSolvingAlgorithmInt;
import de.oth.hsp.clsp.ilog.ICLSPSolvingAlgorithm;
import de.oth.hsp.clsp.ilog.Product;

public class TestCLSPILOGAnbindung {

    public static void main(String[] args) {

        // Fall 1: Verwendung der .dat-Datei; Darstellung der Ergebnisse als
        // Float
        computeCLSPFloatWithDATFile();

        // Fall 2: Verwenden der Daten aus Eingabe; Darstellung der Ergebnisse
        // als Float
        computeCLSPFloatWithParameters();

        // Fall 1: Verwendung der .dat-Datei; Darstellung der Ergebnisse als Int
        computeCLSPIntWithDATFile();

        // Fall 2: Verwenden der Daten aus Eingabe; Darstellung der Ergebnisse
        // als Int
        computeCLSPIntWithParameters();
    }

    public static void computeCLSPFloatWithDATFile() {

        try {
            // Aufruf des Algoritmus
            ICLSPSolvingAlgorithm alg = new CLSPSolvingAlgorithmFloat();
            CLSPResponse response = alg.solve("CLSP", "src/resources/");
            alg.printResult();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void computeCLSPIntWithDATFile() {

        try {

            // Aufruf des Algoritmus
            ICLSPSolvingAlgorithm alg = new CLSPSolvingAlgorithmInt();
            CLSPResponse response = alg.solve("CLSP", "src/resources/");
            alg.printResult();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void computeCLSPIntWithParameters() {

        float epgap = 0.0001f;
        int planningHorizon = 2;
        int bigNumber = 5768668;
        float[][] capacitiesPerResource = { { 3000, 3000 }, { 3000, 3000 } };
        int[][] demandPerPeriod = { { 20, 30 }, { 20, 50 } };
        float[] storageCosts = { 2, 2 };
        float[] setUpCosts = { 200, 200 };
        float[][] pieceProcessingTime = { { 20, 30 }, { 20, 50 } };
        float[][] setUpTimePerPeriod = { { 20, 30 }, { 20, 50 } };
        int[] minLeadTime = { 0, 0 };
        int[] initialInventory = { 10, 0 };
        int[] stockAtEndOfProcess = { 30, 0 };

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
            ICLSPSolvingAlgorithm alg = new CLSPSolvingAlgorithmInt();
            CLSPRequest request = new CLSPRequest(products, epgap, planningHorizon, bigNumber, capacitiesPerResource);
            CLSPResponse response = alg.solve(request);
            alg.printResult();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void computeCLSPFloatWithParameters() {

        float epgap = 0.0001f;
        int planningHorizon = 2;
        int bigNumber = 5768668;
        float[][] capacitiesPerResource = { { 3000, 3000 }, { 3000, 3000 } };
        int[][] demandPerPeriod = { { 20, 30 }, { 20, 50 } };
        float[] storageCosts = { 2, 2 };
        float[] setUpCosts = { 200, 200 };
        float[][] pieceProcessingTime = { { 20, 30 }, { 20, 50 } };
        float[][] setUpTimePerPeriod = { { 20, 30 }, { 20, 50 } };
        int[] minLeadTime = { 0, 0 };
        int[] initialInventory = { 10, 0 };
        int[] stockAtEndOfProcess = { 30, 0 };

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
            ICLSPSolvingAlgorithm alg = new CLSPSolvingAlgorithmFloat();
            CLSPRequest request = new CLSPRequest(products, epgap, planningHorizon, bigNumber, capacitiesPerResource);
            CLSPResponse response = alg.solve(request);
            alg.printResult();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
