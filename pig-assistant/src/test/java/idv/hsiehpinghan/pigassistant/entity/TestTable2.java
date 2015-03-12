package idv.hsiehpinghan.pigassistant.entity;

import idv.hsiehpinghan.collectionutility.utility.ArrayUtility;
import idv.hsiehpinghan.datatypeutility.utility.ByteUtility;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;
import idv.hsiehpinghan.hbaseassistant.utility.ByteConvertUtility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public class TestTable2 extends HBaseTable {
	private static final byte[] SPACE = ByteUtility.SINGLE_SPACE_BYTE_ARRAY;
	private RowKey2 rowKey;
	private TestFamily testFamily;

	@Override
	public HBaseRowKey getRowKey() {
		return rowKey;
	}

	@Override
	public void setRowKey(HBaseRowKey rowKey) {
		this.rowKey = (RowKey2) rowKey;
	}

	public TestFamily getTestFamily() {
		if (testFamily == null) {
			testFamily = this.new TestFamily(this);
		}
		return testFamily;
	}

	public class RowKey2 extends HBaseRowKey {
		private static final int STOCK_CODE_LENGTH = 15;
		private static final int DATE_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
		private static final int STOCK_CODE_BEGIN_INDEX = 0;
		private static final int STOCK_CODE_END_INDEX = STOCK_CODE_BEGIN_INDEX
				+ STOCK_CODE_LENGTH;
		private static final int DATE_BEGIN_INDEX = STOCK_CODE_END_INDEX + 1;
		private static final int DATE_END_INDEX = DATE_BEGIN_INDEX
				+ DATE_LENGTH;

		public RowKey2(TestTable2 entity) {
			super(entity);
		}

		public RowKey2(byte[] bytes, TestTable2 entity) {
			super(entity);
			setBytes(bytes);
		}

		public RowKey2(String stockCode, Date date, TestTable2 entity) {
			super(entity);
			byte[] stockCodeBytes = ByteConvertUtility.toBytes(stockCode,
					STOCK_CODE_LENGTH);
			byte[] dateBytes = ByteConvertUtility.toBytes(date);
			super.setBytes(ArrayUtility
					.addAll(stockCodeBytes, SPACE, dateBytes));
		}

		public byte[] getFuzzyBytes(String stockCode, Date date) {
			byte[] stockCodeBytes;
			if (stockCode == null) {
				stockCodeBytes = ArrayUtility.getBytes(STOCK_CODE_LENGTH,
						ByteUtility.BYTE_ONE);
			} else {
				stockCodeBytes = ArrayUtility.getBytes(STOCK_CODE_LENGTH,
						ByteUtility.BYTE_ZERO);
			}
			byte[] dateBytes;
			if (date == null) {
				dateBytes = ArrayUtility.getBytes(DATE_LENGTH,
						ByteUtility.BYTE_ONE);
			} else {
				dateBytes = ArrayUtility.getBytes(DATE_LENGTH,
						ByteUtility.BYTE_ZERO);
			}
			return ArrayUtility.addAll(stockCodeBytes,
					ByteUtility.SINGLE_ZERO_BYTE_ARRAY, dateBytes);
		}

		public String getStockCode() {
			return ByteConvertUtility.getStringFromBytes(getBytes(),
					STOCK_CODE_BEGIN_INDEX, STOCK_CODE_END_INDEX);
		}

		public void setStockCode(String stockCode) {
			byte[] bytes = getBytes();
			byte[] subBytes = ByteConvertUtility.toBytes(stockCode,
					STOCK_CODE_LENGTH);
			ArrayUtility.replace(bytes, subBytes, STOCK_CODE_BEGIN_INDEX,
					STOCK_CODE_END_INDEX);
		}

		public Date getDate() {
			try {
				return ByteConvertUtility.getDateFromBytes(getBytes(),
						DATE_BEGIN_INDEX, DATE_END_INDEX);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}

		public void setDate(Date date) {
			byte[] bytes = getBytes();
			byte[] subBytes = ByteConvertUtility.toBytes(date);
			ArrayUtility.replace(bytes, subBytes, DATE_BEGIN_INDEX,
					DATE_END_INDEX);
		}
	}

	public class TestFamily extends HBaseColumnFamily {
		public static final String BIG_INTEGER = "bigInteger";
		public static final String BIG_DECIMAL = "bigDecimal";
		public static final String STRING = "string";

		private TestFamily(TestTable2 entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<TestQualifier> getQualifiers() {
			return (Set<TestQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public BigInteger getBigInteger(int year, int month) {
			HBaseColumnQualifier qual = new TestQualifier(BIG_INTEGER, year,
					month);
			TestValue val = (TestValue) super.getLatestValue(qual);
			if (val == null) {
				return null;
			}
			return val.getAsBigInteger();
		}

		public void setBigInteger(int year, int month, Date ver,
				BigInteger bigInteger) {
			TestQualifier qual = new TestQualifier(BIG_INTEGER, year, month);
			TestValue val = new TestValue();
			val.set(bigInteger);
			add(qual, ver, val);
		}

		public BigDecimal getBigDecimal(int year, int month) {
			HBaseColumnQualifier qual = new TestQualifier(BIG_DECIMAL, year,
					month);
			TestValue val = (TestValue) super.getLatestValue(qual);
			if (val == null) {
				return null;
			}
			return val.getAsBigDecimal();
		}

		public void setBigDecimal(int year, int month, Date ver,
				BigDecimal bigDecimal) {
			TestQualifier qual = new TestQualifier(BIG_DECIMAL, year, month);
			TestValue val = new TestValue();
			val.set(bigDecimal);
			add(qual, ver, val);
		}

		public String getString(int year, int month) {
			HBaseColumnQualifier qual = new TestQualifier(STRING, year, month);
			TestValue val = (TestValue) super.getLatestValue(qual);
			if (val == null) {
				return null;
			}
			return val.getAsString();
		}

		public void setString(int year, int month, Date ver, String string) {
			TestQualifier qual = new TestQualifier(STRING, year, month);
			TestValue val = new TestValue();
			val.set(string);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new TestQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new TestValue(bytes);
		}

		public class TestQualifier extends HBaseColumnQualifier {
			private static final int COLUMN_NAME_LENGTH = 100;
			private static final int YEAR_LENGTH = 4;
			private static final int MONTH_LENGTH = 2;
			private static final int COLUMN_NAME_BEGIN_INDEX = 0;
			private static final int COLUMN_NAME_END_INDEX = COLUMN_NAME_BEGIN_INDEX
					+ COLUMN_NAME_LENGTH;
			private static final int YEAR_BEGIN_INDEX = COLUMN_NAME_END_INDEX + 1;
			private static final int YEAR_END_INDEX = YEAR_BEGIN_INDEX
					+ YEAR_LENGTH;
			private static final int MONTH_BEGIN_INDEX = YEAR_END_INDEX + 1;
			private static final int MONTH_END_INDEX = MONTH_BEGIN_INDEX
					+ MONTH_LENGTH;

			public TestQualifier() {
				super();
			}

			public TestQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public TestQualifier(String columnName, int year, int month) {
				super();
				byte[] columnNameBytes = ByteConvertUtility.toBytes(columnName,
						COLUMN_NAME_LENGTH);
				byte[] yearBytes = ByteConvertUtility
						.toBytes(year, YEAR_LENGTH);
				byte[] monthBytes = ByteConvertUtility.toBytes(month,
						MONTH_LENGTH);
				super.setBytes(ArrayUtility.addAll(columnNameBytes, SPACE,
						yearBytes, SPACE, monthBytes));
			}

			public String getColumnName() {
				return ByteConvertUtility.getStringFromBytes(getBytes(),
						COLUMN_NAME_BEGIN_INDEX, COLUMN_NAME_END_INDEX);
			}

			public void setColumnName(String columnName) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(columnName,
						COLUMN_NAME_LENGTH);
				ArrayUtility.replace(bytes, subBytes, COLUMN_NAME_BEGIN_INDEX,
						COLUMN_NAME_END_INDEX);
			}

			public int getYear() {
				return ByteConvertUtility.getIntFromBytes(getBytes(),
						YEAR_BEGIN_INDEX, YEAR_END_INDEX);
			}

			public void setYear(int year) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(year, YEAR_LENGTH);
				ArrayUtility.replace(bytes, subBytes, YEAR_BEGIN_INDEX,
						YEAR_END_INDEX);
			}

			public int getMonth() {
				return ByteConvertUtility.getIntFromBytes(getBytes(),
						MONTH_BEGIN_INDEX, MONTH_END_INDEX);
			}

			public void setMonth(int month) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(month,
						MONTH_LENGTH);
				ArrayUtility.replace(bytes, subBytes, MONTH_BEGIN_INDEX,
						MONTH_END_INDEX);
			}
		}

		public class TestValue extends HBaseValue {
			public TestValue() {
				super();
			}

			public TestValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public BigInteger getAsBigInteger() {
				return ByteConvertUtility.getBigIntegerFromBytes(getBytes());
			}

			public void set(BigInteger value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}

			public String getAsString() {
				return ByteConvertUtility.getStringFromBytes(getBytes());
			}

			public void set(String value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}

			public BigDecimal getAsBigDecimal() {
				return ByteConvertUtility.getBigDecimalFromBytes(getBytes());
			}

			public void set(BigDecimal value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}
		}
	}
}
