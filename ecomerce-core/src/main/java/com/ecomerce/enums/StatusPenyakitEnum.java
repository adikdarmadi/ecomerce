package com.ecomerce.enums;

public enum StatusPenyakitEnum {
	
	BARU("BARU"),
	LAMA("LAMA");
	
	private String val;

	StatusPenyakitEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}}
