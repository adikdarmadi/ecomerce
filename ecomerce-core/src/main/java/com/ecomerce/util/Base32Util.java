package com.ecomerce.util;

/**
 * The Class Base32.
 * 
 * @author Roberto
 */
public class Base32Util {
	private static final char[] BASE32_CODE_ARRAY = "0123456789ABCDEFGHJKMNPQRSTVWXYZ"
			.toCharArray();

	private Base32Util() {
		super();
	}

	/**
	 * Encode the byte[] in Base 32
	 * 
	 * @param byteArrayToEncode
	 * 
	 * @return String
	 */
	public static String encode(byte[] byteArrayToEncode) {
		// Douglas Crockford's Base32 Encoding
		// see http://www.crockford.com/wrmg/base32.html
		assert byteArrayToEncode != null;
		assert byteArrayToEncode.length <= Integer.MAX_VALUE / 8 * 5 - 4;
		char[] encodedCharArray = new char[(byteArrayToEncode.length + 4) / 5 * 8];
		int blockPosition = 0, bitPosition = 0;
		long decimalValue;
		while (blockPosition + 4 < byteArrayToEncode.length) {
			decimalValue = (long) (byteArrayToEncode[blockPosition + 0] & 0xff) << 32
					| (long) (byteArrayToEncode[blockPosition + 1] & 0xff) << 24
					| (long) (byteArrayToEncode[blockPosition + 2] & 0xff) << 16
					| (long) (byteArrayToEncode[blockPosition + 3] & 0xff) << 8
					| (byteArrayToEncode[blockPosition + 4] & 0xff);
			encodedCharArray[bitPosition + 0] = BASE32_CODE_ARRAY[(int) (decimalValue >> 35) & 0x1f];
			encodedCharArray[bitPosition + 1] = BASE32_CODE_ARRAY[(int) (decimalValue >> 30) & 0x1f];
			encodedCharArray[bitPosition + 2] = BASE32_CODE_ARRAY[(int) (decimalValue >> 25) & 0x1f];
			encodedCharArray[bitPosition + 3] = BASE32_CODE_ARRAY[(int) (decimalValue >> 20) & 0x1f];
			encodedCharArray[bitPosition + 4] = BASE32_CODE_ARRAY[(int) (decimalValue >> 15) & 0x1f];
			encodedCharArray[bitPosition + 5] = BASE32_CODE_ARRAY[(int) (decimalValue >> 10) & 0x1f];
			encodedCharArray[bitPosition + 6] = BASE32_CODE_ARRAY[(int) (decimalValue >> 5) & 0x1f];
			encodedCharArray[bitPosition + 7] = BASE32_CODE_ARRAY[(int) (decimalValue >> 0) & 0x1f];
			blockPosition += 5;
			bitPosition += 8;
		}
		if (blockPosition + 4 == byteArrayToEncode.length) {
			decimalValue = (long) (byteArrayToEncode[blockPosition + 0] & 0xff) << 32
					| (long) (byteArrayToEncode[blockPosition + 1] & 0xff) << 24
					| (long) (byteArrayToEncode[blockPosition + 2] & 0xff) << 16
					| (long) (byteArrayToEncode[blockPosition + 3] & 0xff) << 8;
			encodedCharArray[bitPosition + 0] = BASE32_CODE_ARRAY[(int) (decimalValue >> 35) & 0x1f];
			encodedCharArray[bitPosition + 1] = BASE32_CODE_ARRAY[(int) (decimalValue >> 30) & 0x1f];
			encodedCharArray[bitPosition + 2] = BASE32_CODE_ARRAY[(int) (decimalValue >> 25) & 0x1f];
			encodedCharArray[bitPosition + 3] = BASE32_CODE_ARRAY[(int) (decimalValue >> 20) & 0x1f];
			encodedCharArray[bitPosition + 4] = BASE32_CODE_ARRAY[(int) (decimalValue >> 15) & 0x1f];
			encodedCharArray[bitPosition + 5] = BASE32_CODE_ARRAY[(int) (decimalValue >> 10) & 0x1f];
			encodedCharArray[bitPosition + 6] = BASE32_CODE_ARRAY[(int) (decimalValue >> 5) & 0x1f];
			encodedCharArray[bitPosition + 7] = 'P';
		} else if (blockPosition + 3 == byteArrayToEncode.length) {
			decimalValue = (long) (byteArrayToEncode[blockPosition + 0] & 0xff) << 32
					| (long) (byteArrayToEncode[blockPosition + 1] & 0xff) << 24
					| (long) (byteArrayToEncode[blockPosition + 2] & 0xff) << 16;
			encodedCharArray[bitPosition + 0] = BASE32_CODE_ARRAY[(int) (decimalValue >> 35) & 0x1f];
			encodedCharArray[bitPosition + 1] = BASE32_CODE_ARRAY[(int) (decimalValue >> 30) & 0x1f];
			encodedCharArray[bitPosition + 2] = BASE32_CODE_ARRAY[(int) (decimalValue >> 25) & 0x1f];
			encodedCharArray[bitPosition + 3] = BASE32_CODE_ARRAY[(int) (decimalValue >> 20) & 0x1f];
			encodedCharArray[bitPosition + 4] = BASE32_CODE_ARRAY[(int) (decimalValue >> 15) & 0x1f];
			encodedCharArray[bitPosition + 5] = '=';
			encodedCharArray[bitPosition + 6] = '=';
			encodedCharArray[bitPosition + 7] = '=';
		} else if (blockPosition + 2 == byteArrayToEncode.length) {
			decimalValue = (long) (byteArrayToEncode[blockPosition + 0] & 0xff) << 32
					| (long) (byteArrayToEncode[blockPosition + 1] & 0xff) << 24;
			encodedCharArray[bitPosition + 0] = BASE32_CODE_ARRAY[(int) (decimalValue >> 35) & 0x1f];
			encodedCharArray[bitPosition + 1] = BASE32_CODE_ARRAY[(int) (decimalValue >> 30) & 0x1f];
			encodedCharArray[bitPosition + 2] = BASE32_CODE_ARRAY[(int) (decimalValue >> 25) & 0x1f];
			encodedCharArray[bitPosition + 3] = BASE32_CODE_ARRAY[(int) (decimalValue >> 20) & 0x1f];
			encodedCharArray[bitPosition + 4] = '=';
			encodedCharArray[bitPosition + 5] = '=';
			encodedCharArray[bitPosition + 6] = '=';
			encodedCharArray[bitPosition + 7] = '=';
		} else if (blockPosition + 1 == byteArrayToEncode.length) {
			decimalValue = (long) (byteArrayToEncode[blockPosition + 0] & 0xff) << 32;
			encodedCharArray[bitPosition + 0] = BASE32_CODE_ARRAY[(int) (decimalValue >> 35) & 0x1f];
			encodedCharArray[bitPosition + 1] = BASE32_CODE_ARRAY[(int) (decimalValue >> 30) & 0x1f];
			encodedCharArray[bitPosition + 2] = '=';
			encodedCharArray[bitPosition + 3] = '=';
			encodedCharArray[bitPosition + 4] = '=';
			encodedCharArray[bitPosition + 5] = '=';
			encodedCharArray[bitPosition + 6] = '=';
			encodedCharArray[bitPosition + 7] = '=';
		}
		return new String(encodedCharArray);
	}
}
