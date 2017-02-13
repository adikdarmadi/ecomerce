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
 * class JenisProduk
 * 
 * @author Generator
 */
@Entity 
@Table(name = "t_detail_komponen_harga")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DetailKomponenHarga {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "deskripsi", nullable = false, length = 80)
	@NotNull(message="deskripsi harus Diisi")
	private String deskripsi;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_struk_order_detail")
	private StrukOrderDetail strukOrderDetail;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public StrukOrderDetail getStrukOrderDetail() {
		return strukOrderDetail;
	}

	public void setStrukOrderDetail(StrukOrderDetail strukOrderDetail) {
		this.strukOrderDetail = strukOrderDetail;
	}
	
	


}