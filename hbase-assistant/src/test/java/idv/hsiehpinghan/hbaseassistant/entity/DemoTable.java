package idv.hsiehpinghan.hbaseassistant.entity;

import idv.hsiehpinghan.collectionutility.utility.ArrayUtility;
import idv.hsiehpinghan.datatypeutility.utility.ByteUtility;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;
import idv.hsiehpinghan.hbaseassistant.utility.ByteConvertUtility;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;

public class DemoTable extends HBaseTable {
	private static final byte[] SPACE = ByteUtility.SINGLE_SPACE_BYTE_ARRAY;
	private RowKey rowKey;
	private SimpleFamily simpleFamily;
	private CombinedFamily combinedFamily;
	private ComplexFamily complexFamily;

	@Override
	public HBaseRowKey getRowKey() {
		return rowKey;
	}

	@Override
	public void setRowKey(HBaseRowKey rowKey) {
		this.rowKey = (RowKey) rowKey;
	}

	public SimpleFamily getSimpleFamily() {
		if (simpleFamily == null) {
			simpleFamily = this.new SimpleFamily(this);
		}
		return simpleFamily;
	}

	public CombinedFamily getCombinedFamily() {
		if (combinedFamily == null) {
			combinedFamily = this.new CombinedFamily(this);
		}
		return combinedFamily;
	}

	public ComplexFamily getComplexFamily() {
		if (complexFamily == null) {
			complexFamily = this.new ComplexFamily(this);
		}
		return complexFamily;
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

		public RowKey(DemoTable entity) {
			super(entity);
		}

		public RowKey(byte[] bytes, DemoTable entity) {
			super(entity);
			setBytes(bytes);
		}

		public RowKey(String stockCode, Date date, DemoTable entity) {
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

	public class SimpleFamily extends HBaseColumnFamily {
		public static final String USER_NAME = "userName";
		public static final String AGE = "age";

		private SimpleFamily(DemoTable entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<SimpleQualifier> getQualifiers() {
			return (Set<SimpleQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public String getUserName() {
			HBaseColumnQualifier qual = new SimpleQualifier(USER_NAME);
			SimpleValue val = (SimpleValue) super.getLatestValue(qual);
			if (val == null) {
				return null;
			}
			return val.getAsString();
		}

		public void setUserName(Date ver, String userName) {
			SimpleQualifier qual = new SimpleQualifier(USER_NAME);
			SimpleValue val = new SimpleValue();
			val.set(userName);
			add(qual, ver, val);
		}

		public Integer getAge() {
			HBaseColumnQualifier qual = new SimpleQualifier(AGE);
			SimpleValue val = (SimpleValue) super.getLatestValue(qual);
			if (val == null) {
				return null;
			}
			return val.getAsInteger();
		}

		public void setAge(Date ver, Integer age) {
			SimpleQualifier qual = new SimpleQualifier(AGE);
			SimpleValue val = new SimpleValue();
			val.set(age);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new SimpleQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new SimpleValue(bytes);
		}

		public class SimpleQualifier extends HBaseColumnQualifier {
			public SimpleQualifier() {
				super();
			}

			public SimpleQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public SimpleQualifier(String columnName) {
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

		public class SimpleValue extends HBaseValue {
			public SimpleValue() {
				super();
			}

			public SimpleValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public Integer getAsInteger() {
				return ByteConvertUtility.getIntegerFromBytes(getBytes());
			}

			public void set(Integer value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}

			public String getAsString() {
				return ByteConvertUtility.getStringFromBytes(getBytes());
			}

			public void set(String value) {
				setBytes(ByteConvertUtility.toBytes(value));
			}
		}
	}

	public class CombinedFamily extends HBaseColumnFamily {
		private CombinedFamily(DemoTable entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<CombinedQualifier> getQualifiers() {
			return (Set<CombinedQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public BigDecimal get(String elementId, Date instant) {
			HBaseColumnQualifier qual = new CombinedQualifier(elementId,
					instant);
			CombinedValue val = (CombinedValue) super.getLatestValue(qual);
			if (val == null) {
				return null;
			}
			return val.getValue();
		}

		public void set(String elementId, Date instant, Date ver,
				BigDecimal value) {
			HBaseColumnQualifier qual = new CombinedQualifier(elementId,
					instant);
			CombinedValue val = new CombinedValue(value);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new CombinedQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new CombinedValue(bytes);
		}

		public class CombinedQualifier extends HBaseColumnQualifier {
			private static final int ELEMENT_ID_LENGTH = 300;
			private static final int INSTANT_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
			private static final int ELEMENT_ID_BEGIN_INDEX = 0;
			private static final int ELEMENT_ID_END_INDEX = ELEMENT_ID_BEGIN_INDEX
					+ ELEMENT_ID_LENGTH;
			private static final int INSTANT_BEGIN_INDEX = ELEMENT_ID_END_INDEX + 1;
			private static final int INSTANT_END_INDEX = INSTANT_BEGIN_INDEX
					+ INSTANT_LENGTH;

			public CombinedQualifier() {
				super();
			}

			public CombinedQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public CombinedQualifier(String elementId, Date instant) {
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

		public class CombinedValue extends HBaseValue {
			public CombinedValue() {
				super();
			}

			public CombinedValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public CombinedValue(BigDecimal value) {
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

	public class ComplexFamily extends HBaseColumnFamily {
		private ComplexFamily(DemoTable entity) {
			super(entity);
		}

		@SuppressWarnings("unchecked")
		public Set<ComplexQualifier> getQualifiers() {
			return (Set<ComplexQualifier>) (Object) super
					.getQualifierVersionValueMap().keySet();
		}

		public ComplexValue getComplexValue(String elementId, Date instant) {
			HBaseColumnQualifier qual = new ComplexQualifier(elementId, instant);
			return (ComplexValue) super.getLatestValue(qual);
		}

		public void setComplexValue(String elementId, Date instant, Date ver,
				String unitType, BigDecimal value) {
			HBaseColumnQualifier qual = new ComplexQualifier(elementId, instant);
			ComplexValue val = new ComplexValue(unitType, value);
			add(qual, ver, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new ComplexQualifier(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new ComplexValue(bytes);
		}

		public class ComplexQualifier extends HBaseColumnQualifier {
			private static final int ELEMENT_ID_LENGTH = 300;
			private static final int INSTANT_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
			private static final int ELEMENT_ID_BEGIN_INDEX = 0;
			private static final int ELEMENT_ID_END_INDEX = ELEMENT_ID_BEGIN_INDEX
					+ ELEMENT_ID_LENGTH;
			private static final int INSTANT_BEGIN_INDEX = ELEMENT_ID_END_INDEX + 1;
			private static final int INSTANT_END_INDEX = INSTANT_BEGIN_INDEX
					+ INSTANT_LENGTH;

			public ComplexQualifier() {
				super();
			}

			public ComplexQualifier(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public ComplexQualifier(String elementId, Date instant) {
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

		public class ComplexValue extends HBaseValue {
			private static final int UNIT_TYPE_LENGTH = 10;
			private static final int VALUE_LENGTH = 20;
			private static final int UNIT_TYPE_BEGIN_INDEX = 0;
			private static final int UNIT_TYPE_END_INDEX = UNIT_TYPE_BEGIN_INDEX
					+ UNIT_TYPE_LENGTH;
			private static final int VALUE_BEGIN_INDEX = UNIT_TYPE_END_INDEX + 1;
			private static final int VALUE_END_INDEX = VALUE_BEGIN_INDEX
					+ VALUE_LENGTH;

			public ComplexValue() {
				super();
			}

			public ComplexValue(byte[] bytes) {
				super();
				setBytes(bytes);
			}

			public ComplexValue(String unitType, BigDecimal value) {
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
}
