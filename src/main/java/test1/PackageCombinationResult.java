package test1;

import java.math.BigDecimal;

public class PackageCombinationResult {
	BigDecimal totalCostOfItemsInPackage = BigDecimal.ZERO;
	BigDecimal totalWeightOfItems = BigDecimal.ZERO;
	String binEquivalent;
	
	public PackageCombinationResult(BigDecimal totalCostOfItemsInPackage, BigDecimal totalWeightOfItems,String binEquivalent) {
		super();
		this.totalCostOfItemsInPackage = totalCostOfItemsInPackage;
		this.totalWeightOfItems = totalWeightOfItems;
		this.binEquivalent = binEquivalent;
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

}
