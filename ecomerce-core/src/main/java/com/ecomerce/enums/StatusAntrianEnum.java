package com.ecomerce.enums;

public enum StatusAntrianEnum {
	
	MENUNGGU("0"),
	DIPANGGIL_SUSTER("1"),
	DIPANGGIL_DOKTER("2"),
	SELESAI_DIPERIKSA("3"),	
	PERSIAPAN("4"),
	PERIKSA("5"),
	SELESAI_HASIL("7"),
	AMBIIL_HASIL("8"),
	HASIL("6"),
	SAMPLE_DI_AMBIL("9"),
	SAMPLE_DI_TERIMA("10"),
	SAMPLE_DI_VERIFIKASI("11"),
	VALIDASI("12"),
	STATUS_CETAK("13"),
	VALIDASI_ANALIS("14")
	;
	
	private String val;

	StatusAntrianEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}}

