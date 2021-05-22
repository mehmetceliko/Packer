package test1;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PackageRow {

	
	public PackageRow(BigDecimal maxAllowedWeight, ArrayList<Item> itemList) {
		super();
		this.maxAllowedWeight = maxAllowedWeight;
		this.itemList = itemList;
	}
	
	
	public PackageRow(BigDecimal maxAllowedWeight, ArrayList<Item> itemList, String binEquivalentOfOptimumCost) {
		super();
		this.maxAllowedWeight = maxAllowedWeight;
		this.itemList = itemList;
		this.binEquivalentOfOptimumCost = binEquivalentOfOptimumCost;
	}


	BigDecimal maxAllowedWeight = BigDecimal.ZERO;	
	BigDecimal cost = BigDecimal.ZERO;
	ArrayList<Item> itemList = new ArrayList<>();
	String binEquivalentOfOptimumCost = "-";
	
	public BigDecimal getCost() {
		return cost;
	}
	
	
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public BigDecimal getMaxAllowedWeight() {
		return maxAllowedWeight;
	}


	public void setMaxAllowedWeight(BigDecimal maxAllowedWeight) {
		this.maxAllowedWeight = maxAllowedWeight;
	}


	public ArrayList<Item> getItemList() {
		return itemList;
	}


	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}


	public String getBinEquivalentOfOptimumCost() {
		return binEquivalentOfOptimumCost;
	}


	public void setBinEquivalentOfOptimumCost(String binEquivalentOfOptimumCost) {
		this.binEquivalentOfOptimumCost = binEquivalentOfOptimumCost;
	}



	
	
 
}
