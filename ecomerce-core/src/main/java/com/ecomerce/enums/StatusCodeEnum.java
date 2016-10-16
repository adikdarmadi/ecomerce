package com.ecomerce.enums;

public enum StatusCodeEnum {
	
	BARU("BARU"),
	LAMA("LAMA");
	
	private String val;

	StatusCodeEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}}
