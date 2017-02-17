package com.ecomerce.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Test;

import com.ecomerce.util.StringUtil;

/**
 * The Class AgeCalculatorTest.
 * 
 * @author Adik
 */
public class StringUtilTest {

	@Test
	public void testNumberFormat() throws ParseException {

		assertEquals(StringUtil.formatNumber("10", 4), "0010");
		assertEquals(StringUtil.formatNumber("6", 2), "06");
		
		assertEquals(StringUtil.formatNumber("16", 5), "00016");
	}
}
