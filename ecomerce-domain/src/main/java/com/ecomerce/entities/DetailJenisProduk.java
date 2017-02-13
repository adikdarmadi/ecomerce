package com.ecomerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * class DetailJenisProduk
 * 
 * @author Generator
 */
@Entity 
@Table(name = "m_detail_jenis_produk")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DetailJenisProduk  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@NotNull(message = "Detail Jenis Produk tidak boleh kosong")
	@Column(name = "detail_jenis_produk", nullable = false, length = 50)
	private String detailJenisProduk;
	

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_jenis_produk")
	@NotNull(message = "Jenis Produk tidak boleh kosong")
	private JenisProduk jenisProduk;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDetailJenisProduk() {
		return detailJenisProduk;
	}


	public void setDetailJenisProduk(String detailJenisProduk) {
		this.detailJenisProduk = detailJenisProduk;
	}


	public JenisProduk getJenisProduk() {
		return jenisProduk;
	}


	public void setJenisProduk(JenisProduk jenisProduk) {
		this.jenisProduk = jenisProduk;
	}
	
	

}