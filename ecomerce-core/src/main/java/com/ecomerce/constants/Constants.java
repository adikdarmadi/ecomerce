package com.ecomerce.constants;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Constants value
 * 
 * @author Roberto
 */
public class Constants {
	public static final String ecomerce = "SkFTQU1FRElLQQ==";
	public static final String APP_VERSION = "APP_VERSION";
	
	public static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";
	
	//for example
	public static final String IDR = "IDR";
	public static final String RP = "RP";
	
	public static final String COMMA = ",";

	public static final DecimalFormat ONE_COMA_FORMAT = new DecimalFormat("#.#");
	public static final DecimalFormat TWO_COMA_FORMAT = new DecimalFormat(
			"#.##");

	public static final DecimalFormat MONEY_FORMAT_WITHOUT_COMMA = new DecimalFormat(
			"###,###");

	public static final class DateFormat {
		public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
				"yyyyMMdd");
		public static final SimpleDateFormat dd_MMM_yyyy = new SimpleDateFormat(
				"dd MMM yyyy");
		public static final SimpleDateFormat yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		public static final SimpleDateFormat yyyy_MM_dd_T_HH_mm_ss = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss");
		public static final SimpleDateFormat yyyyMMdd_HH_mm_ss = new SimpleDateFormat(
				"yyyyMMdd HH:mm:ss");
	}

	/* message */
	public static final class MessageInfo {
		public static final String INFO_MESSAGE = "INFO_MESSAGE";
		public static final String WARNING_MESSAGE = "WARNING_MESSAGE";
		public static final String ERROR_MESSAGE = "ERROR_MESSAGE";
		public static final String EXCEPTION_MESSAGE = "EXCEPTION_MESSAGE";

	}
	
	/* locale id (indonesia / default) and en (english) */
	public static final class Locale {
		public static final String INA = "ina";
		public static final String EN = "en";

	}

}
