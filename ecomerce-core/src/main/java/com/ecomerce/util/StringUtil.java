package com.ecomerce.util;

/**
 * String utility class. untuk kebutuhan khusus di luar fungsi-fungsi StringUtil
 * apache common lib
 * 
 * @author Adik
 */
public final class StringUtil {

	private StringUtil() {
		super();
	}

	/**
	 * Format Nomor: Merubah format nomor sesuai dengan panjang yang diminta
	 * Contoh (1,6) -> 000006
	 * 
	 * @param angka
	 *            String
	 * @param length
	 *            panjang karakter
	 * @return
	 */
	public static String formatNumber(String angka, int length) {
		if (angka == null) {
			return "";
		}
		if (length < 1) {
			return angka;
		}
		String nol = "";
		int finalLength = length - angka.length();
		for (int i = 0; i < finalLength; i++) {
			nol += "0";
		}
		return nol + angka;
	}

	/**
	 * Get Month Name
	 * 
	 * @return
	 */
	public static String monthName(int month) {
		if (month < 1 || month > 12) {
			return "NOT VALID";
		}
		String[] monthNames = { "January", "February", "March", "April", "May",
				"June", "July", "August", "September", "October", "November",
				"December" };
		return monthNames[month - 1];
	}

	/**
	 * Get Day Name
	 * 
	 * @return
	 */
	public static String dayName(int day) {
		if (day < 1 || day > 7) {
			return "NOT VALID";
		}
		String[] monthNames = { "Sunday", "Monday", "Tuesday", "Wednesday",
				"Thursday", "Friday", "Saturday" };
		return monthNames[day];
	}

	/**
	 * Get Nama Bulan
	 * 
	 * @return
	 */
	public static String namaBulan(int month) {
		if (month < 1 || month > 12) {
			return "NOT VALID";
		}
		String[] monthNames = { "Januari", "Februari", "Maret", "April", "Mei",
				"Juni", "Juli", "Agustus", "September", "Oktober", "November",
				"Desember" };
		return monthNames[month];
	}

	/**
	 * Get Nama Hari
	 * 
	 * @return
	 */
	public static String namaHari(int day) {
		if (day < 1 || day > 7) {
			return "NOT VALID";
		}
		String[] monthNames = { "Minggu", "Senin", "Selasa", "Rabu", "Kamis",
				"Jumat", "Sabtu" };
		return monthNames[day];
	}
	
	/**
	 * Validasi String no telpon Indonesia
	 * 
	 * @return
	 */
	public static boolean validateIndonesiaPhoneNumber(String phoneNo) {		
		//xxxx 7 atau 8 digit
		String pattern1 = "\\d\\d\\d\\d([,\\s])?\\d{7,8}";
		//xxx 6 atau 7 digit
		String pattern2 = "\\d\\d\\d([,\\s])?\\d{6,7}";
        if (phoneNo.matches(pattern1)) return true;
        else if (phoneNo.matches(pattern2)) return true;
        else return false;         
    }
	
	/**
	 * Validasi String no registrasi (misalnya)
	 * 
	 * @return
	 */
	public static boolean validateNoRegistrationExample(String phoneNo) {
		// REG000001
		String pattern = "REG\\d{6}";
        if (phoneNo.matches(pattern)) return true;
        else return false;         
    }
}
