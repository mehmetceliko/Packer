package com.mobiquity.packer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.mobiquity.exception.APIException;
 

public class PackItemLoader {
	
	public static ArrayList<PackageRow>  loadItems(String path) throws Exception {
		
		
		ArrayList<PackageRow> listOfRows = new ArrayList<>();		
		
		try (BufferedReader br = new   BufferedReader(new FileReader(path,StandardCharsets.UTF_8))) {
//		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {		
			br.mark(1);
			if (br.read() != 0xFEFF)
			  br.reset();
			
			String line;
		    while ((line = br.readLine()) != null) {
		    	
		    	String[] maxWghtAndItems = line.split(":");
		    	
		    	BigDecimal packageRowMaxWeight = new BigDecimal(maxWghtAndItems[0].trim());
		    	String[] rowItemOptionsStr = maxWghtAndItems[1].replace("  ", " ").trim().split(" ");   //double space in file can cause error
		    	ArrayList<Item> rowItems = new ArrayList<>();
		    			    	
		    	for (String opt : rowItemOptionsStr) {
					//System.out.println(opt);
		    		opt = opt.replace("(", "");
		    		opt = opt.replace(")","");
		    		String[] itemStr = opt.split(",");
		    		int index= Integer.parseInt(itemStr[0]);					 
					BigDecimal wght = new BigDecimal(itemStr[1]);
					//BigDecimal price = new BigDecimal(itemStr[2].replace("€", ""));// no currency support
					BigDecimal price = new BigDecimal(itemStr[2].substring(1));
					
		    		Item rowItem = new Item(index,wght,price);
		    		rowItems.add(rowItem);
				}
		    	

		    	PackageRow packageRow = new PackageRow(packageRowMaxWeight,rowItems);	
		    	listOfRows.add(packageRow);
		    }
		}	
		
		//System.out.println("row count in file is "+listOfRows.size());
		
		ValidationResult vrPack= Validations.validatePackage(listOfRows);
		if (!vrPack.isValid)
			throw new Exception(vrPack.getDescription());
		
		return listOfRows;
	}
	
	
	public static void main(String[] args) throws Exception {
		//C:\workspaces\dev\mobiquity\test1\src\main\java\test1\PackItemLoader.java
		//C:\workspaces\dev\mobiquity\test1\src\test\resources\testFile1.txt
		String filePath ="C:\\workspaces\\dev\\mobiquity\\test1\\src\\test\\resources\\testFile1.txt";// "..\\..\\.\\...\\test\\resources\\testFile1.txt";
		
		loadItems(filePath);
		
		System.out.println("end");
	}

}
