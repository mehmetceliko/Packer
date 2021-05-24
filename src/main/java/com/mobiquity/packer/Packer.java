package com.mobiquity.packer;

import java.util.ArrayList;

import com.google.common.base.Strings;
import com.mobiquity.exception.APIException;

 

public class Packer {

	private Packer() {
	}

	public static String pack(String filePath) throws APIException {
		
		DebugLogger.setDebug(false);//open or close debug display
		
		int maxDigit = 0;
		double binaryBaseNo = 2;
		double power = Math.pow(binaryBaseNo, maxDigit);
		StringBuilder packagingResut = new StringBuilder();
 
		ArrayList<PackageRow> input = null;
		try {
			input = PackItemLoader.loadItems(filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new APIException(filePath + " file can not be loaded",e1);
		}

		DebugLogger.debugLog("\n--------Calculation begins-------\n");
		DebugLogger.debugLog("cmbntn : binEquivalent : Weight : Cost");
		DebugLogger.debugLog("---------------------------------------");

		for (int j = 0; j < input.size(); j++) {

			PackageRow pr = input.get(j);
			maxDigit = pr.getItemList().size();
			power = Math.pow(binaryBaseNo, maxDigit);

			for (int i = 0; i < power; i++) {

				String binEquivalent = Integer.toString(i, 2);				
				binEquivalent = Strings.padStart(binEquivalent, maxDigit, Constants.PaddingCharForBinaryRepresentation);
				
				PackageCombinationResult packageCombinationResult = pr.calculateCombinationsCost(binEquivalent);
				
				if (DebugLogger.logger.getLevel().equals(Constants.logLevel)) {					

					String prCombinationWeight = String.valueOf(packageCombinationResult.getTotalWeightOfItems());
					String prCombinationCost = String.valueOf(packageCombinationResult.getTotalCostOfItemsInPackage());
					String optimumChoice = pr.getBinEquivalentOfOptimumCost().equals(binEquivalent) ? "*" : "";

					DebugLogger.debugLog(Strings.padStart(String.valueOf(i), maxDigit, Constants.PaddingCharForBinaryRepresentation) + " : " + binEquivalent
							+ " : " + Strings.padStart(prCombinationWeight, Constants.PaddingLength, ' ') + " : "
							+ Strings.padStart(prCombinationCost, Constants.PaddingLength, ' ') + " " + optimumChoice);
				}

			}

			String rowResult = pr.getIndexOfSelectedItemsFromBinaryRepresentation();
			DebugLogger.debugLog(rowResult);
			packagingResut.append(rowResult + "\n");
		}

		return packagingResut.toString();
	}
	
	

	


	

}
