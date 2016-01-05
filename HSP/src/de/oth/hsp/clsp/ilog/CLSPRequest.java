package de.oth.hsp.clsp.ilog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.oth.hsp.clsp.model.ClspDatFile;

// import de.oth.hsp.clsp.model.ClspDatFile;

public class CLSPRequest {

    private List<Product> products;
    private float epgap;
    private int planningHorizon;
    private int bigNumber;
    private float[][] capacitiesPerResource;

    public CLSPRequest(List<Product> products, float epgap, int planningHorizon, int bigNumber,
            float[][] capacitiesPerResource) {
        super();
        this.products = products;
        this.epgap = epgap;
        this.planningHorizon = planningHorizon;
        this.bigNumber = bigNumber;
        this.capacitiesPerResource = capacitiesPerResource;
    }

    public CLSPRequest(ClspDatFile clspModel) {
        this.epgap = (float) clspModel.getEpgap();
        this.planningHorizon = clspModel.getT();
        this.bigNumber = clspModel.getM();
        this.capacitiesPerResource = clspModel.getBAsFloatArray();

        List<Product> products = new ArrayList<>();
        int[][] demandPerPeriod = clspModel.getDAsIntArray();
        float[][] pieceProcessingTime = clspModel.getTbAsFloatArray();
        float[][] setUpTimePerPeriod = clspModel.getTrAsFloatArray();
        float[] storageCosts = clspModel.getHAsFloatArray();
        float[] setUpCosts = clspModel.getSAsFloatArray();
        int[] initialInventory = clspModel.getY0AsIntArray();
        int[] minLeadTime = clspModel.getZAsIntArray();
        int[] stockAtEndOfProcess = clspModel.getYTAsIntArray();

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
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public float getEpgap() {
        return epgap;
    }

    public int getPlanningHorizon() {
        return planningHorizon;
    }

    public int getBigNumber() {
        return bigNumber;
    }

    public float[][] getCapacitiesPerResource() {
        return capacitiesPerResource;
    }

}
