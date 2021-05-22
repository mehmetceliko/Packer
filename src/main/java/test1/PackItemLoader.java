package test1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class PackItemLoader {
	
	public static ArrayList<PackageRow>  loadItems(String path) throws FileNotFoundException, IOException {
		
		ArrayList<PackageRow> listOfRows = new ArrayList<>();
		
		try (BufferedReader br = new   BufferedReader(new FileReader(path))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       // process the line.
		    	
		    	System.out.println(line);
		    	
		    	String[] maxWghtAndItems = line.split(":");
		    	
		    	BigDecimal packageRowMaxWeight = new BigDecimal(maxWghtAndItems[0].trim());
		    	String[] rowItemOptionsStr = maxWghtAndItems[1].replace("  ", " ").trim().split(" ");   //double space in file can cause error
		    	ArrayList<Item> rowItems = new ArrayList<>();
		    			    	
		    	for (String opt : rowItemOptionsStr) {
					//System.out.println(opt);
		    		opt = opt.replace("(", "");
		    		opt = opt.replace(")","");
		    		String[] itemStr = opt.split(",");
		    		int index=0;
					try {
						index = new Integer(itemStr[0]);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BigDecimal wght = new BigDecimal(itemStr[1]);
					BigDecimal price = new BigDecimal(itemStr[2].replace("€", ""));// no currency support   		
		    		Item rowItem = new Item(index,wght,price);
		    		rowItems.add(rowItem);
				}
		    	

		    	PackageRow packageRow = new PackageRow(packageRowMaxWeight,rowItems);		
		    	listOfRows.add(packageRow);
		    }
		}	
		
		System.out.println("row count in file is "+listOfRows.size());
		
		return listOfRows;
	}
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		//C:\workspaces\dev\mobiquity\test1\src\main\java\test1\PackItemLoader.java
		//C:\workspaces\dev\mobiquity\test1\src\test\resources\testFile1.txt
		String filePath ="C:\\workspaces\\dev\\mobiquity\\test1\\src\\test\\resources\\testFile1.txt";// "..\\..\\.\\...\\test\\resources\\testFile1.txt";
		
		loadItems(filePath);
		
		System.out.println("end");
	}

}
