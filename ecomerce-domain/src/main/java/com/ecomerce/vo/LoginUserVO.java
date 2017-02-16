package com.ecomerce.vo;

import javax.validation.constraints.NotNull;


public class LoginUserVO {
	
	@NotNull(message="Kata Sandi tidak boleh kosong")
	private String kataSandi;

	@NotNull(message="Nama User tidak boleh kosong")
	private String namaUser;
	
	@NotNull(message="is add harus diisi")
	private Boolean isAdd;
	

	@NotNull(message="is Confirm harus diisi")
	private Boolean isConfirm;

	public String getKataSandi() {
		return kataSandi;
	}

	public void setKataSandi(String kataSandi) {
		this.kataSandi = kataSandi;
	}

	public String getNamaUser() {
		return namaUser;
	}

	public void setNamaUser(String namaUser) {
		this.namaUser = namaUser;
	}

	public Boolean getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(Boolean isAdd) {
		this.isAdd = isAdd;
	}

	public Boolean getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(Boolean isConfirm) {
		this.isConfirm = isConfirm;
	}
	
	

	
}

