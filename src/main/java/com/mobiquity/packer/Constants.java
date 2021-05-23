package com.mobiquity.packer;

import java.math.BigDecimal;
import java.util.logging.Level;

public class Constants {
	public static final char paddingCharForBinaryRepresentation = '0';
	public static final int paddingLength = 10;
	
	public static final BigDecimal MaxWeightLimitOfPackage = BigDecimal.valueOf(100);
	public static final BigDecimal MaxWeightLimitOfItem = BigDecimal.valueOf(100);
	public static final BigDecimal MaxCostLimitOfItem = BigDecimal.valueOf(100);
	public static final int MaxItemCountToSelectForPackage = 15;
	
	public static final String maxItemCountIsAchieved = "Number of item to choose must be <= " + MaxItemCountToSelectForPackage;
	public static final String maxItemCostIsAchieved = "Max cost for an item must be <= " + MaxCostLimitOfItem;
	public static final String maxItemWeightIsAchieved = "Max weight for an item must be <= " + MaxWeightLimitOfItem;
	public static final String maxPckWeightIsAchieved = "Max weight for a package must be <= " + MaxWeightLimitOfPackage;
	
	public static Level logLevel = Level.ALL;
	

}
