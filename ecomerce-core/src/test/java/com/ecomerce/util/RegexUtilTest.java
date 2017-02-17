package com.ecomerce.util;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

import com.ecomerce.util.StringUtil;

/**
 * The Class RegexUtilTest.
 * 
 * @author Adik
 */
public class RegexUtilTest {

	@Test
	public void testValidateIndonesiaPhoneNumber() throws ParseException {
		assertTrue(StringUtil.validateIndonesiaPhoneNumber("082112345678"));
		assertTrue(StringUtil.validateIndonesiaPhoneNumber("0821 1234567"));
		assertTrue(StringUtil.validateIndonesiaPhoneNumber("021 1234567"));
	}
	
	@Test
	public void testValidateExampleNoRegistration() throws ParseException {
		assertTrue(StringUtil.validateNoRegistrationExample("REG000001"));
		assertTrue(StringUtil.validateNoRegistrationExample("REG000021"));
		assertTrue(StringUtil.validateNoRegistrationExample("REG123456"));
	}
	
}
