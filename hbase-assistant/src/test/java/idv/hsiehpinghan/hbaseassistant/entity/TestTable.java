package idv.hsiehpinghan.hbaseassistant.entity;

import idv.hsiehpinghan.collectionutility.utility.ArrayUtility;
import idv.hsiehpinghan.datatypeutility.utility.ByteUtility;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;
import idv.hsiehpinghan.hbaseassistant.enumeration.Enumeration;
import idv.hsiehpinghan.hbaseassistant.utility.ByteConvertUtility;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public class TestTable extends HBaseTable {
	private static final byte[] SPACE = ByteUtility.SINGLE_SPACE_BYTE_ARRAY;
	private RowKey rowKey;
	private ColumnNameFamily columnNameFamily;
	private ValuesFamily valuesFamily;
	private ValueFamily valueFamily;
	private QualifierColumnNameFamily qualifierColumnNameFamily;

	@Override
	public HBaseRowKey getRowKey() {
		return rowKey;
	}

	@Override
	public void setRowKey(HBaseRowKey rowKey) {
		this.rowKey = (RowKey) rowKey;
	}

	public ColumnNameFamily getColumnNameFamily() {
		if (columnNameFamily == null) {
			columnNameFamily = this.new ColumnNameFamily(this);
		}
		return columnNameFamily;
	}

	public ValuesFamily getValuesFamily() {
		if (valuesFamily == null) {
			valuesFamily = this.new ValuesFamily(this);
		}
		return valuesFamily;
	}

	public ValueFamily getValueFamily() {
		if (valueFamily == null) {
			valueFamily = this.new ValueFamily(this);
		}
		return valueFamily;
	}

	public QualifierColumnNameFamily getQualifierColumnNameFamily() {
		if (qualifierColumnNameFamily == null) {
			qualifierColumnNameFamily = this.new QualifierColumnNameFamily(this);
		}
		return qualifierColumnNameFamily;
	}

	public class RowKey extends HBaseRowKey {
		public RowKey(TestTable entity) {
			super(entity);
		}

		public RowKey(byte[] bytes, TestTable entity) {
			super(entity);
			setBytes(bytes);
		}

		public RowKey(String stockCode, TestTable entity) {
			super(entity);
			setStockCode(stockCode);
		}

		public String getStockCode() {
			return ByteConvertUtility.getStringFromBytes(getBytes());
		}

		public void setStockCode(String stockCode) {
			byte[] bytes = ByteConvertUtility.toBytes(stockCode);
			setBytes(bytes);
		}
	}

	public class ColumnNameFamily extends HBaseColumnFamily {
		public static final String ENUMERATION = "enumeration";
		public static final String STRING = "string";

		private ColumnNameFamily(TestTable entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<ColumnNameQualifier> getQualifiers() {
			return (Set<ColumnNameQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public Enumeration getEnumeration() {
			HBaseColumnQualifier qual = new ColumnNameQualifier(ENUMERATION);
			ColumnNameValue val = (ColumnNameValue) super.getLatestValue(qual);
			return val.getAsEnumeration();
		}

		public void setEnumeration(Date ver, Enumeration enumeration) {
			ColumnNameQualifier qual = new ColumnNameQualifier(ENUMERATION);
			ColumnNameValue val = new ColumnNameValue();
			val.set(enumeration);
			add(qual, ver, val);
		}

		public String getString() {
			HBaseColumnQualifier qual = new ColumnNameQualifier(STRING);
			ColumnNameValue val = (ColumnNameValue) super.getLatestValue(qual);
			return val.getAsString();
		}

		public void setString(Date ver, String string) {
			ColumnNameQualifier qual = new ColumnNameQualifier(STRING);
			ColumnNameValue val = new ColumnNameValue();
			val.set(string);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new ColumnNameQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new ColumnNameValue(bytes);
		}

		public class ColumnNameQualifier extends HBaseColumnQualifier {
			public ColumnNameQualifier() {
				super();
			}

			public ColumnNameQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public ColumnNameQualifier(String columnName) {
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

		public class ColumnNameValue extends HBaseValue {
			public ColumnNameValue() {
				super();
			}

			public ColumnNameValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public String getAsString() {
				return ByteConvertUtility.getStringFromBytes(getBytes());
			}

			public void set(String value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}

			public Enumeration getAsEnumeration() {
				return Enumeration.valueOf(ByteConvertUtility
						.getStringFromBytes(getBytes()));
			}

			public void set(Enumeration value) {
				setBytes(ByteConvertUtility.toBytes(value.name()));
			}
		}
	}

	public class ValuesFamily extends HBaseColumnFamily {
		private ValuesFamily(TestTable entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<ValuesQualifier> getQualifiers() {
			return (Set<ValuesQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public ValuesValue getValuesValue(String elementId,
				Enumeration enumeration, Date instant) {
			HBaseColumnQualifier qual = new ValuesQualifier(elementId,
					enumeration, instant);
			return (ValuesValue) super.getLatestValue(qual);
		}

		public void setValuesValue(String elementId, Enumeration enumeration,
				Date instant, Date ver, String unitType, BigDecimal value) {
			HBaseColumnQualifier qual = new ValuesQualifier(elementId,
					enumeration, instant);
			ValuesValue val = new ValuesValue(unitType, value);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new ValuesQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new ValuesValue(bytes);
		}

		public class ValuesQualifier extends HBaseColumnQualifier {
			private static final int ELEMENT_ID_LENGTH = 300;
			private static final int ENUMERATION_LENGTH = 10;
			private static final int INSTANT_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
			private static final int ELEMENT_ID_BEGIN_INDEX = 0;
			private static final int ELEMENT_ID_END_INDEX = ELEMENT_ID_BEGIN_INDEX
					+ ELEMENT_ID_LENGTH;
			private static final int ENUMERATION_BEGIN_INDEX = ELEMENT_ID_END_INDEX + 1;
			private static final int ENUMERATION_END_INDEX = ENUMERATION_BEGIN_INDEX
					+ ENUMERATION_LENGTH;
			private static final int INSTANT_BEGIN_INDEX = ENUMERATION_END_INDEX + 1;
			private static final int INSTANT_END_INDEX = INSTANT_BEGIN_INDEX
					+ INSTANT_LENGTH;

			public ValuesQualifier() {
				super();
			}

			public ValuesQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public ValuesQualifier(String elementId, Enumeration enumeration,
					Date instant) {
				super();
				byte[] elementIdBytes = ByteConvertUtility.toBytes(elementId,
						ELEMENT_ID_LENGTH);
				byte[] enumerationBytes = ByteConvertUtility.toBytes(
						enumeration.name(), ENUMERATION_LENGTH);
				byte[] instantBytes = ByteConvertUtility.toBytes(instant);
				super.setBytes(ArrayUtility.addAll(elementIdBytes, SPACE,
						enumerationBytes, SPACE, instantBytes));
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

			public Enumeration getEnumeration() {
				return Enumeration
						.valueOf(ByteConvertUtility.getStringFromBytes(
								getBytes(), ENUMERATION_BEGIN_INDEX,
								ENUMERATION_END_INDEX));
			}

			public void setEnumeration(Enumeration enumeration) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(
						enumeration.name(), ENUMERATION_LENGTH);
				ArrayUtility.replace(bytes, subBytes, ENUMERATION_BEGIN_INDEX,
						ENUMERATION_END_INDEX);
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

		public class ValuesValue extends HBaseValue {
			private static final int UNIT_TYPE_LENGTH = 10;
			private static final int VALUE_LENGTH = 20;
			private static final int UNIT_TYPE_BEGIN_INDEX = 0;
			private static final int UNIT_TYPE_END_INDEX = UNIT_TYPE_BEGIN_INDEX
					+ UNIT_TYPE_LENGTH;
			private static final int VALUE_BEGIN_INDEX = UNIT_TYPE_END_INDEX + 1;
			private static final int VALUE_END_INDEX = VALUE_BEGIN_INDEX
					+ VALUE_LENGTH;

			public ValuesValue() {
				super();
			}

			public ValuesValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public ValuesValue(String unitType, BigDecimal value) {
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

	public class ValueFamily extends HBaseColumnFamily {
		private ValueFamily(TestTable entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<ValueQualifier> getQualifiers() {
			return (Set<ValueQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public BigDecimal get(String elementId, Enumeration enumeration,
				Date instant) {
			HBaseColumnQualifier qual = new ValueQualifier(elementId,
					enumeration, instant);
			ValueValue val = (ValueValue) super.getLatestValue(qual);
			return val.getValue();
		}

		public void set(String elementId, Enumeration enumeration,
				Date instant, Date ver, BigDecimal value) {
			HBaseColumnQualifier qual = new ValueQualifier(elementId,
					enumeration, instant);
			ValueValue val = new ValueValue(value);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new ValueQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new ValueValue(bytes);
		}

		public class ValueQualifier extends HBaseColumnQualifier {
			private static final int ELEMENT_ID_LENGTH = 300;
			private static final int ENUMERATION_LENGTH = 10;
			private static final int INSTANT_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
			private static final int ELEMENT_ID_BEGIN_INDEX = 0;
			private static final int ELEMENT_ID_END_INDEX = ELEMENT_ID_BEGIN_INDEX
					+ ELEMENT_ID_LENGTH;
			private static final int ENUMERATION_BEGIN_INDEX = ELEMENT_ID_END_INDEX + 1;
			private static final int ENUMERATION_END_INDEX = ENUMERATION_BEGIN_INDEX
					+ ENUMERATION_LENGTH;
			private static final int INSTANT_BEGIN_INDEX = ENUMERATION_END_INDEX + 1;
			private static final int INSTANT_END_INDEX = INSTANT_BEGIN_INDEX
					+ INSTANT_LENGTH;

			public ValueQualifier() {
				super();
			}

			public ValueQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public ValueQualifier(String elementId, Enumeration enumeration,
					Date instant) {
				super();
				byte[] elementIdBytes = ByteConvertUtility.toBytes(elementId,
						ELEMENT_ID_LENGTH);
				byte[] enumerationBytes = ByteConvertUtility.toBytes(
						enumeration.name(), ENUMERATION_LENGTH);
				byte[] instantBytes = ByteConvertUtility.toBytes(instant);
				super.setBytes(ArrayUtility.addAll(elementIdBytes, SPACE,
						enumerationBytes, SPACE, instantBytes));
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

			public Enumeration getEnumeration() {
				return Enumeration
						.valueOf(ByteConvertUtility.getStringFromBytes(
								getBytes(), ENUMERATION_BEGIN_INDEX,
								ENUMERATION_END_INDEX));
			}

			public void setEnumeration(Enumeration enumeration) {
				byte[] bytes = getBytes();
				byte[] subBytes = ByteConvertUtility.toBytes(
						enumeration.name(), ENUMERATION_LENGTH);
				ArrayUtility.replace(bytes, subBytes, ENUMERATION_BEGIN_INDEX,
						ENUMERATION_END_INDEX);
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

		public class ValueValue extends HBaseValue {
			public ValueValue() {
				super();
			}

			public ValueValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public ValueValue(BigDecimal value) {
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

	public class QualifierColumnNameFamily extends HBaseColumnFamily {
		public static final String OPERATING_INCOME_OF_CURRENT_MONTH = "operatingIncomeOfCurrentMonth";
		public static final String OPERATING_INCOME_OF_DIFFERENT_PERCENT = "operatingIncomeOfDifferentPercent";
		public static final String OPERATING_INCOME_OF_COMMENT = "operatingIncomeOfComment";

		private QualifierColumnNameFamily(TestTable entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<QualifierColumnNameQualifier> getQualifiers() {
			return (Set<QualifierColumnNameQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public BigInteger getOperatingIncomeOfCurrentMonth(int year, int month) {
			HBaseColumnQualifier qual = new QualifierColumnNameQualifier(
					OPERATING_INCOME_OF_CURRENT_MONTH, year, month);
			QualifierColumnNameValue val = (QualifierColumnNameValue) super
					.getLatestValue(qual);
			return val.getAsBigInteger();
		}

		public void setOperatingIncomeOfCurrentMonth(int year, int month,
				Date ver, BigInteger operatingIncomeOfCurrentMonth) {
			QualifierColumnNameQualifier qual = new QualifierColumnNameQualifier(
					OPERATING_INCOME_OF_CURRENT_MONTH, year, month);
			QualifierColumnNameValue val = new QualifierColumnNameValue();
			val.set(operatingIncomeOfCurrentMonth);
			add(qual, ver, val);
		}

		public BigDecimal getOperatingIncomeOfDifferentPercent(int year,
				int month) {
			HBaseColumnQualifier qual = new QualifierColumnNameQualifier(
					OPERATING_INCOME_OF_DIFFERENT_PERCENT, year, month);
			QualifierColumnNameValue val = (QualifierColumnNameValue) super
					.getLatestValue(qual);
			return val.getAsBigDecimal();
		}

		public void setOperatingIncomeOfDifferentPercent(int year, int month,
				Date ver, BigDecimal operatingIncomeOfDifferentPercent) {
			QualifierColumnNameQualifier qual = new QualifierColumnNameQualifier(
					OPERATING_INCOME_OF_DIFFERENT_PERCENT, year, month);
			QualifierColumnNameValue val = new QualifierColumnNameValue();
			val.set(operatingIncomeOfDifferentPercent);
			add(qual, ver, val);
		}

		public String getOperatingIncomeOfComment(int year, int month) {
			HBaseColumnQualifier qual = new QualifierColumnNameQualifier(
					OPERATING_INCOME_OF_COMMENT, year, month);
			QualifierColumnNameValue val = (QualifierColumnNameValue) super
					.getLatestValue(qual);
			return val.getAsString();
		}

		public void setOperatingIncomeOfComment(int year, int month, Date ver,
				String operatingIncomeOfComment) {
			QualifierColumnNameQualifier qual = new QualifierColumnNameQualifier(
					OPERATING_INCOME_OF_COMMENT, year, month);
			QualifierColumnNameValue val = new QualifierColumnNameValue();
			val.set(operatingIncomeOfComment);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new QualifierColumnNameQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new QualifierColumnNameValue(bytes);
		}

		public class QualifierColumnNameQualifier extends HBaseColumnQualifier {
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

			public QualifierColumnNameQualifier() {
				super();
			}

			public QualifierColumnNameQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public QualifierColumnNameQualifier(String columnName, int year,
					int month) {
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

		public class QualifierColumnNameValue extends HBaseValue {
			public QualifierColumnNameValue() {
				super();
			}

			public QualifierColumnNameValue(byte[] bytes) {
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
