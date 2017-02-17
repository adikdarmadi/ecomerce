package com.ecomerce.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.ecomerce.util.DateUtil;

/**
 * The Class DateUtilTest.
 * 
 * @author Adik
 */
public class DateUtilTest {

	@Test
	public void testGetAge() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateInString = "30-06-1976 10:20:56";
		Date birthDate = sdf.parse(dateInString);
		int result = DateUtil.getAge(birthDate);
		assertEquals(result, 39);
	}

	@Test
	public void testGetMonthOf2Date() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String dateInString1 = "30-06-2016 10:20:56";
		Date date1 = sdf.parse(dateInString1);
		String dateInString2 = "30-05-2016 10:20:56";
		Date date2 = sdf.parse(dateInString2);
		int result = DateUtil.getMonthOf2Date(date1, date2);
		assertEquals(result, 1);
	}
}
