package com.ecomerce.util;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.ecomerce.util.AgeCalculator;

/**
 * The Class AgeCalculatorTest.
 * 
 * @author Adik
 */
public class AgeCalculatorTest {

	//@Test
	public void testGetAge() throws ParseException {
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//		String dateInString = "11-05-1989 10:20:56";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy");
		String dateInString = "11-05-1989";
		Date birthDate = sdf.parse(dateInString);
		assertEquals(AgeCalculator.calculateAge(birthDate).getDays(), 0);
		assertEquals(AgeCalculator.calculateAge(birthDate).getMonths(), 0);
		assertEquals(AgeCalculator.calculateAge(birthDate).getYears(), 27);
	}
}
