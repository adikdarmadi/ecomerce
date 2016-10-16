package com.ecomerce.enums;

public enum TipePasienEnum {
	
	BARU("BARU"),
	LAMA("LAMA");
	
	private String val;

	TipePasienEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}}
