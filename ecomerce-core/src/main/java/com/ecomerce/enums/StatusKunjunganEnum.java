package com.ecomerce.enums;

public enum StatusKunjunganEnum {
	
	BARU("BARU"),
	LAMA("LAMA");
	
	private String val;

	StatusKunjunganEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}}
