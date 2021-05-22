package test1;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.google.common.base.Strings;

public class Starter {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		int maxDigit = 3;
		double a = 2;
        double b = maxDigit;
        double power = Math.pow(a, b);
        /*
        System.out.println(a + " to the power " + b + " = " + power);
        
		
        for (int i = 0;i<power;i++) {     
        	
        	String binEquivalent = Integer.toString(i, 2);
        	binEquivalent = Strings.padStart(binEquivalent, maxDigit, '0');//String.format("%010d", binEquivalent);
		
        	System.out.println(binEquivalent);
        }
		*/
        
        
        
        String filePath ="C:\\workspaces\\dev\\mobiquity\\test1\\src\\test\\resources\\testFile1.txt";
		
        ArrayList<PackageRow> input=null;
		try {
			input = PackItemLoader.loadItems(filePath);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        System.out.println("\n--------hesaplama basliyor-------\n");
        
        //System.out.println("cmbntn : binEquivalent : Weight : Cost");
        //System.out.println("---------------------------------------");
        
        for (int j = 0; j<input.size();j++) {
        
	        PackageRow row = input.get(j);
	        maxDigit = row.getItemList().size();
	        power = Math.pow(a, maxDigit);
	        PackageRow pr = input.get(j);
	        
	        for (int i = 0;i<power;i++) {     
	        	
	        	String binEquivalent = Integer.toString(i, 2);
	        	binEquivalent = Strings.padStart(binEquivalent, maxDigit, '0');//String.format("%010d", binEquivalent);
	        	
	        	PackageCombinationResult packageCombinationResult=CalculateCombinationsCost(pr,binEquivalent);
			 
			
				String prCombinationWeight = String.valueOf(packageCombinationResult.getTotalWeightOfItems());
				String prCombinationCost = String.valueOf(packageCombinationResult.getTotalCostOfItemsInPackage());
				
				String optimumChoice = pr.getBinEquivalentOfOptimumCost().equals(binEquivalent) ? "*" : "";
				
		 
	        	//System.out.println( Strings.padStart(String.valueOf(i), maxDigit, '0') + " : " + binEquivalent + " : " + Strings.padStart(prCombinationWeight,10,' ') + " : " + Strings.padStart(prCombinationCost,10,' ') + " " + optimumChoice);
				
	        }
	        
	        //System.out.println(row.getBinEquivalentOfOptimumCost());
	        System.out.println(getIndexOfSelectedItemsFromBinaryRepresentation(pr, pr.getBinEquivalentOfOptimumCost()));
        }

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
		
		/**
		 * 	0 : if value of this BigDecimal is equal to that of BigDecimal object passed as parameter.
			1 : if value of this BigDecimal is greater than that of BigDecimal object passed as parameter.
			-1 : if value of this BigDecimal is less than that of BigDecimal object passed as parameter.
		 * */
		
		//if maxweight is not achieved and the new cost is bigger than the current then the new optimum is thenew one 
		if (pr.getMaxAllowedWeight().compareTo(totalWeightOfItems) > 0 && totalCostOfItemsInPackage.compareTo(pr.getCost())> 0) {
			pr.setBinEquivalentOfOptimumCost(binEquivalent);
			pr.setCost(totalCostOfItemsInPackage);
		}
	
		
		PackageCombinationResult packageCombinationResult = new PackageCombinationResult(totalCostOfItemsInPackage, totalWeightOfItems,binEquivalent);
		
		return packageCombinationResult;
	}

}
