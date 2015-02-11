package idv.hsiehpinghan.hbaseassistant.utility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import junit.framework.Assert;

import org.apache.commons.lang3.time.DateUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ByteConvertUtilityTest {
	private String nullString = null;
	private String string = "string";
	private int stringLength = 10;
	private int i = 1;
	private int intLength = 20;
	private Date nullDate = null;
	private Date date;
	private int dateLength = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
	private BigDecimal nullBigDecimal = null;
	private BigDecimal bigDecimal = new BigDecimal("2.2");
	private int bigDecimalLength = 30;
	private Integer nullInteger = null;
	private Integer integer = new Integer("3");
	private int integerLength = 40;
	private BigInteger nullBigInteger = null;
	private BigInteger bigInteger = new BigInteger("4");
	private int bigIntegerLength = 50;

	@BeforeClass
	public void beforeClass() throws Exception {
		date = DateUtils.parseDate("2020/01/01", "yyyy/MM/dd");
	}

	@Test
	public void stringToBytesFromBytes() {
		byte[] bytes;
		// Test null.
		bytes = ByteConvertUtility.toBytes(nullString);
		Assert.assertNull(ByteConvertUtility.getStringFromBytes(bytes));

		bytes = ByteConvertUtility.toBytes(nullString, stringLength);
		Assert.assertNull(ByteConvertUtility.getStringFromBytes(bytes, 0,
				stringLength));

		// Test not null.
		bytes = ByteConvertUtility.toBytes(string);
		Assert.assertEquals(string,
				ByteConvertUtility.getStringFromBytes(bytes));

		bytes = ByteConvertUtility.toBytes(string, stringLength);
		Assert.assertEquals(string,
				ByteConvertUtility.getStringFromBytes(bytes, 0, stringLength));
	}

	@Test
	public void intTest() {
		byte[] bytes;

		// Test not null.
		bytes = ByteConvertUtility.toBytes(i, intLength);
		Assert.assertEquals(i,
				ByteConvertUtility.getIntFromBytes(bytes, 0, intLength));
	}

	@Test
	public void dateTest() throws Exception {
		byte[] bytes;
		// Test null.
		bytes = ByteConvertUtility.toBytes(nullDate);
		Assert.assertNull(ByteConvertUtility.getDateFromBytes(bytes));

		bytes = ByteConvertUtility.toBytes(nullDate);
		Assert.assertNull(ByteConvertUtility.getDateFromBytes(bytes, 0,
				dateLength));

		// Test not null.
		bytes = ByteConvertUtility.toBytes(date);
		Assert.assertEquals(date, ByteConvertUtility.getDateFromBytes(bytes));

		bytes = ByteConvertUtility.toBytes(date);
		Assert.assertEquals(date,
				ByteConvertUtility.getDateFromBytes(bytes, 0, dateLength));
	}

	@Test
	public void bigDecimalTest() {
		byte[] bytes;
		// Test null.
		bytes = ByteConvertUtility.toBytes(nullBigDecimal);
		Assert.assertNull(ByteConvertUtility.getBigDecimalFromBytes(bytes));

		bytes = ByteConvertUtility.toBytes(nullBigDecimal, bigDecimalLength);
		Assert.assertNull(ByteConvertUtility.getBigDecimalFromBytes(bytes, 0,
				bigDecimalLength));

		// Test not null.
		bytes = ByteConvertUtility.toBytes(bigDecimal);
		Assert.assertEquals(bigDecimal,
				ByteConvertUtility.getBigDecimalFromBytes(bytes));

		bytes = ByteConvertUtility.toBytes(bigDecimal);
		Assert.assertEquals(bigDecimal, ByteConvertUtility
				.getBigDecimalFromBytes(bytes, 0, bigDecimalLength));
	}

	@Test
	public void IntegerTest() {
		byte[] bytes;
		// Test null.
		bytes = ByteConvertUtility.toBytes(nullInteger);
		Assert.assertNull(ByteConvertUtility.getIntegerFromBytes(bytes));

		bytes = ByteConvertUtility.toBytes(nullInteger, integerLength);
		Assert.assertNull(ByteConvertUtility.getIntegerFromBytes(bytes, 0,
				integerLength));

		// Test not null.
		bytes = ByteConvertUtility.toBytes(integer);
		Assert.assertEquals(integer,
				ByteConvertUtility.getIntegerFromBytes(bytes));

		bytes = ByteConvertUtility.toBytes(integer);
		Assert.assertEquals(integer,
				ByteConvertUtility.getIntegerFromBytes(bytes, 0, integerLength));
	}

	@Test
	public void BigIntegerTest() {
		byte[] bytes;
		// Test null.
		bytes = ByteConvertUtility.toBytes(nullBigInteger);
		Assert.assertNull(ByteConvertUtility.getBigIntegerFromBytes(bytes));

		bytes = ByteConvertUtility.toBytes(nullBigInteger, bigIntegerLength);
		Assert.assertNull(ByteConvertUtility.getBigIntegerFromBytes(bytes, 0,
				bigIntegerLength));

		// Test not null.
		bytes = ByteConvertUtility.toBytes(bigInteger);
		Assert.assertEquals(bigInteger,
				ByteConvertUtility.getBigIntegerFromBytes(bytes));

		bytes = ByteConvertUtility.toBytes(bigInteger);
		Assert.assertEquals(bigInteger, ByteConvertUtility
				.getBigIntegerFromBytes(bytes, 0, bigIntegerLength));
	}
}
