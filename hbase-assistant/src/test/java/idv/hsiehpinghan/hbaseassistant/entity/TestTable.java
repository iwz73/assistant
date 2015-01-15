package idv.hsiehpinghan.hbaseassistant.entity;

import idv.hsiehpinghan.collectionutility.utility.ArrayUtility;
import idv.hsiehpinghan.datatypeutility.utility.ByteUtility;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;
import idv.hsiehpinghan.hbaseassistant.utility.ByteConvertUtility;

import java.text.ParseException;
import java.util.Date;

public class TestTable extends HBaseTable {
	private static final byte[] SPACE = ByteUtility.SINGLE_SPACE_BYTE_ARRAY;
	private TestFamily1 family1;
	private TestFamily2 family2;

	public TestTable() {
		super();
	}

	public TestFamily1 getFamily1() {
		if (family1 == null) {
			family1 = this.new TestFamily1(this);
		}
		return family1;
	}

	public TestFamily2 getFamily2() {
		if (family2 == null) {
			family2 = this.new TestFamily2(this);
		}
		return family2;
	}

	public class TestRowKey extends HBaseRowKey {
		private static final int KEY_DATE_1_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
		private static final int KEY_STRING_1_LENGTH = 20;
		private static final int KEY_INT_1_LENGTH = 20;

		private static final int KEY_DATE_1_BEGIN_INDEX = 0;
		private static final int KEY_DATE_1_END_INDEX = KEY_DATE_1_BEGIN_INDEX
				+ KEY_DATE_1_LENGTH;
		private static final int KEY_STRING_1_BEGIN_INDEX = KEY_DATE_1_END_INDEX + 1;
		private static final int KEY_STRING_1_END_INDEX = KEY_STRING_1_BEGIN_INDEX
				+ KEY_STRING_1_LENGTH;
		private static final int KEY_INT_1_BEGIN_INDEX = KEY_STRING_1_END_INDEX + 1;
		private static final int KEY_INT_1_END_INDEX = KEY_INT_1_BEGIN_INDEX
				+ KEY_INT_1_LENGTH;

		private Date keyDate1;
		private String keyString1;
		private int keyInt1;

		public TestRowKey(TestTable entity) {
			super(entity);
		}

		public TestRowKey(Date keyDate1, String keyString1, int keyInt1,
				TestTable entity) {
			super(entity);
			this.keyDate1 = keyDate1;
			this.keyString1 = keyString1;
			this.keyInt1 = keyInt1;
		}

		public TestRowKey(byte[] bytes, TestTable entity) {
			super(entity);
			fromBytes(bytes);
		}

		public Date getValueDate1() {
			return keyDate1;
		}

		public void setValueDate1(Date keyDate1) {
			this.keyDate1 = keyDate1;
		}

		public String getValueString1() {
			return keyString1;
		}

		public void setValueString1(String keyString1) {
			this.keyString1 = keyString1;
		}

		public int getValueInt1() {
			return keyInt1;
		}

		public void setValueInt1(int keyInt1) {
			this.keyInt1 = keyInt1;
		}

		@Override
		public byte[] toBytes() {
			byte[] keyDate1Bytes = ByteConvertUtility.toBytes(keyDate1);
			byte[] keyString1Bytes = ByteConvertUtility.toBytes(keyString1,
					KEY_STRING_1_LENGTH);
			byte[] keyInt1Bytes = ByteConvertUtility.toBytes(keyInt1,
					KEY_INT_1_LENGTH);
			return ArrayUtility.addAll(keyDate1Bytes, SPACE, keyString1Bytes,
					SPACE, keyInt1Bytes);
		}

		@Override
		public void fromBytes(byte[] bytes) {
			try {
				this.keyDate1 = ByteConvertUtility.getDateFromBytes(bytes,
						KEY_DATE_1_BEGIN_INDEX, KEY_DATE_1_END_INDEX);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			this.keyString1 = ByteConvertUtility.getStringFromBytes(bytes,
					KEY_STRING_1_BEGIN_INDEX, KEY_STRING_1_END_INDEX);
			this.keyInt1 = ByteConvertUtility.getIntFromBytes(bytes,
					KEY_INT_1_BEGIN_INDEX, KEY_INT_1_END_INDEX);
		}
	}

	public class TestFamily1 extends HBaseColumnFamily {
		private TestFamily1(TestTable entity) {
			super(entity);
		}

		public void add(String qual, Date date, Date valueDate1,
				String valueString1, int valueInt1) {
			HBaseColumnQualifier qualifier = this.new TestQualifier1(qual);
			TestValue1 val = this.new TestValue1(valueDate1, valueString1,
					valueInt1);
			add(qualifier, date, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new TestQualifier1(bytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] bytes) {
			return this.new TestValue1(bytes);
		}

		public class TestQualifier1 extends HBaseColumnQualifier {
			private String qual;

			public TestQualifier1() {
				super();
			}

			public TestQualifier1(String qual) {
				super();
				this.qual = qual;
			}

			public TestQualifier1(byte[] bytes) {
				super();
				fromBytes(bytes);
			}

			@Override
			public byte[] toBytes() {
				return ByteConvertUtility.toBytes(qual);
			}

			@Override
			public void fromBytes(byte[] bytes) {

				this.qual = ByteConvertUtility.getStringFromBytes(bytes);
			}

			public String getQual() {
				return qual;
			}

			public void setQual(String qual) {
				this.qual = qual;
			}
		}

		public class TestValue1 extends HBaseValue {
			private static final int VALUE_DATE_1_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
			private static final int VALUE_STRING_1_LENGTH = 20;
			private static final int VALUE_INT_1_LENGTH = 20;

			private static final int VALUE_DATE_1_BEGIN_INDEX = 0;
			private static final int VALUE_DATE_1_END_INDEX = VALUE_DATE_1_BEGIN_INDEX
					+ VALUE_DATE_1_LENGTH;
			private static final int VALUE_STRING_1_BEGIN_INDEX = VALUE_DATE_1_END_INDEX + 1;
			private static final int VALUE_STRING_1_END_INDEX = VALUE_STRING_1_BEGIN_INDEX
					+ VALUE_STRING_1_LENGTH;
			private static final int VALUE_INT_1_BEGIN_INDEX = VALUE_STRING_1_END_INDEX + 1;
			private static final int VALUE_INT_1_END_INDEX = VALUE_INT_1_BEGIN_INDEX
					+ VALUE_INT_1_LENGTH;

			private Date valueDate1;
			private String valueString1;
			private int valueInt1;

			public TestValue1() {
				super();
			}

			public TestValue1(Date valueDate1, String valueString1,
					int valueInt1) {
				super();
				this.valueDate1 = valueDate1;
				this.valueString1 = valueString1;
				this.valueInt1 = valueInt1;
			}

			public TestValue1(byte[] bytes) {
				super();
				fromBytes(bytes);
			}

			public Date getValueDate1() {
				return valueDate1;
			}

			public void setValueDate1(Date valueDate1) {
				this.valueDate1 = valueDate1;
			}

			public String getValueString1() {
				return valueString1;
			}

			public void setValueString1(String valueString1) {
				this.valueString1 = valueString1;
			}

			public int getValueInt1() {
				return valueInt1;
			}

			public void setValueInt1(int valueInt1) {
				this.valueInt1 = valueInt1;
			}

			@Override
			public byte[] toBytes() {
				byte[] valueDate1Bytes = ByteConvertUtility.toBytes(valueDate1);
				byte[] valueString1Bytes = ByteConvertUtility.toBytes(
						valueString1, VALUE_STRING_1_LENGTH);
				byte[] valueInt1Bytes = ByteConvertUtility.toBytes(valueInt1,
						VALUE_INT_1_LENGTH);
				return ArrayUtility.addAll(valueDate1Bytes, SPACE,
						valueString1Bytes, SPACE, valueInt1Bytes);
			}

			@Override
			public void fromBytes(byte[] bytes) {
				try {
					this.valueDate1 = ByteConvertUtility.getDateFromBytes(
							bytes, VALUE_DATE_1_BEGIN_INDEX,
							VALUE_DATE_1_END_INDEX);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
				this.valueString1 = ByteConvertUtility.getStringFromBytes(
						bytes, VALUE_STRING_1_BEGIN_INDEX,
						VALUE_STRING_1_END_INDEX);
				this.valueInt1 = ByteConvertUtility.getIntFromBytes(bytes,
						VALUE_INT_1_BEGIN_INDEX, VALUE_INT_1_END_INDEX);
			}
		}
	}

	public class TestFamily2 extends HBaseColumnFamily {
		private TestFamily2(TestTable entity) {
			super(entity);
		}

		public void add(String qual, Date date, Date valueDate2,
				String valueString2, int valueInt2) {
			HBaseColumnQualifier qualifier = this.new TestQualifier2(qual);
			TestValue2 val = this.new TestValue2(valueDate2, valueString2,
					valueInt2);
			add(qualifier, date, val);
		}

		@Override
		public HBaseColumnQualifier generateColumnQualifier(byte[] bytes) {
			return this.new TestQualifier2(bytes);
		}

		@Override
		public HBaseValue generateValue(byte[] bytes) {
			return this.new TestValue2(bytes);
		}

		public class TestQualifier2 extends HBaseColumnQualifier {
			private String qual;

			public TestQualifier2() {
				super();
			}

			public TestQualifier2(String qual) {
				super();
				this.qual = qual;
			}

			public TestQualifier2(byte[] bytes) {
				super();
				fromBytes(bytes);
			}

			@Override
			public byte[] toBytes() {
				return ByteConvertUtility.toBytes(qual);
			}

			@Override
			public void fromBytes(byte[] bytes) {
				this.qual = ByteConvertUtility.getStringFromBytes(bytes);
			}

			public String getQual() {
				return qual;
			}

			public void setQual(String qual) {
				this.qual = qual;
			}
		}

		public class TestValue2 extends HBaseValue {
			private static final int VALUE_DATE_2_LENGTH = ByteConvertUtility.DEFAULT_DATE_PATTERN_LENGTH;
			private static final int VALUE_STRING_2_LENGTH = 20;
			private static final int VALUE_INT_2_LENGTH = 20;

			private static final int VALUE_DATE_2_BEGIN_INDEX = 0;
			private static final int VALUE_DATE_2_END_INDEX = VALUE_DATE_2_BEGIN_INDEX
					+ VALUE_DATE_2_LENGTH;
			private static final int VALUE_STRING_2_BEGIN_INDEX = VALUE_DATE_2_END_INDEX + 1;
			private static final int VALUE_STRING_2_END_INDEX = VALUE_STRING_2_BEGIN_INDEX
					+ VALUE_STRING_2_LENGTH;
			private static final int VALUE_INT_2_BEGIN_INDEX = VALUE_STRING_2_END_INDEX + 1;
			private static final int VALUE_INT_2_END_INDEX = VALUE_INT_2_BEGIN_INDEX
					+ VALUE_INT_2_LENGTH;

			private Date valueDate2;
			private String valueString2;
			private int valueInt2;

			public TestValue2() {
				super();
			}

			public TestValue2(Date valueDate2, String valueString2,
					int valueInt2) {
				super();
				this.valueDate2 = valueDate2;
				this.valueString2 = valueString2;
				this.valueInt2 = valueInt2;
			}

			public TestValue2(byte[] bytes) {
				super();
				fromBytes(bytes);
			}

			public Date getValueDate2() {
				return valueDate2;
			}

			public void setValueDate2(Date valueDate2) {
				this.valueDate2 = valueDate2;
			}

			public String getValueString2() {
				return valueString2;
			}

			public void setValueString2(String valueString2) {
				this.valueString2 = valueString2;
			}

			public int getValueInt2() {
				return valueInt2;
			}

			public void setValueInt2(int valueInt2) {
				this.valueInt2 = valueInt2;
			}

			@Override
			public byte[] toBytes() {
				byte[] valueDate2Bytes = ByteConvertUtility.toBytes(valueDate2);
				byte[] valueString2Bytes = ByteConvertUtility.toBytes(
						valueString2, VALUE_STRING_2_LENGTH);
				byte[] valueInt2Bytes = ByteConvertUtility.toBytes(valueInt2,
						VALUE_INT_2_LENGTH);
				return ArrayUtility.addAll(valueDate2Bytes, SPACE,
						valueString2Bytes, SPACE, valueInt2Bytes);
			}

			@Override
			public void fromBytes(byte[] bytes) {
				try {
					this.valueDate2 = ByteConvertUtility.getDateFromBytes(
							bytes, VALUE_DATE_2_BEGIN_INDEX,
							VALUE_DATE_2_END_INDEX);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
				this.valueString2 = ByteConvertUtility.getStringFromBytes(
						bytes, VALUE_STRING_2_BEGIN_INDEX,
						VALUE_STRING_2_END_INDEX);
				this.valueInt2 = ByteConvertUtility.getIntFromBytes(bytes,
						VALUE_INT_2_BEGIN_INDEX, VALUE_INT_2_END_INDEX);
			}
		}
	}
}
