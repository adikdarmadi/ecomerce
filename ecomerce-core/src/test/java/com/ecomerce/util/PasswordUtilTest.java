package com.ecomerce.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import com.ecomerce.util.PasswordUtil;

/**
 * The Class PasswordUtilTest.
 * 
 * @author Adik
 */
public class PasswordUtilTest {

	@Test
	public void testGeneratePassword() throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		PasswordUtil passwordUtil = new PasswordUtil();
		String password = "admin";
		String encryptedPassword = passwordUtil.encryptPassword(password);
		System.out.println("encryptedPassword (" + password + ") = "
				+ encryptedPassword);
		assertNotNull(encryptedPassword);
	}

	@Test
	public void testPasswordEquals() throws 
			IOException {
		PasswordUtil passwordUtil = new PasswordUtil();
		String password = "admin";
		String hashPassword = "9NEWRrb+A0sJ+41H+acQEy7rdY8=$GCZZyCy+Cs4=";		
		assertTrue(passwordUtil.isPasswordEqual(password, hashPassword));
	}

}
