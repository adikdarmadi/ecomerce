package com.ecomerce.enums;

public enum StatusNegPosEnum {
	
	NEG("NEG"),
	POS("POS");
	
	private String val;

	StatusNegPosEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
}
