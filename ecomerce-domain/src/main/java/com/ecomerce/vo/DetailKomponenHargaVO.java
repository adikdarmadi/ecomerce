package com.ecomerce.vo;

import javax.validation.constraints.NotNull;

public class DetailKomponenHargaVO {

	@NotNull(message="Deskripsi Harus Diisi")
	private String deskripsi;

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	
}