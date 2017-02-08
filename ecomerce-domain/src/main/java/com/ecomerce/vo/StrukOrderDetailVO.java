package com.ecomerce.vo;

import javax.validation.constraints.NotNull;

public class StrukOrderDetailVO {

	@NotNull(message = "Produk Tidak Boleh Kosong")
	private ProdukVO produk;

	public ProdukVO getProduk() {
		return produk;
	}

	public void setProduk(ProdukVO produk) {
		this.produk = produk;
	}
	
	

	
}