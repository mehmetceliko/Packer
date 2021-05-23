package com.mobiquity.packer;

public class ValidationResult {	
 
	String  description = "";
	boolean isValid = true;
	
	public ValidationResult(String description, boolean isValid) {
		super();
		this.description = description;
		this.isValid = isValid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	

}
