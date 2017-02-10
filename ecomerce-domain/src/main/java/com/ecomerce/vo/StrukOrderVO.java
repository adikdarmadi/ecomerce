package com.ecomerce.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * class Produk
 * 
 * @author Generator
 */
public class StrukOrderVO {

    @NotNull(message="tanggal harus Diisi") 
	private Date tanggalOrder;
    
	@NotEmpty(message="struk order Detail Harus Diisi")
	@Valid
	private List<StrukOrderDetailVO> strukOrders = new ArrayList<StrukOrderDetailVO>();

	public Date getTanggalOrder() {
		return tanggalOrder;
	}

	public void setTanggalOrder(Date tanggalOrder) {
		this.tanggalOrder = tanggalOrder;
	}

	public List<StrukOrderDetailVO> getStrukOrders() {
		return strukOrders;
	}

	public void setStrukOrders(List<StrukOrderDetailVO> strukOrders) {
		this.strukOrders = strukOrders;
	}


	
}