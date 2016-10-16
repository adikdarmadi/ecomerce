package com.ecomerce.enums;

public enum Status {

	TERSEDIA("Tersedia"), ORDER("ORDER"), DIPAKAI("DIPAKAI"), SELESAI("SELESAI");

	private String val;

	Status(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}

}
