package de.oth.hsp.clsp.ilog;

import java.util.HashMap;
import java.util.Map;

public class Product {
	
	private Map<Integer, Integer> demandPerPeriod = new HashMap<Integer, Integer>();
	private Map<Integer, Integer> completeLotsOfEarlierPlanningRuns = new HashMap<Integer, Integer>();
	private float storageCosts;
	private float setUpCosts;
	private float processingTime;
	private float setUpTimePerPeriod;
	private float backorderCosts;
	private int maxLotSize;
	private int initialBackorder;
	private int initialInventory;
	
	public Product(Map<Integer, Integer> demandPerPeriod, float storageCosts, float setUpCosts,
			float processingTime, float setUpTimePerPeriod,
			float backorderCosts, int maxLotSize, int initialBackorder,
			Map<Integer, Integer> completeLotsOfEarlierPlanningRuns, int initialInventory) {
		super();
		this.demandPerPeriod = demandPerPeriod;
		this.storageCosts = storageCosts;
		this.setUpCosts = setUpCosts;
		this.processingTime = processingTime;
		this.setUpTimePerPeriod = setUpTimePerPeriod;
		this.backorderCosts = backorderCosts;
		this.maxLotSize = maxLotSize;
		this.initialBackorder = initialBackorder;
		this.completeLotsOfEarlierPlanningRuns = completeLotsOfEarlierPlanningRuns;
		this.initialInventory = initialInventory;
	}
	
	public int getDemandPerPeriod(int period) {
		return demandPerPeriod.get(period);
	}
	public float getStorageCosts() {
		return storageCosts;
	}
	public float getSetUpCosts() {
		return setUpCosts;
	}
	public float getProcessingTime() {
		return processingTime;
	}
	public float getSetUpTimePerPeriod() {
		return setUpTimePerPeriod;
	}
	public float getBackorderCosts() {
		return backorderCosts;
	}
	public int getMaxLotSize() {
		return maxLotSize;
	}
	public int getInitialBackorder() {
		return initialBackorder;
	}
	public int getCompleteLotsOfEarlierPlanningRuns(int period) {
		return completeLotsOfEarlierPlanningRuns.get(period);
	}
	public int getInitialInventory() {
		return initialInventory;
	}
	
}
