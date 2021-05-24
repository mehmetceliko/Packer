package com.mobiquity.packer;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PackageRow {
	private BigDecimal maxAllowedWeight = BigDecimal.ZERO;
	private BigDecimal cost = BigDecimal.ZERO;
	private ArrayList<Item> itemList = new ArrayList<>();
	private String binEquivalentOfOptimumCost = "-";
	private String ValidationResultDescription = "";

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

	public String getValidationResultDescription() {
		return ValidationResultDescription;
	}

	public void setValidationResultDescription(String validationResultDescription) {
		ValidationResultDescription = validationResultDescription;
	}
	
	public PackageCombinationResult calculateCombinationsCost(String binEquivalent) {
		

		BigDecimal totalWeightOfItems = BigDecimal.ZERO;
		BigDecimal totalCostOfItemsInPackage = BigDecimal.ZERO;
		
		
		
		for (int i = 0; i < this.getItemList().size(); i++) {

			if (binEquivalent.charAt(i) == '1') {
				Item item = this.getItemList().get(i);
				totalWeightOfItems = totalWeightOfItems.add(item.getWeight());
				totalCostOfItemsInPackage = totalCostOfItemsInPackage.add(item.getPrice());
			}
		}
		 
		//if maxweight is not achieved and the new cost is bigger than the current then the new optimum is thenew one 
		if (this.getMaxAllowedWeight().compareTo(totalWeightOfItems) >= 0 && totalCostOfItemsInPackage.compareTo(this.getCost())> 0) {
			this.setBinEquivalentOfOptimumCost(binEquivalent);
			this.setCost(totalCostOfItemsInPackage);
		}else if (this.getMaxAllowedWeight().compareTo(totalWeightOfItems) >= 0 && totalCostOfItemsInPackage.compareTo(this.getCost()) == 0 ) {//cost equality
			//if costs are equal, take the smalll weight 
			//get weight of the current optimum and compare it with the current. 
			if (getTotalWeightOfChoise(this.getBinEquivalentOfOptimumCost()).compareTo(totalWeightOfItems) > 0) {
				this.setBinEquivalentOfOptimumCost(binEquivalent);
				this.setCost(totalCostOfItemsInPackage);
			}
		}
		 		
		PackageCombinationResult packageCombinationResult = new PackageCombinationResult(totalCostOfItemsInPackage, totalWeightOfItems,binEquivalent);
		
		return packageCombinationResult;
	}
	
	
	public String getIndexOfSelectedItemsFromBinaryRepresentation() {
		String binEquivalentOptimum = this.getBinEquivalentOfOptimumCost();		
		
		ArrayList<String> selectedItems = new ArrayList<>();
		for (int i = 0; i < binEquivalentOptimum.length();i++) {
			if (binEquivalentOptimum.charAt(i) == '1')
				selectedItems.add( String.valueOf(this.getItemList().get(i).getIndex()));				
		}
		
		String result = "-";
		
		for (String str : selectedItems) {
			
			if (result.equals("-"))
				result = str;
			else 
				result = result + "," + str;
		} 
		 
		return result;
	}
	
	
	public BigDecimal getTotalWeightOfChoise(String binEquivalent) {
		String binEquivalentOptimum = this.getBinEquivalentOfOptimumCost();		
		BigDecimal weightOfChoice = BigDecimal.ZERO;
		
		ArrayList<String> selectedItems = new ArrayList<>();
		for (int i = 0; i < binEquivalentOptimum.length();i++) {
			if (binEquivalentOptimum.charAt(i) == '1') {
				Item item = this.getItemList().get(i);
				selectedItems.add( String.valueOf(item.getIndex()));	
				weightOfChoice.add(item.getWeight());
			}			
		}
				 
		return weightOfChoice;
	}


	
}
