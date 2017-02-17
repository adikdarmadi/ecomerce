package com.ecomerce.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.ecomerce.util.IndonesianNumberToWords;

/**
 * The Class DateUtilTest.
 * 
 * @author Adik
 */
public class IndonesiaNumberToWordsTest {

	@Test
	public void testConvert() {
		long input = 92803;
		String result = IndonesianNumberToWords.convert(input);
		assertEquals(result, "sembilan puluh dua ribu delapan ratus tiga");
	}
}
