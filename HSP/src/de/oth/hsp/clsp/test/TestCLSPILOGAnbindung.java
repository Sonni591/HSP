package de.oth.hsp.clsp.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.oth.hsp.clsp.ilog.CLSPRequest;
import de.oth.hsp.clsp.ilog.CLSPResponse;
import de.oth.hsp.clsp.ilog.CLSPSolvingAlgorithm;
import de.oth.hsp.clsp.ilog.Product;
import de.oth.hsp.common.ilog.exception.NotSolvableException;

public class TestCLSPILOGAnbindung {

    public static int[][] backorders = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };;
    public static int[][] lotsPerPeriod = { { 110, 0, 200, 0, 0, 130, 0, 190, 0, 0, 150, 0 },
            { 110, 0, 150, 0, 110, 0, 150, 0, 110, 0, 150, 0 }, { 110, 0, 70, 130, 0, 130, 0, 130, 0, 130, 0, 80 },
            { 110, 0, 70, 130, 0, 60, 70, 130, 0, 60, 70, 80 }, { 110, 0, 200, 0, 0, 130, 0, 190, 0, 0, 150, 0 },
            { 110, 0, 70, 130, 0, 130, 0, 130, 0, 130, 0, 80 }, { 110, 0, 70, 130, 0, 130, 0, 130, 0, 130, 0, 80 },
            { 110, 0, 70, 130, 0, 130, 0, 130, 0, 130, 0, 80 } };;
    public static int[][] stockAtEndOfPeriod = { { 0, 60, 0, 130, 50, 0, 70, 0, 110, 60, 0, 80, 0 },
            { 0, 60, 0, 80, 0, 60, 0, 80, 0, 60, 0, 80, 0 }, { 0, 60, 0, 0, 50, 0, 70, 0, 50, 0, 70, 0, 0 },
            { 0, 60, 0, 0, 50, 0, 0, 0, 50, 0, 0, 0, 0 }, { 0, 60, 0, 130, 50, 0, 70, 0, 110, 60, 0, 80, 0 },
            { 0, 60, 0, 0, 50, 0, 70, 0, 50, 0, 70, 0, 0 }, { 0, 60, 0, 0, 50, 0, 70, 0, 50, 0, 70, 0, 0 },
            { 0, 60, 0, 0, 50, 0, 70, 0, 50, 0, 70, 0, 0 } };;
    public static int[][] binaryDecisionVariable = { { 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0 },
            { 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0 }, { 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
            { 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0 },
            { 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1 },
            { 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1 } };;

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

            // lediglich f�r Vergleich der berechneten L�sung mit ilog
            compareWithResults(response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void compareWithResults(CLSPResponse response) {
        boolean allRight = true;
        if (response.isSolvable() == false) {
            System.out.println("Modell konnte nicht berechnet werden");
            allRight = false;
        }
        if (response.getBackorders().length == backorders.length) {
            for (int i = 0; i < backorders.length; i++) {
                boolean eq = Arrays.equals(backorders[i], response.getBackorders()[i]);
                if (eq == false) {
                    System.out.println("Backorder Arrays stimmen nicht �berein");
                    allRight = false;
                }
            }
        } else {
            System.out.println("Backorder Arrays stimmen nicht �berein");
            allRight = false;
        }
        if (response.getLotsPerPeriod().length == lotsPerPeriod.length) {
            for (int i = 0; i < lotsPerPeriod.length; i++) {
                boolean eq = Arrays.equals(lotsPerPeriod[i], response.getLotsPerPeriod()[i]);
                if (eq == false) {
                    System.out.println("lotsPerPeriod Arrays stimmen nicht �berein");
                    allRight = false;
                }
            }
        } else {
            System.out.println("lotsPerPeriod Arrays stimmen nicht �berein");
            allRight = false;
        }
        if (response.getStockAtEndOfPeriod().length == stockAtEndOfPeriod.length) {
            for (int i = 0; i < stockAtEndOfPeriod.length; i++) {
                boolean eq = Arrays.equals(stockAtEndOfPeriod[i], response.getStockAtEndOfPeriod()[i]);
                if (eq == false) {
                    System.out.println("stockAtEndOfPeriod Arrays stimmen nicht �berein");
                    allRight = false;
                }
            }
        } else {
            System.out.println("stockAtEndOfPeriod Arrays stimmen nicht �berein");
            allRight = false;
        }
        if (response.getBinaryDecisionVariable().length == binaryDecisionVariable.length) {
            for (int i = 0; i < binaryDecisionVariable.length; i++) {
                boolean eq = Arrays.equals(binaryDecisionVariable[i], response.getBinaryDecisionVariable()[i]);
                if (eq == false) {
                    System.out.println("binaryDecisionVariable Arrays stimmen nicht �berein");
                    allRight = false;
                }
            }
        } else {
            System.out.println("binaryDecisionVariable Arrays stimmen nicht �berein");
            allRight = false;
        }
        if (allRight == true) {
            System.out.println("Modell wurde richtig berechnet");
        }
    }

    public static void computeCLSPWithParameters() {

        float epgap = 0.0001f;
        int planningHorizon = 12;
        int capacity = 432000;
        float[] additionalCapacity = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

        int[][] demandPerPeriod = { { 50, 60, 70, 80, 50, 60, 70, 80, 50, 60, 70, 80 },
                { 50, 60, 70, 80, 50, 60, 70, 80, 50, 60, 70, 80 }, { 50, 60, 70, 80, 50, 60, 70, 80, 50, 60, 70, 80 },
                { 50, 60, 70, 80, 50, 60, 70, 80, 50, 60, 70, 80 }, { 50, 60, 70, 80, 50, 60, 70, 80, 50, 60, 70, 80 },
                { 50, 60, 70, 80, 50, 60, 70, 80, 50, 60, 70, 80 }, { 50, 60, 70, 80, 50, 60, 70, 80, 50, 60, 70, 80 },
                { 50, 60, 70, 80, 50, 60, 70, 80, 50, 60, 70, 80 } };
        float[] storageCosts = { 1, 1, 1, 1, 1, 1, 1, 1 };
        float[] setUpCosts = { 190, 135, 95, 70, 160, 115, 100, 110 };
        float[] pieceProcessingTime = { 245, 360, 340, 230, 245, 360, 340, 230 };
        float[] setUpTimePerPeriod = { 1250, 880, 620, 480, 1250, 880, 620, 480 };
        float[] backorderCostsPerProduct = { 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000 };
        int[] maxLotSizePerProduct = { 347, 237, 252, 373, 347, 237, 252, 373 };
        int[] initialBackorders = { 0, 0, 0, 0, 0, 0, 0, 0 };
        int[][] completeLotsOfEarlierPlanningRuns = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
        int[] initialInventory = { 0, 0, 0, 0, 0, 0, 0, 0 };

        List<Product> products = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Map<Integer, Integer> demandPerPeriodMap = new HashMap<Integer, Integer>();
            for (int j = 0; j < demandPerPeriod[i].length; j++) {
                demandPerPeriodMap.put(j, demandPerPeriod[i][j]);
            }
            Map<Integer, Integer> completeLotsOfEarlierPlanningRunsMap = new HashMap<Integer, Integer>();
            for (int j = 0; j < completeLotsOfEarlierPlanningRuns[i].length; j++) {
                completeLotsOfEarlierPlanningRunsMap.put(j, completeLotsOfEarlierPlanningRuns[i][j]);
            }
            Product product = new Product(demandPerPeriodMap, storageCosts[i], setUpCosts[i], pieceProcessingTime[i],
                    setUpTimePerPeriod[i], backorderCostsPerProduct[i], maxLotSizePerProduct[i], initialBackorders[i],
                    completeLotsOfEarlierPlanningRunsMap, initialInventory[i]);
            products.add(product);
        }

        try {
            CLSPSolvingAlgorithm alg = new CLSPSolvingAlgorithm();
            CLSPRequest request = new CLSPRequest(products, epgap, planningHorizon, capacity, additionalCapacity);
            CLSPResponse response = alg.solve(request);
            alg.printResult();
            compareWithResults(response);
        } catch (NotSolvableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
