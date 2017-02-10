package com.ecomerce.vo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class StrukOrderDetailVO {

	@NotNull(message = "Produk Tidak Boleh Kosong")
	private ProdukVO produk;
	
	@NotEmpty(message="Detail Komponen Harus Diisi")
	@Valid
	private List<DetailKomponenHargaVO> detailKomponenHarga = new ArrayList<DetailKomponenHargaVO>();

	public ProdukVO getProduk() {
		return produk;
	}

	public void setProduk(ProdukVO produk) {
		this.produk = produk;
	}

	public List<DetailKomponenHargaVO> getDetailKomponenHarga() {
		return detailKomponenHarga;
	}

	public void setDetailKomponenHarga(List<DetailKomponenHargaVO> detailKomponenHarga) {
		this.detailKomponenHarga = detailKomponenHarga;
	}
	
	
	
	
	

	
}