package de.oth.hsp.clsp.ilog;

import java.util.HashMap;
import java.util.Map;

public class Product {

    private Map<Integer, Integer> demandPerPeriod = new HashMap<Integer, Integer>();
    private Map<Integer, Float> pieceProcessingTime = new HashMap<Integer, Float>();
    private Map<Integer, Float> setUpTimePerPeriod = new HashMap<Integer, Float>();
    private float storageCosts;
    private float setUpCosts;
    private int minLeadTime;
    private int stockAtEndOfProcess;
    private int initialInventory;

    public Product(Map<Integer, Integer> demandPerPeriod, float storageCosts, float setUpCosts,
            Map<Integer, Float> pieceProcessingTime, Map<Integer, Float> setUpTimePerPeriod, int initialInventory,
            int minLeadTime, int stockAtEndOfProcess) {
        super();
        this.demandPerPeriod = demandPerPeriod;
        this.storageCosts = storageCosts;
        this.setUpCosts = setUpCosts;
        this.pieceProcessingTime = pieceProcessingTime;
        this.setUpTimePerPeriod = setUpTimePerPeriod;
        this.initialInventory = initialInventory;
        this.minLeadTime = minLeadTime;
        this.stockAtEndOfProcess = stockAtEndOfProcess;
    }

    public int getMinLeadTime() {
        return minLeadTime;
    }

    public int getStockAtEndOfProcess() {
        return stockAtEndOfProcess;
    }

    public int getDemandPerPeriod(int key) {
        return demandPerPeriod.get(key);
    }

    public float getPieceProcessingTime(int key) {
        return pieceProcessingTime.get(key);
    }

    public float getSetUpTimePerPeriod(int key) {
        return setUpTimePerPeriod.get(key);
    }

    public float getStorageCosts() {
        return storageCosts;
    }

    public float getSetUpCosts() {
        return setUpCosts;
    }

    public int getInitialInventory() {
        return initialInventory;
    }

}
