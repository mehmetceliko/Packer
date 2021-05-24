package com.mobiquity.packer;

import java.math.BigDecimal;

public class Item {
	private int index;
	private BigDecimal weight=BigDecimal.ZERO;
	private BigDecimal price =BigDecimal.ZERO;
	
	public Item(int index, BigDecimal weight, BigDecimal price) {
		super();
		this.index = index;
		this.weight = weight;
		this.price = price;
	}

	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
		
	 

}
