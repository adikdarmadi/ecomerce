package com.ecomerce.util;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.ecomerce.util.CommonUtil;

/**
 * The Class DateUtilTest.
 * 
 * @author Roberto
 */
public class CommonUtilTest {

	@Test
	public void testCheckEquality() {

		String object1 = "harkit";
		String object2 = "Harkit";
		assertFalse(CommonUtil.checkEquality(object1, object2));
	}

}
