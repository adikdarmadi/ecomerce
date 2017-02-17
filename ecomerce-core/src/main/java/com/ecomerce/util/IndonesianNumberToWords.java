package com.ecomerce.util;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * The Class IndonesianNumberToWords.
 * 
 * @author Adik
 */
public final class IndonesianNumberToWords implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6996656969010738825L;

	private static final int THREE = 3;
	private static final int SIX = 6;
	private static final int NINE = 9;
	private static final int TEN = 10;
	private static final int TWELVE = 12;
	private static final int TWENTY = 20;
	private static final int HUNDRED = 100;

	private IndonesianNumberToWords() {
		// To prevent instantiation of this class
	}

	/** The Constant TENS_NAMES. */
	private static final String[] TENS_NAMES = { "", " sepuluh", " dua puluh",
			" tiga puluh", " empat puluh", " lima puluh", " enam puluh",
			" tujuh puluh", " delapan puluh", " sembilan puluh" };

	/** The Constant NUMBER_NAMES. */
	private static final String[] NUMBER_NAMES = { "", " satu", " dua",
			" tiga", " empat", " lima", " enam", " tujuh", " delapan",
			" sembilan", " sepuluh", " sebelas", " dua belas", " tiga belas",
			" empat belas", " lima belas", " enam belas", " tujuh belas",
			" delapan belas", " sembilan belas" };

	/**
	 * Convert less than one thousand.
	 * 
	 * @param number
	 *            the number
	 * @return the string
	 */
	private static String convertLessThanOneThousand(int number) {
		String soFar;
		int convertedNum = number;
		if (number % HUNDRED < TWENTY) {
			soFar = NUMBER_NAMES[number % HUNDRED];
			convertedNum /= HUNDRED;
		} else {
			soFar = NUMBER_NAMES[number % TEN];
			convertedNum /= TEN;
			soFar = TENS_NAMES[convertedNum % TEN] + soFar;
			convertedNum /= TEN;
		}
		if (convertedNum == 0) {
			return soFar;
		}
		if (convertedNum == 1) {
			return "seratus" + soFar;
		} else {
			return NUMBER_NAMES[convertedNum] + " ratus" + soFar;
		}
	}

	/**
	 * Converts a long number into its String representation in bahasa
	 * indonesia.
	 * 
	 * @param number
	 *            the number
	 * @return the string
	 */
	public static String convert(long number) {
		// 0 to 999 999 999 999
		if (number == 0) {
			return "nol";
		}
		String snumber = Long.toString(number);
		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		snumber = df.format(number);
		// XXXnnnnnnnnn
		int billions = Integer.parseInt(snumber.substring(0, THREE));
		// nnnXXXnnnnnn
		int millions = Integer.parseInt(snumber.substring(THREE, SIX));
		// nnnnnnXXXnnn
		int hundredThousands = Integer.parseInt(snumber.substring(SIX, NINE));
		// nnnnnnnnnXXX
		int thousands = Integer.parseInt(snumber.substring(NINE, TWELVE));
		String tradBillions;
		switch (billions) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			tradBillions = convertLessThanOneThousand(billions) + " milyar ";
			break;
		default:
			tradBillions = convertLessThanOneThousand(billions) + " milyar ";
		}
		String result = tradBillions;
		String tradMillions;
		switch (millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(millions) + " juta ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(millions) + " juta ";
		}
		result = result + tradMillions;
		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = "seribu ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(hundredThousands)
					+ " ribu ";
		}
		result = result + tradHundredThousands;
		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result = result + tradThousand;
		// remove extra spaces!
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}

}
