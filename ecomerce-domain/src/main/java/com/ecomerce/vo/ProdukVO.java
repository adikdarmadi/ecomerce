package com.ecomerce.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecomerce.base.BaseMaster;
import com.ecomerce.base.vo.BaseMasterVO;
import com.ecomerce.entities.DetailJenisProduk;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * class Produk
 * 
 * @author Generator
 */
public class ProdukVO extends BaseMasterVO{
	
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