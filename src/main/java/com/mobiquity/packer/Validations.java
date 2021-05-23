package com.mobiquity.packer;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Validations {
	
	
public static ValidationResult validatePackageRowItemCountToChoose(PackageRow pr) {
		
		ArrayList<Item>itemList = pr.getItemList();
		
			if (itemList.size() > Constants.MaxItemCountToSelectForPackage) {
				  
				return new ValidationResult("Number of item to choose is " + itemList.size() +". " + Constants.maxItemCountIsAchieved,false);
			}
		
		return new ValidationResult("",true);		
	}
	
	
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
	
	public static ValidationResult validatePackage(ArrayList<PackageRow> listOfRows) {
		
		ArrayList<ValidationResult> vrList = new ArrayList<ValidationResult>();
		
		for (PackageRow packageRow : listOfRows) {
			
			ValidationResult vr= validatePackageRowWeight(packageRow);
			if (!vr.isValid)
				vrList.add(vr);
			
			vr= validatePackageRowItemsWeight(packageRow);
			if (!vr.isValid)
				vrList.add(vr);
			
			vr= validatePackageRowItemsCost(packageRow);
			if (!vr.isValid)
				vrList.add(vr);
			
			vr= validatePackageRowItemCountToChoose(packageRow);
			if (!vr.isValid)
				vrList.add(vr);			
		}
		
		if(vrList.size()>0) {
			StringBuilder errDesc = new StringBuilder();
			
			for (ValidationResult validationResult : vrList) {
				errDesc.append(validationResult.description+"\n");
			}
			
			return new ValidationResult(errDesc.toString(), false);
		}
				
		return new ValidationResult("",true);		
	}	
	

}
