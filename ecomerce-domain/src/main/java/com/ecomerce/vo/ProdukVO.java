package com.ecomerce.vo;

import com.ecomerce.entities.DetailJenisProduk;

/**
 * class Produk
 * 
 * @author Generator
 */
public class ProdukVO {
	
	private Integer id;

	private DetailJenisProduk detailJenisProduk;

	private String namaProduk;


	public DetailJenisProduk getDetailJenisProduk() {
		return detailJenisProduk;
	}

	public void setDetailJenisProduk(DetailJenisProduk detailJenisProduk) {
		this.detailJenisProduk = detailJenisProduk;
	}

	public String getNamaProduk() {
		return namaProduk;
	}

	public void setNamaProduk(String namaProduk) {
		this.namaProduk = namaProduk;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
}