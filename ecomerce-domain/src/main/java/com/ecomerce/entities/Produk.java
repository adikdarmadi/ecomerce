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
 * class Produk
 * 
 * @author Generator
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "m_produk")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Produk {


    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_detail_jenis_produk")
	@NotNull(message = "Detail Jenis Produk Tidak Boleh Kosong")
	private DetailJenisProduk detailJenisProduk;

	@NotNull(message = "Nama Produk Tidak Boleh Kosong")
	@Column(name = "nama_produk",nullable=false)
	private String namaProduk;
	
    public Integer getId() {

		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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
	
	
}