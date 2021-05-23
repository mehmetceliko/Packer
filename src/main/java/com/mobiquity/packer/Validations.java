package com.mobiquity.packer;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Validations {
	
	public static ValidationResult validatePackageRowItemsCost(PackageRow pr) {
		
		ArrayList<Item>itemList = pr.getItemList();
		
		for (Item item : itemList) {
			if (item.getPrice().compareTo(Constants.MaxCostLimitOfItem) > 0) {
				  
				return new ValidationResult("Cost of item is " + item.getPrice() +". " + Constants.maxItemCostIsAchieved,false);
			}
		}
		
		return new ValidationResult("",true);		
	}
	
	
	public static ValidationResult validatePackageRowItemsWeight(PackageRow pr) {
		
		ArrayList<Item>itemList = pr.getItemList();
		
		for (Item item : itemList) {
			if (item.getWeight().compareTo(Constants.MaxWeightLimitOfItem) > 0) {
				  
				return new ValidationResult("Weight of item is " + item.getWeight() +". " + Constants.maxItemWeightIsAchieved,false);
			}
		}
		
		return new ValidationResult("",true);		
	}
	
	public static ValidationResult validatePackageRowWeight(PackageRow pr) {
		
		BigDecimal pckWeight = pr.getMaxAllowedWeight();
		
	 
			if (pckWeight.compareTo(Constants.MaxWeightLimitOfPackage) > 0) {
				  
				return new ValidationResult("Weight of package is " + pckWeight +". " + Constants.maxPckWeightIsAchieved,false);
			}
	 
		
		return new ValidationResult("",true);		
	}	

}
