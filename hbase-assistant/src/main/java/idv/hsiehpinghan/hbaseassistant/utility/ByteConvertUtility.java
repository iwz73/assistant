package idv.hsiehpinghan.hbaseassistant.utility;

import idv.hsiehpinghan.datatypeutility.utility.StringUtility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.hadoop.hbase.util.Bytes;

public class ByteConvertUtility {
	private static final String EMPTY_STRING = StringUtility.EMPTY_STRING;
	private static final String DEFAULT_DATE_PATTERN = "yyyyMMdd";
	public static final int DEFAULT_DATE_PATTERN_LENGTH = 8;
	public static final int DEFAULT_BOOLEAN_LENGTH = 5;

	/**
	 * Convert String to bytes.
	 * 
	 * @param string
	 * @return
	 */
	public static byte[] toBytes(String string) {
		if (string == null) {
			return Bytes.toBytes(EMPTY_STRING);
		}
		return Bytes.toBytes(string);
	}

	/**
	 * Get string from bytes.
	 * 
	 * @param bytes
	 * @return
	 */
	public static String getStringFromBytes(byte[] bytes) {
		String str = Bytes.toString(bytes).trim();
		if (EMPTY_STRING.equals(str)) {
			return null;
		}
		return str;
	}

	/**
	 * Convert boolean to bytes.
	 * 
	 * @param bool
	 * @return
	 */
	public static byte[] toBytes(boolean bool) {
		String str = String.valueOf(bool);
		return toBytes(str, DEFAULT_BOOLEAN_LENGTH);
	}

	/**
	 * Convert boolean to bytes.
	 * 
	 * @param bool
	 * @param byteLength
	 * @return
	 */
	public static byte[] toBytes(boolean bool, int byteLength) {
		String str = String.valueOf(bool);
		return toBytes(str, byteLength);
	}

	/**
	 * Convert bool to bytes.
	 * 
	 * @param bool
	 * @return
	 */
	public static byte[] toBytes(Boolean bool) {
		return toBytes(bool, 0);
	}

	/**
	 * Convert bool to bytes.
	 * 
	 * @param bool
	 * @param byteLength
	 * @return
	 */
	public static byte[] toBytes(Boolean bool, int byteLength) {
		if (bool == null) {
			if (byteLength == 0) {
				return toBytes(StringUtility.EMPTY_STRING);
			} else {
				return toBytes(StringUtility.EMPTY_STRING, byteLength);
			}
		} else {
			if (byteLength == 0) {
				return toBytes(bool.toString());
			} else {
				return toBytes(bool.toString(), byteLength);
			}
		}
	}

	/**
	 * Get boolean from bytes.
	 * 
	 * @param bytes
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static Boolean getBooleanFromBytes(byte[] bytes, int beginIndex,
			int endIndex) {
		String str = null;
		if (beginIndex == 0 && endIndex == 0) {
			str = getStringFromBytes(bytes);
		} else {
			str = getStringFromBytes(bytes, beginIndex, endIndex);
		}
		if (str == null) {
			return null;
		}
		return Boolean.valueOf(str);
	}

	/**
	 * Get boolean from bytes.
	 * 
	 * @param bytes
	 * @return
	 */
	public static Boolean getBooleanFromBytes(byte[] bytes) {
		return getBooleanFromBytes(bytes, 0, 0);
	}

	/**
	 * Convert String to bytes.
	 * 
	 * @param string
	 * @param byteLength
	 * @return
	 */
	public static byte[] toBytes(String string, int byteLength) {
		if (string == null) {
			return Bytes
					.toBytes(StringUtils.leftPad(EMPTY_STRING, byteLength));
		}
		checkSize(string, byteLength);
		return Bytes.toBytes(StringUtils.leftPad(string, byteLength));
	}

	/**
	 * Get string from bytes.
	 * 
	 * @param bytes
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static String getStringFromBytes(byte[] bytes, int beginIndex,
			int endIndex) {
		String str = Bytes.toString(
				ArrayUtils.subarray(bytes, beginIndex, endIndex)).trim();
		if (EMPTY_STRING.equals(str)) {
			return null;
		} else {
			return str;
		}
	}

	/**
	 * Convert int to bytes.
	 * 
	 * @param integer
	 * @param byteLength
	 * @return
	 */
	public static byte[] toBytes(int integer, int byteLength) {
		String str = String.valueOf(integer);
		return toBytes(str, byteLength);
	}

	/**
	 * Get int from bytes.
	 * 
	 * @param bytes
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static int getIntFromBytes(byte[] bytes, int beginIndex, int endIndex) {
		String str = getStringFromBytes(bytes, beginIndex, endIndex);
		return Integer.valueOf(str);
	}

	/**
	 * Convert date to bytes.
	 * 
	 * @param date
	 * @return
	 */
	public static byte[] toBytes(Date date) {
		return toBytes(date, DEFAULT_DATE_PATTERN_LENGTH, DEFAULT_DATE_PATTERN);
	}

	/**
	 * Get date from bytes.
	 * 
	 * @param bytes
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFromBytes(byte[] bytes, int beginIndex,
			int endIndex) throws ParseException {
		return getDateFromBytes(bytes, beginIndex, endIndex,
				DEFAULT_DATE_PATTERN);
	}

	/**
	 * Get date from bytes.
	 * 
	 * @param bytes
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateFromBytes(byte[] bytes) throws ParseException {
		return getDateFromBytes(bytes, 0, 0);
	}

	/**
	 * Convert bigDecimal to bytes.
	 * 
	 * @param bigDecimal
	 * @return
	 */
	public static byte[] toBytes(BigDecimal bigDecimal) {
		return toBytes(bigDecimal, 0);
	}

	/**
	 * Convert bigDecimal to bytes.
	 * 
	 * @param bigDecimal
	 * @param byteLength
	 * @return
	 */
	public static byte[] toBytes(BigDecimal bigDecimal, int byteLength) {
		if (bigDecimal == null) {
			if (byteLength == 0) {
				return Bytes.toBytes(StringUtility.EMPTY_STRING);
			} else {
				return toBytes(StringUtility.EMPTY_STRING, byteLength);
			}
		} else {
			if (byteLength == 0) {
				return Bytes.toBytes(bigDecimal.toString());
			} else {
				return toBytes(bigDecimal.toString(), byteLength);
			}
		}
	}

	/**
	 * Convert integer to bytes.
	 * 
	 * @param integer
	 * @return
	 */
	public static byte[] toBytes(Integer integer) {
		return toBytes(integer, 0);
	}

	/**
	 * Convert integer to bytes.
	 * 
	 * @param integer
	 * @param byteLength
	 * @return
	 */
	public static byte[] toBytes(Integer integer, int byteLength) {
		if (integer == null) {
			if (byteLength == 0) {
				return toBytes(StringUtility.EMPTY_STRING);
			} else {
				return toBytes(StringUtility.EMPTY_STRING, byteLength);
			}
		} else {
			if (byteLength == 0) {
				return toBytes(integer.toString());
			} else {
				return toBytes(integer.toString(), byteLength);
			}
		}
	}

	/**
	 * Convert bigInteger to bytes.
	 * 
	 * @param bigInteger
	 * @return
	 */
	public static byte[] toBytes(BigInteger bigInteger) {
		return toBytes(bigInteger, 0);
	}

	/**
	 * Convert bigInteger to bytes.
	 * 
	 * @param bigInteger
	 * @param byteLength
	 * @return
	 */
	public static byte[] toBytes(BigInteger bigInteger, int byteLength) {
		if (bigInteger == null) {
			if (byteLength == 0) {
				return Bytes.toBytes(StringUtility.EMPTY_STRING);
			} else {
				return toBytes(StringUtility.EMPTY_STRING, byteLength);
			}
		} else {
			if (byteLength == 0) {
				return Bytes.toBytes(bigInteger.toString());
			} else {
				return toBytes(bigInteger.toString(), byteLength);
			}
		}
	}

	/**
	 * Get bigDecimal from bytes.
	 * 
	 * @param bytes
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static BigDecimal getBigDecimalFromBytes(byte[] bytes,
			int beginIndex, int endIndex) {
		String str = null;
		if (beginIndex == 0 && endIndex == 0) {
			str = getStringFromBytes(bytes);
		} else {
			str = getStringFromBytes(bytes, beginIndex, endIndex);
		}
		if (str == null) {
			return null;
		}
		return new BigDecimal(str);
	}

	/**
	 * Get bigDecimal from bytes.
	 * 
	 * @param bytes
	 * @return
	 */
	public static BigDecimal getBigDecimalFromBytes(byte[] bytes) {
		return getBigDecimalFromBytes(bytes, 0, 0);
	}

	/**
	 * Get integer from bytes.
	 * 
	 * @param bytes
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static Integer getIntegerFromBytes(byte[] bytes, int beginIndex,
			int endIndex) {
		String str = null;
		if (beginIndex == 0 && endIndex == 0) {
			str = getStringFromBytes(bytes);
		} else {
			str = getStringFromBytes(bytes, beginIndex, endIndex);
		}
		if (str == null) {
			return null;
		}
		return Integer.valueOf(str);
	}

	/**
	 * Get integer from bytes.
	 * 
	 * @param bytes
	 * @return
	 */
	public static Integer getIntegerFromBytes(byte[] bytes) {
		return getIntegerFromBytes(bytes, 0, 0);
	}

	/**
	 * Get bigInteger from bytes.
	 * 
	 * @param bytes
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static BigInteger getBigIntegerFromBytes(byte[] bytes,
			int beginIndex, int endIndex) {
		String str = null;
		if (beginIndex == 0 && endIndex == 0) {
			str = getStringFromBytes(bytes);
		} else {
			str = getStringFromBytes(bytes, beginIndex, endIndex);
		}
		if (str == null) {
			return null;
		}
		return new BigInteger(str);
	}

	/**
	 * Get bigInteger from bytes.
	 * 
	 * @param bytes
	 * @return
	 */
	public static BigInteger getBigIntegerFromBytes(byte[] bytes) {
		return getBigIntegerFromBytes(bytes, 0, 0);
	}

	private static byte[] toBytes(Date date, int byteLength, String datePattern) {
		if (date == null) {
			return toBytes(EMPTY_STRING, byteLength);
		}
		String str = DateFormatUtils.format(date, datePattern);
		return toBytes(str, byteLength);
	}

	private static Date getDateFromBytes(byte[] bytes, int beginIndex,
			int endIndex, String datePattern) throws ParseException {
		String str = null;
		if (beginIndex == 0 && endIndex == 0) {
			str = getStringFromBytes(bytes);
		} else {
			str = getStringFromBytes(bytes, beginIndex, endIndex);
		}
		if (str == null) {
			return null;
		}
		return DateUtils.parseDate(str, datePattern);
	}

	private static void checkSize(String string, int byteLength) {
		int length = string.length();
		if (length > byteLength) {
			throw new RuntimeException("String(" + string + ")'s size("
					+ length + ") bigger than " + byteLength + " !!!");
		}
	}

}
