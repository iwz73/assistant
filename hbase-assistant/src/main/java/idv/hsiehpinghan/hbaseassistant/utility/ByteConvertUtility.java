package idv.hsiehpinghan.hbaseassistant.utility;

import idv.hsiehpinghan.datatypeutility.utility.StringUtility;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.hadoop.hbase.util.Bytes;

public class ByteConvertUtility {
	private static final String DEFAULT_DATE_PATTERN = "yyyyMMdd";
	public static final int DEFAULT_DATE_PATTERN_LENGTH = 8;

	/**
	 * Convert String to bytes.
	 * 
	 * @param string
	 * @return
	 */
	public static byte[] toBytes(String string) {
		return Bytes.toBytes(string);
	}

	/**
	 * Get string from bytes.
	 * 
	 * @param bytes
	 * @return
	 */
	public static String getStringFromBytes(byte[] bytes) {
		return Bytes.toString(bytes);
	}

	/**
	 * Convert String to bytes.
	 * 
	 * @param string
	 * @param byteLength
	 * @return
	 */
	public static byte[] toBytes(String string, int byteLength) {
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
		return Bytes.toString(ArrayUtils.subarray(bytes, beginIndex, endIndex))
				.trim();
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
	 * Convert bigDecimal to bytes.
	 * 
	 * @param bigDecimal
	 * @param byteLength
	 * @return
	 */
	public static byte[] toBytes(BigDecimal bigDecimal, int byteLength) {
		if (bigDecimal == null) {
			return ByteConvertUtility.toBytes(StringUtility.EMPTY_STRING,
					byteLength);
		}
		return ByteConvertUtility.toBytes(bigDecimal.toString(), byteLength);
	}

	/**
	 * Get bigDecimal from bytes.
	 * 
	 * @param bytes
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 * @throws ParseException
	 */
	public static BigDecimal getBigDecimalFromBytes(byte[] bytes,
			int beginIndex, int endIndex) throws ParseException {
		String str = getStringFromBytes(bytes, beginIndex, endIndex);
		if (StringUtility.EMPTY_STRING.equals(str)) {
			return null;
		}
		return new BigDecimal(str);
	}
	
	private static byte[] toBytes(Date date, int byteLength, String datePattern) {
		if (date == null) {
			return ByteConvertUtility.toBytes(StringUtility.EMPTY_STRING,
					byteLength);
		}
		return ByteConvertUtility.toBytes(
				DateFormatUtils.format(date, datePattern), byteLength);
	}
	
	private static Date getDateFromBytes(byte[] bytes, int beginIndex,
			int endIndex, String datePattern) throws ParseException {
		String str = getStringFromBytes(bytes, beginIndex, endIndex);
		if (StringUtility.EMPTY_STRING.equals(str)) {
			return null;
		}
		return DateUtils.parseDate(str, datePattern);
	}
}
