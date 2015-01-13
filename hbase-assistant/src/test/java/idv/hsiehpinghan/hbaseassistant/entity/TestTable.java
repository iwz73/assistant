package idv.hsiehpinghan.hbaseassistant.entity;

import idv.hsiehpinghan.collectionutility.utility.ArrayUtility;
import idv.hsiehpinghan.datatypeutility.utility.IntegerUtility;
import idv.hsiehpinghan.datatypeutility.utility.LongUtility;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;

import java.util.Date;
import java.util.NavigableMap;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.util.Bytes;

public class TestTable extends HBaseTable {
	private TestFamily1 family1;
	private TestFamily2 family2;

	public TestTable() {
		super();
	}

	public TestTable(TestRowKey rowKey, TestFamily1 family1) {
		super(rowKey);
		this.family1 = family1;
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
		private static final int KEY_DATE_1_LENTH = LongUtility.LONG_BYTE_AMOUNT;
		private static final int KEY_STRING_1_LENTH = 10;
		private static final int KEY_INT_1_LENTH = IntegerUtility.INT_BYTE_AMOUNT;

		private static final int KEY_DATE_1_BEGIN_INDEX = 0;
		private static final int KEY_DATE_1_END_INDEX = KEY_DATE_1_BEGIN_INDEX
				+ KEY_DATE_1_LENTH;
		private static final int KEY_STRING_1_BEGIN_INDEX = KEY_DATE_1_END_INDEX;
		private static final int KEY_STRING_1_END_INDEX = KEY_STRING_1_BEGIN_INDEX
				+ KEY_STRING_1_LENTH;
		private static final int KEY_INT_1_BEGIN_INDEX = KEY_STRING_1_END_INDEX;
		private static final int KEY_INT_1_END_INDEX = KEY_INT_1_BEGIN_INDEX
				+ KEY_INT_1_LENTH;

		private Date keyDate1;
		private String keyString1;
		private int keyInt1;

		public TestRowKey(TestTable table) {
			super(table);
		}

		public TestRowKey(Date keyDate1, String keyString1, int keyInt1,
				TestTable table) {
			super(table);
			this.keyDate1 = keyDate1;
			this.keyString1 = keyString1;
			this.keyInt1 = keyInt1;
		}

		public TestRowKey(byte[] bytes, TestTable table) {
			super(table);
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
			byte[] keyDate1Bytes = Bytes.toBytes(keyDate1.getTime());
			byte[] keyString1Bytes = Bytes.toBytes(StringUtils.leftPad(
					keyString1, KEY_STRING_1_LENTH));
			byte[] keyInt1Bytes = Bytes.toBytes(keyInt1);
			return ArrayUtility.addAll(keyDate1Bytes, keyString1Bytes,
					keyInt1Bytes);
		}

		@Override
		public void fromBytes(byte[] bytes) {
			this.keyDate1 = new Date(Bytes.toLong(ArrayUtils.subarray(bytes,
					KEY_DATE_1_BEGIN_INDEX, KEY_DATE_1_END_INDEX)));
			this.keyString1 = Bytes.toString(
					ArrayUtils.subarray(bytes, KEY_STRING_1_BEGIN_INDEX,
							KEY_STRING_1_END_INDEX)).trim();
			this.keyInt1 = Bytes.toInt(ArrayUtils.subarray(bytes,
					KEY_INT_1_BEGIN_INDEX, KEY_INT_1_END_INDEX));
		}
	}

	public class TestFamily1 extends HBaseColumnFamily {
		private TestFamily1(TestTable table) {
			super(table);
		}

		public void add(String qual, Date date, Date valueDate1,
				String valueString1, int valueInt1) {
			HBaseColumnQualifier qualifier = this.new TestQualifier1(qual);
			NavigableMap<Date, HBaseValue> verMap = getVersionValueMap(qualifier);
			TestValue1 val = this.new TestValue1(valueDate1, valueString1,
					valueInt1);
			verMap.put(date, val);
		}

		@Override
		protected HBaseColumnQualifier generateColumnQualifier(
				byte[] qualifierBytes) {
			return this.new TestQualifier1(qualifierBytes);
		}

		@Override
		protected HBaseValue generateValue(byte[] valueBytes) {
			return this.new TestValue1(valueBytes);
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

			public TestQualifier1(byte[] qualifierBytes) {
				super();
				fromBytes(qualifierBytes);
			}

			@Override
			public byte[] toBytes() {
				return Bytes.toBytes(qual);
			}

			@Override
			public void fromBytes(byte[] qualBytes) {
				this.qual = Bytes.toString(qualBytes);
			}

			public String getQual() {
				return qual;
			}

			public void setQual(String qual) {
				this.qual = qual;
			}

			@Override
			public int compareTo(HBaseColumnQualifier o) {
				String qual = this.getClass().cast(o).getQual();
				return this.getQual().compareTo(qual);
			}

		}

		public class TestValue1 extends HBaseValue {
			private static final int VALUE_DATE_1_LENTH = LongUtility.LONG_BYTE_AMOUNT;
			private static final int VALUE_STRING_1_LENTH = 20;
			private static final int VALUE_INT_1_LENTH = IntegerUtility.INT_BYTE_AMOUNT;

			private static final int VALUE_DATE_1_BEGIN_INDEX = 0;
			private static final int VALUE_DATE_1_END_INDEX = VALUE_DATE_1_BEGIN_INDEX
					+ VALUE_DATE_1_LENTH;
			private static final int VALUE_STRING_1_BEGIN_INDEX = VALUE_DATE_1_END_INDEX;
			private static final int VALUE_STRING_1_END_INDEX = VALUE_STRING_1_BEGIN_INDEX
					+ VALUE_STRING_1_LENTH;
			private static final int VALUE_INT_1_BEGIN_INDEX = VALUE_STRING_1_END_INDEX;
			private static final int VALUE_INT_1_END_INDEX = VALUE_INT_1_BEGIN_INDEX
					+ VALUE_INT_1_LENTH;

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

			public TestValue1(byte[] valueBytes) {
				super();
				fromBytes(valueBytes);
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
				byte[] valueDate1Bytes = Bytes.toBytes(valueDate1.getTime());
				byte[] valueString1Bytes = Bytes.toBytes(StringUtils.leftPad(
						valueString1, VALUE_STRING_1_LENTH));
				byte[] valueInt1Bytes = Bytes.toBytes(valueInt1);
				return ArrayUtility.addAll(valueDate1Bytes, valueString1Bytes,
						valueInt1Bytes);
			}

			@Override
			public void fromBytes(byte[] bytes) {
				this.valueDate1 = new Date(Bytes.toLong(ArrayUtils
						.subarray(bytes, VALUE_DATE_1_BEGIN_INDEX,
								VALUE_DATE_1_END_INDEX)));
				this.valueString1 = Bytes.toString(
						ArrayUtils.subarray(bytes, VALUE_STRING_1_BEGIN_INDEX,
								VALUE_STRING_1_END_INDEX)).trim();
				this.valueInt1 = Bytes.toInt(ArrayUtils.subarray(bytes,
						VALUE_INT_1_BEGIN_INDEX, VALUE_INT_1_END_INDEX));
			}
		}
	}

	public class TestFamily2 extends HBaseColumnFamily {
		private TestFamily2(TestTable table) {
			super(table);
		}

		public void add(String qual, Date date, Date valueDate2,
				String valueString2, int valueInt2) {
			HBaseColumnQualifier qualifier = this.new TestQualifier2(qual);
			NavigableMap<Date, HBaseValue> verMap = getVersionValueMap(qualifier);
			TestValue2 val = this.new TestValue2(valueDate2, valueString2,
					valueInt2);
			verMap.put(date, val);
		}

		@Override
		public HBaseColumnQualifier generateColumnQualifier(
				byte[] qualifierBytes) {
			return this.new TestQualifier2(qualifierBytes);
		}

		@Override
		public HBaseValue generateValue(byte[] valueBytes) {
			return this.new TestValue2(valueBytes);
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

			public TestQualifier2(byte[] qualifierBytes) {
				super();
				fromBytes(qualifierBytes);
			}

			@Override
			public byte[] toBytes() {
				return Bytes.toBytes(qual);
			}

			@Override
			public void fromBytes(byte[] qualBytes) {
				this.qual = Bytes.toString(qualBytes);
			}

			public String getQual() {
				return qual;
			}

			public void setQual(String qual) {
				this.qual = qual;
			}

			@Override
			public int compareTo(HBaseColumnQualifier o) {
				String qual = this.getClass().cast(o).getQual();
				return this.getQual().compareTo(qual);
			}

		}

		public class TestValue2 extends HBaseValue {
			private static final int VALUE_DATE_2_LENTH = LongUtility.LONG_BYTE_AMOUNT;
			private static final int VALUE_STRING_2_LENTH = 20;
			private static final int VALUE_INT_2_LENTH = IntegerUtility.INT_BYTE_AMOUNT;

			private static final int VALUE_DATE_2_BEGIN_INDEX = 0;
			private static final int VALUE_DATE_2_END_INDEX = VALUE_DATE_2_BEGIN_INDEX
					+ VALUE_DATE_2_LENTH;
			private static final int VALUE_STRING_2_BEGIN_INDEX = VALUE_DATE_2_END_INDEX;
			private static final int VALUE_STRING_2_END_INDEX = VALUE_STRING_2_BEGIN_INDEX
					+ VALUE_STRING_2_LENTH;
			private static final int VALUE_INT_2_BEGIN_INDEX = VALUE_STRING_2_END_INDEX;
			private static final int VALUE_INT_2_END_INDEX = VALUE_INT_2_BEGIN_INDEX
					+ VALUE_INT_2_LENTH;

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

			public TestValue2(byte[] valueBytes) {
				super();
				fromBytes(valueBytes);
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
				byte[] valueDate2Bytes = Bytes.toBytes(valueDate2.getTime());
				byte[] valueString2Bytes = Bytes.toBytes(StringUtils.leftPad(
						valueString2, VALUE_STRING_2_LENTH));
				byte[] valueInt2Bytes = Bytes.toBytes(valueInt2);
				return ArrayUtility.addAll(valueDate2Bytes, valueString2Bytes,
						valueInt2Bytes);
			}

			@Override
			public void fromBytes(byte[] bytes) {
				this.valueDate2 = new Date(Bytes.toLong(ArrayUtils
						.subarray(bytes, VALUE_DATE_2_BEGIN_INDEX,
								VALUE_DATE_2_END_INDEX)));
				this.valueString2 = Bytes.toString(
						ArrayUtils.subarray(bytes, VALUE_STRING_2_BEGIN_INDEX,
								VALUE_STRING_2_END_INDEX)).trim();
				this.valueInt2 = Bytes.toInt(ArrayUtils.subarray(bytes,
						VALUE_INT_2_BEGIN_INDEX, VALUE_INT_2_END_INDEX));
			}
		}
	}
}
