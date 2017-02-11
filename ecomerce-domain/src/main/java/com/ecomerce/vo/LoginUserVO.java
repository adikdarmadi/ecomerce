package com.ecomerce.vo;

import javax.validation.constraints.NotNull;


public class LoginUserVO {
	
	@NotNull(message="Kata Sandi tidak boleh kosong")
	private String kataSandi;

	@NotNull(message="Nama User tidak boleh kosong")
	private String namaUser;

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
	
	

	
}

