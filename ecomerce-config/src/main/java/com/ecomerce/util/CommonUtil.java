package com.ecomerce.util;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Common utility class.
 * 
 * @author Roberto
 */
public final class CommonUtil {

	/** The Logger. */
	private static final Log LOG = LogFactory.getLog(CommonUtil.class);

	/**
	 * To prevent instantiation of a new common util.
	 */
	private CommonUtil() {
		super();
	}

	/**
	 * Remove string qualifier used in the scripting code / GUI (client side).
	 * 
	 * @param expression
	 *            An expression with string qualifier (ex: ${orang.nama})
	 * @return An expression without string qualifier (ex: orang.nama)
	 */
	public static String trimExpression(String expression) {
		if (expression.charAt(0) == '$') {
			return expression.substring(2, expression.length() - 1);
		} else {
			return expression;
		}
	}

	/**
	 * Get value of a map entry or a bean.
	 * 
	 * @param source
	 *            A map or a bean
	 * @param property
	 *            If the source is map, this is the key, if the source is bean,
	 *            this is the bean's property.
	 * @return The value of a map entry or a bean's property.
	 */
	@SuppressWarnings("unchecked")
	public static Object getValue(Object source, String property) {
		Object value = null;
		if (source != null && StringUtils.isNotEmpty(property)) {
			if (source instanceof Map<?, ?>) {
				Map<String, Object> map = (Map<String, Object>) source;
				value = map.get(property);
			} else {
				try {
					value = PropertyUtils.getProperty(source, property);
				} catch (java.lang.Exception e) {
					LOG.error("Error when getting property: " + property
							+ " from source: " + source, e);
				}
			}
		}
		return value;
	}

	/**
	 * Format a value based on its type.
	 * 
	 * @param value
	 *            the value
	 * @return The formatted string
	 */
	public static String formatValue(Object value) {
		String result = "";
		if (value == null) {
			return result;
		}
		if (value instanceof Date) {
			result = DateUtil.formatDate((Date) value);
		} else {
			result = value.toString();
		}
		return result;
	}

	/**
	 * Check whether a string is null. If it is null, an empty string is
	 * returned. Otherwise, the string itself is returned.
	 * 
	 * @param string
	 * @return An empty string or the string itself.
	 */
	public static String checkNullString(String string) {
		if (string == null) {
			return "";
		}
		return string;
	}

	/**
	 * Check whether an object is null. If it is null, an empty string is
	 * returned. Otherwise, the string representation of the object is returned.
	 * 
	 * @param object
	 *            Object to convert to String
	 * @return
	 */
	public static String checkNullString(Object object) {
		if (object == null) {
			return "";
		}
		return object.toString();
	}

	/**
	 * Check whether an Integer object is null. If it is, Integer 0 is returned.
	 * Otherwise, the Integer object itself is returned.
	 * 
	 * @param integer
	 * @return An Integer object.
	 */
	public static Integer checkNullInteger(Integer integer) {
		if (integer == null) {
			return Integer.valueOf(0);
		}
		return integer;
	}

	/**
	 * Checking whether an object is equal to at least one of the objects given.
	 * 
	 * @param object
	 * @param objectsToCompare
	 * @return
	 */
	public static boolean checkEquality(Object object,
			Object... objectsToCompare) {
		if (object == null) {
			return false;
		}
		if (objectsToCompare == null || objectsToCompare.length == 0) {
			throw new IllegalArgumentException(
					"ObjectsToCompare cannot be null or empty.");
		}
		for (Object objectToCompare : objectsToCompare) {
			if (objectToCompare == null) {
				continue;
			}
			if (object.equals(objectToCompare)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checking whether the string is equal ignoring case with at least one of
	 * the string given.
	 * 
	 * @param string
	 * @param stringsToCompare
	 * @return
	 */
	public static boolean checkEqualityIgnoreCase(String string,
			String... stringsToCompare) {
		if (string == null) {
			throw new IllegalArgumentException("String cannot be null.");
		}
		if (stringsToCompare == null || stringsToCompare.length == 0) {
			throw new IllegalArgumentException(
					"StringsToCompare cannot be null or empty.");
		}
		for (String stringToCompare : stringsToCompare) {
			if (string.equalsIgnoreCase(stringToCompare)) {
				return true;
			}
		}
		return false;
	}
	
	  public static Boolean isNotNullOrEmpty(Object object) {
	        return !isNullOrEmpty(object);
	    }
	    
	    @SuppressWarnings("rawtypes")
		public static Boolean isNullOrEmpty(Object object) {
	        if (object == null) {
	            return true;
	        } else {
	            if (object instanceof Collection) {
	                if (((Collection) object).isEmpty()) {
	                    return true;
	                }
	            } else if (object instanceof AbstractMap) {
	                if (((AbstractMap) object).isEmpty()) {
	                    return true;
	                }
	            } else {
	                if (object.toString().trim().equals("")) {
	                    return true;
	                }
	            }
	            return false;
	        }
	    }
}
