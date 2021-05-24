package com.mobiquity.packer;

import java.math.BigDecimal;

public class PackageCombinationResult {
	private BigDecimal totalCostOfItemsInPackage = BigDecimal.ZERO;
	private BigDecimal totalWeightOfItems = BigDecimal.ZERO;
	private String binEquivalent;
	
	public PackageCombinationResult(BigDecimal totalCostOfItemsInPackage, BigDecimal totalWeightOfItems,String binEquivalent) {
		super();
		this.totalCostOfItemsInPackage = totalCostOfItemsInPackage;
		this.totalWeightOfItems = totalWeightOfItems;
		this.setBinEquivalent(binEquivalent);
	}
	
	public BigDecimal getTotalCostOfItemsInPackage() {
		return totalCostOfItemsInPackage;
	}
	public void setTotalCostOfItemsInPackage(BigDecimal totalCostOfItemsInPackage) {
		this.totalCostOfItemsInPackage = totalCostOfItemsInPackage;
	}
	public BigDecimal getTotalWeightOfItems() {
		return totalWeightOfItems;
	}
	public void setTotalWeightOfItems(BigDecimal totalWeightOfItems) {
		this.totalWeightOfItems = totalWeightOfItems;
	}

	public String getBinEquivalent() {
		return binEquivalent;
	}

	public void setBinEquivalent(String binEquivalent) {
		this.binEquivalent = binEquivalent;
	}

}
