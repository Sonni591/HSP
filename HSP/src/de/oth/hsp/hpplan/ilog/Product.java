package de.oth.hsp.hpplan.ilog;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains the values of a product which are necessary for the
 * computation with ilog
 *
 */
public class Product {

    private Map<Integer, Integer> demandPerPeriod = new HashMap<Integer, Integer>();
    private float storageCosts;
    private int initialInventory;

    public Product(Map<Integer, Integer> demandPerPeriod, float storageCosts, int initialInventory) {
        super();
        this.demandPerPeriod = demandPerPeriod;
        this.storageCosts = storageCosts;
        this.initialInventory = initialInventory;
    }

    public int getDemandPerPeriod(int key) {
        return demandPerPeriod.get(key);
    }

    public float getStorageCosts() {
        return storageCosts;
    }

    public int getInitialInventory() {
        return initialInventory;
    }
}
