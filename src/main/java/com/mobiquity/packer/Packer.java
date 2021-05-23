package com.mobiquity.packer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.logging.Level;

import com.google.common.base.Strings;
import com.mobiquity.exception.APIException;

 

public class Packer {

	private Packer() {
	}

	public static String pack(String filePath) throws APIException {
		
		DebugLogger.logger.setLevel(Level.OFF);
		
		int maxDigit = 0;
		double binaryBaseNo = 2;
		double b = maxDigit;
		double power = Math.pow(binaryBaseNo, b);
		StringBuilder packagingResut = new StringBuilder();
 
		ArrayList<PackageRow> input = null;
		try {
			input = PackItemLoader.loadItems(filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new APIException(filePath + " file can not be loaded",e1);
		}

		DebugLogger.LogDebugInfo("\n--------hesaplama basliyor-------\n");
		DebugLogger.LogDebugInfo("cmbntn : binEquivalent : Weight : Cost");
		DebugLogger.LogDebugInfo("---------------------------------------");

		for (int j = 0; j < input.size(); j++) {

			PackageRow row = input.get(j);
			maxDigit = row.getItemList().size();
			power = Math.pow(binaryBaseNo, maxDigit);
			PackageRow pr = input.get(j);

			for (int i = 0; i < power; i++) {

				String binEquivalent = Integer.toString(i, 2);
				binEquivalent = Strings.padStart(binEquivalent, maxDigit, '0');// String.format("%010d", binEquivalent);
				
				CalculateCombinationsCost(pr, binEquivalent);

//				PackageCombinationResult packageCombinationResult = CalculateCombinationsCost(pr, binEquivalent);
//
//				String prCombinationWeight = String.valueOf(packageCombinationResult.getTotalWeightOfItems());
//				String prCombinationCost = String.valueOf(packageCombinationResult.getTotalCostOfItemsInPackage());//
//				String optimumChoice = pr.getBinEquivalentOfOptimumCost().equals(binEquivalent) ? "*" : "";
//
//				DebugLogger.LogDebugInfo(Strings.padStart(String.valueOf(i), maxDigit, '0') + " : " + binEquivalent + " : "
//						+ Strings.padStart(prCombinationWeight, 10, ' ') + "	 : "
//						+ Strings.padStart(prCombinationCost, 10, ' ') + " " + optimumChoice);

			}

			// System.out.println(row.getBinEquivalentOfOptimumCost());
			String rowResult = getIndexOfSelectedItemsFromBinaryRepresentation(pr, pr.getBinEquivalentOfOptimumCost());
			DebugLogger.LogDebugInfo(rowResult);
			packagingResut.append(rowResult + "\n");
		}

		return packagingResut.toString();
	}
	
	
	public static String getIndexOfSelectedItemsFromBinaryRepresentation(PackageRow pr, String binEquivalentOptimum) {
		ArrayList<String> selectedItems = new ArrayList<>();
		for (int i = 0; i < binEquivalentOptimum.length();i++) {
			if (binEquivalentOptimum.charAt(i) == '1')
				selectedItems.add( String.valueOf(pr.getItemList().get(i).getIndex()));				
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
	
	public static PackageCombinationResult CalculateCombinationsCost(PackageRow pr, String binEquivalent) {
		

		BigDecimal totalWeightOfItems = BigDecimal.ZERO;
		BigDecimal totalCostOfItemsInPackage = BigDecimal.ZERO;
		
		
		
		for ( int i = 0; i < pr.itemList.size();i++) {
			
			if (binEquivalent.charAt(i)=='1') {
			Item item = pr.getItemList().get(i);
			totalWeightOfItems = totalWeightOfItems.add(  item.getWeight());
			totalCostOfItemsInPackage = totalCostOfItemsInPackage.add( item.getPrice());
			}
		}
		 
		//if maxweight is not achieved and the new cost is bigger than the current then the new optimum is thenew one 
		if (pr.getMaxAllowedWeight().compareTo(totalWeightOfItems) > 0 && totalCostOfItemsInPackage.compareTo(pr.getCost())> 0) {
			pr.setBinEquivalentOfOptimumCost(binEquivalent);
			pr.setCost(totalCostOfItemsInPackage);
		}
	
		
		PackageCombinationResult packageCombinationResult = new PackageCombinationResult(totalCostOfItemsInPackage, totalWeightOfItems,binEquivalent);
		
		return packageCombinationResult;
	}

	

}
