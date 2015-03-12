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
	private RowKey rowKey;
	private AFamily aFamily;
	private BFamily bFamily;
	private CFamily cFamily;
	private DFamily dFamily;

	@Override
	public HBaseRowKey getRowKey() {
		return rowKey;
	}

	@Override
	public void setRowKey(HBaseRowKey rowKey) {
		this.rowKey = (RowKey) rowKey;
	}

	public AFamily getAFamily() {
		if (aFamily == null) {
			aFamily = this.new AFamily(this);
		}
		return aFamily;
	}

	public BFamily getBFamily() {
		if (bFamily == null) {
			bFamily = this.new BFamily(this);
		}
		return bFamily;
	}

	public CFamily getCFamily() {
		if (cFamily == null) {
			cFamily = this.new CFamily(this);
		}
		return cFamily;
	}

	public DFamily getDFamily() {
		if (dFamily == null) {
			dFamily = this.new DFamily(this);
		}
		return dFamily;
	}

	public class RowKey extends HBaseRowKey {
		private static final int STOCK_CODE_LENGTH = 15;
		private static final int DATE_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
		private static final int STOCK_CODE_BEGIN_INDEX = 0;
		private static final int STOCK_CODE_END_INDEX = STOCK_CODE_BEGIN_INDEX
				+ STOCK_CODE_LENGTH;
		private static final int DATE_BEGIN_INDEX = STOCK_CODE_END_INDEX + 1;
		private static final int DATE_END_INDEX = DATE_BEGIN_INDEX
				+ DATE_LENGTH;

		public RowKey(TestTable2 entity) {
			super(entity);
		}

		public RowKey(byte[] bytes, TestTable2 entity) {
			super(entity);
			setBytes(bytes);
		}

		public RowKey(String stockCode, Date date, TestTable2 entity) {
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

	public class AFamily extends HBaseColumnFamily {
		public static final String STRING = "string";

		private AFamily(TestTable2 entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<AQualifier> getQualifiers() {
			return (Set<AQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public String getString() {
			HBaseColumnQualifier qual = new AQualifier(STRING);
			AValue val = (AValue) super.getLatestValue(qual);
			if (val == null) {
				return null;
			}
			return val.getAsString();
		}

		public void setString(Date ver, String string) {
			AQualifier qual = new AQualifier(STRING);
			AValue val = new AValue();
			val.set(string);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new AQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new AValue(bytes);
		}

		public class AQualifier extends HBaseColumnQualifier {
			public AQualifier() {
				super();
			}

			public AQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public AQualifier(String columnName) {
				super();
				setColumnName(columnName);
			}

			public String getColumnName() {
				return ByteConvertUtility.getStringFromBytes(getBytes());
			}

			public void setColumnName(String columnName) {
				byte[] bytes = ByteConvertUtility.toBytes(columnName);
				setBytes(bytes);
			}
		}

		public class AValue extends HBaseValue {
			public AValue() {
				super();
			}

			public AValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public String getAsString() {
				return ByteConvertUtility.getStringFromBytes(getBytes());
			}

			public void set(String value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}
		}
	}

	public class BFamily extends HBaseColumnFamily {
		private BFamily(TestTable2 entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<BQualifier> getQualifiers() {
			return (Set<BQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public BValue getBValue(String elementId, Date instant) {
			HBaseColumnQualifier qual = new BQualifier(elementId, instant);
			return (BValue) super.getLatestValue(qual);
		}

		public void setBValue(String elementId, Date instant, Date ver,
				String unitType, BigDecimal value) {
			HBaseColumnQualifier qual = new BQualifier(elementId, instant);
			BValue val = new BValue(unitType, value);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new BQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new BValue(bytes);
		}

		public class BQualifier extends HBaseColumnQualifier {
			private static final int ELEMENT_ID_LENGTH = 300;
			private static final int INSTANT_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
			private static final int ELEMENT_ID_BEGIN_INDEX = 0;
			private static final int ELEMENT_ID_END_INDEX = ELEMENT_ID_BEGIN_INDEX
					+ ELEMENT_ID_LENGTH;
			private static final int INSTANT_BEGIN_INDEX = ELEMENT_ID_END_INDEX + 1;
			private static final int INSTANT_END_INDEX = INSTANT_BEGIN_INDEX
					+ INSTANT_LENGTH;

			public BQualifier() {
				super();
			}

			public BQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public BQualifier(String elementId, Date instant) {
				super();
				byte[] elementIdBytes = ByteConvertUtility.toBytes(elementId,
						ELEMENT_ID_LENGTH);
				byte[] instantBytes = ByteConvertUtility.toBytes(instant);
				super.setBytes(ArrayUtility.addAll(elementIdBytes, SPACE,
						instantBytes));
			}

			public String getElementId() {
				return ByteConvertUtility.getStringFromBytes(getBytes(),
						ELEMENT_ID_BEGIN_INDEX, ELEMENT_ID_END_INDEX);
			}

			public void setElementId(String elementId) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(elementId,
						ELEMENT_ID_LENGTH);
				ArrayUtility.replace(bytes, subBytes, ELEMENT_ID_BEGIN_INDEX,
						ELEMENT_ID_END_INDEX);
			}

			public Date getInstant() {
				try {
					return ByteConvertUtility.getDateFromBytes(getBytes(),
							INSTANT_BEGIN_INDEX, INSTANT_END_INDEX);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}

			public void setInstant(Date instant) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(instant);
				ArrayUtility.replace(bytes, subBytes, INSTANT_BEGIN_INDEX,
						INSTANT_END_INDEX);
			}
		}

		public class BValue extends HBaseValue {
			private static final int UNIT_TYPE_LENGTH = 10;
			private static final int VALUE_LENGTH = 20;
			private static final int UNIT_TYPE_BEGIN_INDEX = 0;
			private static final int UNIT_TYPE_END_INDEX = UNIT_TYPE_BEGIN_INDEX
					+ UNIT_TYPE_LENGTH;
			private static final int VALUE_BEGIN_INDEX = UNIT_TYPE_END_INDEX + 1;
			private static final int VALUE_END_INDEX = VALUE_BEGIN_INDEX
					+ VALUE_LENGTH;

			public BValue() {
				super();
			}

			public BValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public BValue(String unitType, BigDecimal value) {
				super();
				byte[] unitTypeBytes = ByteConvertUtility.toBytes(unitType,
						UNIT_TYPE_LENGTH);
				byte[] valueBytes = ByteConvertUtility.toBytes(value,
						VALUE_LENGTH);
				super.setBytes(ArrayUtility.addAll(unitTypeBytes, SPACE,
						valueBytes));
			}

			public String getUnitType() {
				return ByteConvertUtility.getStringFromBytes(getBytes(),
						UNIT_TYPE_BEGIN_INDEX, UNIT_TYPE_END_INDEX);
			}

			public void setUnitType(String unitType) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(unitType,
						UNIT_TYPE_LENGTH);
				ArrayUtility.replace(bytes, subBytes, UNIT_TYPE_BEGIN_INDEX,
						UNIT_TYPE_END_INDEX);
			}

			public BigDecimal getValue() {
				return ByteConvertUtility.getBigDecimalFromBytes(getBytes(),
						VALUE_BEGIN_INDEX, VALUE_END_INDEX);
			}

			public void setValue(BigDecimal value) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(value,
						VALUE_LENGTH);
				ArrayUtility.replace(bytes, subBytes, VALUE_BEGIN_INDEX,
						VALUE_END_INDEX);
			}
		}
	}

	public class CFamily extends HBaseColumnFamily {
		private CFamily(TestTable2 entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<CQualifier> getQualifiers() {
			return (Set<CQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public BigDecimal get(String elementId, Date instant) {
			HBaseColumnQualifier qual = new CQualifier(elementId, instant);
			CValue val = (CValue) super.getLatestValue(qual);
			if (val == null) {
				return null;
			}
			return val.getValue();
		}

		public void set(String elementId, Date instant, Date ver,
				BigDecimal value) {
			HBaseColumnQualifier qual = new CQualifier(elementId, instant);
			CValue val = new CValue(value);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new CQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new CValue(bytes);
		}

		public class CQualifier extends HBaseColumnQualifier {
			private static final int ELEMENT_ID_LENGTH = 300;
			private static final int INSTANT_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
			private static final int ELEMENT_ID_BEGIN_INDEX = 0;
			private static final int ELEMENT_ID_END_INDEX = ELEMENT_ID_BEGIN_INDEX
					+ ELEMENT_ID_LENGTH;
			private static final int INSTANT_BEGIN_INDEX = ELEMENT_ID_END_INDEX + 1;
			private static final int INSTANT_END_INDEX = INSTANT_BEGIN_INDEX
					+ INSTANT_LENGTH;

			public CQualifier() {
				super();
			}

			public CQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public CQualifier(String elementId, Date instant) {
				super();
				byte[] elementIdBytes = ByteConvertUtility.toBytes(elementId,
						ELEMENT_ID_LENGTH);
				byte[] instantBytes = ByteConvertUtility.toBytes(instant);
				super.setBytes(ArrayUtility.addAll(elementIdBytes, SPACE,
						instantBytes));
			}

			public String getElementId() {
				return ByteConvertUtility.getStringFromBytes(getBytes(),
						ELEMENT_ID_BEGIN_INDEX, ELEMENT_ID_END_INDEX);
			}

			public void setElementId(String elementId) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(elementId,
						ELEMENT_ID_LENGTH);
				ArrayUtility.replace(bytes, subBytes, ELEMENT_ID_BEGIN_INDEX,
						ELEMENT_ID_END_INDEX);
			}

			public Date getInstant() {
				try {
					return ByteConvertUtility.getDateFromBytes(getBytes(),
							INSTANT_BEGIN_INDEX, INSTANT_END_INDEX);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}

			public void setInstant(Date instant) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(instant);
				ArrayUtility.replace(bytes, subBytes, INSTANT_BEGIN_INDEX,
						INSTANT_END_INDEX);
			}
		}

		public class CValue extends HBaseValue {
			public CValue() {
				super();
			}

			public CValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public CValue(BigDecimal value) {
				super();
				setValue(value);
			}

			public BigDecimal getValue() {
				return ByteConvertUtility.getBigDecimalFromBytes(getBytes());
			}

			public void setValue(BigDecimal value) {
				byte[] bytes = ByteConvertUtility.toBytes(value);
				setBytes(bytes);
			}
		}
	}

	public class DFamily extends HBaseColumnFamily {
		public static final String OPERATING_INCOME_OF_CURRENT_MONTH = "operatingIncomeOfCurrentMonth";
		public static final String OPERATING_INCOME_OF_DIFFERENT_PERCENT = "operatingIncomeOfDifferentPercent";
		public static final String OPERATING_INCOME_OF_COMMENT = "operatingIncomeOfComment";

		private DFamily(TestTable2 entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<DQualifier> getQualifiers() {
			return (Set<DQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public BigInteger getOperatingIncomeOfCurrentMonth(int year, int month) {
			HBaseColumnQualifier qual = new DQualifier(
					OPERATING_INCOME_OF_CURRENT_MONTH, year, month);
			DValue val = (DValue) super.getLatestValue(qual);
			if (val == null) {
				return null;
			}
			return val.getAsBigInteger();
		}

		public void setOperatingIncomeOfCurrentMonth(int year, int month,
				Date ver, BigInteger operatingIncomeOfCurrentMonth) {
			DQualifier qual = new DQualifier(OPERATING_INCOME_OF_CURRENT_MONTH,
					year, month);
			DValue val = new DValue();
			val.set(operatingIncomeOfCurrentMonth);
			add(qual, ver, val);
		}

		public BigDecimal getOperatingIncomeOfDifferentPercent(int year,
				int month) {
			HBaseColumnQualifier qual = new DQualifier(
					OPERATING_INCOME_OF_DIFFERENT_PERCENT, year, month);
			DValue val = (DValue) super.getLatestValue(qual);
			if (val == null) {
				return null;
			}
			return val.getAsBigDecimal();
		}

		public void setOperatingIncomeOfDifferentPercent(int year, int month,
				Date ver, BigDecimal operatingIncomeOfDifferentPercent) {
			DQualifier qual = new DQualifier(
					OPERATING_INCOME_OF_DIFFERENT_PERCENT, year, month);
			DValue val = new DValue();
			val.set(operatingIncomeOfDifferentPercent);
			add(qual, ver, val);
		}

		public String getOperatingIncomeOfComment(int year, int month) {
			HBaseColumnQualifier qual = new DQualifier(
					OPERATING_INCOME_OF_COMMENT, year, month);
			DValue val = (DValue) super.getLatestValue(qual);
			if (val == null) {
				return null;
			}
			return val.getAsString();
		}

		public void setOperatingIncomeOfComment(int year, int month, Date ver,
				String operatingIncomeOfComment) {
			DQualifier qual = new DQualifier(OPERATING_INCOME_OF_COMMENT, year,
					month);
			DValue val = new DValue();
			val.set(operatingIncomeOfComment);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new DQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new DValue(bytes);
		}

		public class DQualifier extends HBaseColumnQualifier {
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

			public DQualifier() {
				super();
			}

			public DQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public DQualifier(String columnName, int year, int month) {
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

		public class DValue extends HBaseValue {
			public DValue() {
				super();
			}

			public DValue(byte[] bytes) {
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
