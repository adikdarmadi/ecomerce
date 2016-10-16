package com.ecomerce.util;

import org.junit.Test;

import com.ecomerce.util.CurrencyUtil;

import static org.junit.Assert.*;

/**
 * The Class CurrencyUtilTest.
 * 
 * @author Roberto
 */
public class CurrencyUtilTest {

	@Test
	public void testGetPriceInINA() {

		double input = 10000000;
		assertNotNull(CurrencyUtil.getPriceInINA(input));
		assertEquals(CurrencyUtil.getPriceInINA(input), "Rp. 10.000.000,00");
	}
	
	@Test
	public void testGetPriceInUSD() {

		double input = 100.25;
		assertNotNull(CurrencyUtil.getPriceInUSD(input));
		assertEquals(CurrencyUtil.getPriceInUSD(input), "$100.25");
	}

}
