package idv.hsiehpinghan.hbaseassistant.entity;

import idv.hsiehpinghan.collectionutility.utility.ArrayUtility;
import idv.hsiehpinghan.datatypeutility.utility.IntegerUtility;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Test table.
 * 
 * @author thank.hsiehpinghan
 *
 */
public class TestTable extends HBaseTable {
	private ColFam1 cf1;
	private ColFam2 cf2;

	public TestTable() {
		super();
	}

	public TestTable(Key rowKey, ColFam1 cf1, ColFam2 cf2) {
		super(rowKey);
		this.cf1 = cf1;
		this.cf2 = cf2;
	}

	public HBaseColumnFamily getCf1() {
		return cf1;
	}

	public void setCf1(ColFam1 cf1) {
		this.cf1 = cf1;
	}

	public ColFam2 getCf2() {
		return cf2;
	}

	public void setCf2(ColFam2 cf2) {
		this.cf2 = cf2;
	}

	/**
	 * Row key.
	 * 
	 * @author thank.hsiehpinghan
	 *
	 */
	public class Key extends HBaseRowKey {
		private final int ID_LENGTH = 10;
		private final int ID_BEGIN = 0;
		private final int ID_END = 10;
		private final int ORDER_BEGIN = 10;
		private final int ORDER_END = 10 + IntegerUtility.INT_BYTE_AMOUNT;
		private String id;
		private int order;

		public Key(String id, int order, HBaseTable table) {
			super(table);
			this.id = id;
			this.order = order;
		}

		public Key(byte[] rowKey, HBaseTable table) {
			super(table);
			fromBytes(rowKey);
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}

		@Override
		public byte[] toBytes() {
			byte[] idArr = Bytes.toBytes(StringUtils.leftPad(id, ID_LENGTH));
			byte[] orderArr = Bytes.toBytes(order);
			byte[] all = ArrayUtility.addAll(idArr, orderArr);
			return all;
		}

		@Override
		public void fromBytes(byte[] bytes) {
			this.id = Bytes.toString(ArrayUtils.subarray(bytes, ID_BEGIN,
					ID_END));
			this.order = Bytes.toInt(ArrayUtils.subarray(bytes, ORDER_BEGIN,
					ORDER_END));

			System.err.println("2 id : " + id + "; order : " + order);

		}

	}

	/**
	 * Column family.
	 * 
	 * @author thank.hsiehpinghan
	 *
	 */
	public class ColFam1 extends HBaseColumnFamily {
		// private Map<TestQualifier1, Map<Date, TestValue1>> map;

		public void add(String q, Date d, BigDecimal v) {
			NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qvMap = getQualifierVersionValueMap();
			HBaseColumnQualifier qu = this.new TestQualifier1(q);
			NavigableMap<Date, HBaseValue> innerM = qvMap.get(qu);
			if (innerM == null) {
				innerM = new TreeMap<Date, HBaseValue>();
			}
			TestQualifier1 qual = new TestQualifier1(q);
			TestValue1 val = new TestValue1(v);
			innerM.put(d, val);
			qvMap.put(qual, innerM);
		}

		@Override
		public void fromMap(
				NavigableMap<byte[], NavigableMap<Long, byte[]>> valueMap) {
			NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualifierVersionValueMap = new TreeMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>>();
			for (Map.Entry<byte[], NavigableMap<Long, byte[]>> qtvEnt : valueMap
					.entrySet()) {
				ColFam1.TestQualifier1 q = new ColFam1.TestQualifier1(
						qtvEnt.getKey());
				NavigableMap<Long, byte[]> tvMap = qtvEnt.getValue();
				NavigableMap<Date, HBaseValue> versionValueMap = new TreeMap<Date, HBaseValue>();
				for (Map.Entry<Long, byte[]> tvEnt : tvMap.entrySet()) {
					Date t = new Date(tvEnt.getKey());
					ColFam1.TestValue1 v = new ColFam1.TestValue1(
							tvEnt.getValue());
					versionValueMap.put(t, v);
				}
				qualifierVersionValueMap.put(q, versionValueMap);
			}
			setQualifierVersionValueMap(qualifierVersionValueMap);

		}

		/**
		 * Qualifier.
		 * 
		 * @author thank.hsiehpinghan
		 *
		 */
		public class TestQualifier1 extends HBaseColumnQualifier {
			private final int LEN = 10;
			private String s;

			public TestQualifier1() {
				super();
			}

			public TestQualifier1(String s) {
				super();
				this.s = s;
			}

			public TestQualifier1(byte[] sArr) {
				super();
				fromBytes(sArr);
			}

			public String getS() {
				return s;
			}

			@Override
			public byte[] toBytes() {
				return Bytes.toBytes(StringUtils.leftPad(s, LEN));
			}

			@Override
			public void fromBytes(byte[] bytes) {
				this.s = Bytes.toString(bytes).trim();
			}

			@Override
			public int compareTo(HBaseColumnQualifier o) {
				return this.getS().compareTo(((TestQualifier1) o).getS());
			}

		}

		/**
		 * Value.
		 * 
		 * @author thank.hsiehpinghan
		 *
		 */
		public class TestValue1 extends HBaseValue {
			private BigDecimal v;

			public TestValue1() {
				super();
			}

			public TestValue1(BigDecimal v) {
				super();
				this.v = v;
			}

			public TestValue1(byte[] vArr) {
				super();
				fromBytes(vArr);
			}

			public BigDecimal getV() {
				return v;
			}

			@Override
			public byte[] toBytes() {
				return Bytes.toBytes(v);
			}

			@Override
			public void fromBytes(byte[] bytes) {
				this.v = Bytes.toBigDecimal(bytes);
			}

		}
	}

	/**
	 * Column family.
	 * 
	 * @author thank.hsiehpinghan
	 *
	 */
	public class ColFam2 extends HBaseColumnFamily {
		// private Map<TestQualifier1, Map<Date, TestValue1>> map;

		public void add(String s, Date d, BigDecimal v) {
			NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> map = getQualifierVersionValueMap();
			if (map == null) {
				map = new TreeMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>>();
				setQualifierVersionValueMap(map);
			}
			HBaseColumnQualifier qu = this.new TestQualifier1(s);
			NavigableMap<Date, HBaseValue> innerM = map.get(qu);
			if (innerM == null) {
				innerM = new TreeMap<Date, HBaseValue>();
			}
			TestQualifier1 qual = new TestQualifier1(s);
			TestValue1 val = new TestValue1(v);
			innerM.put(d, val);
			map.put(qual, innerM);
		}

		@Override
		public void fromMap(
				NavigableMap<byte[], NavigableMap<Long, byte[]>> valueMap) {
			NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualifierVersionValueMap = new TreeMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>>();
			for (Map.Entry<byte[], NavigableMap<Long, byte[]>> qtvEnt : valueMap
					.entrySet()) {
				ColFam2.TestQualifier1 q = new ColFam2.TestQualifier1(
						qtvEnt.getKey());
				NavigableMap<Long, byte[]> tvMap = qtvEnt.getValue();
				NavigableMap<Date, HBaseValue> versionValueMap = new TreeMap<Date, HBaseValue>();
				for (Map.Entry<Long, byte[]> tvEnt : tvMap.entrySet()) {
					Date t = new Date(tvEnt.getKey());
					ColFam2.TestValue1 v = new ColFam2.TestValue1(
							tvEnt.getValue());
					versionValueMap.put(t, v);
				}
				qualifierVersionValueMap.put(q, versionValueMap);
			}
			setQualifierVersionValueMap(qualifierVersionValueMap);

		}

		/**
		 * Qualifier.
		 * 
		 * @author thank.hsiehpinghan
		 *
		 */
		public class TestQualifier1 extends HBaseColumnQualifier {
			private final int LEN = 10;
			private String s;

			public TestQualifier1() {
				super();
			}

			public TestQualifier1(String s) {
				super();
				this.s = s;
			}

			public TestQualifier1(byte[] sArr) {
				super();
				fromBytes(sArr);
			}

			public String getS() {
				return s;
			}

			@Override
			public byte[] toBytes() {
				return Bytes.toBytes(StringUtils.leftPad(s, LEN));
			}

			@Override
			public void fromBytes(byte[] bytes) {
				this.s = Bytes.toString(bytes).trim();
			}

			@Override
			public int compareTo(HBaseColumnQualifier o) {
				return this.getS().compareTo(((TestQualifier1) o).getS());
			}

		}

		/**
		 * Value.
		 * 
		 * @author thank.hsiehpinghan
		 *
		 */
		public class TestValue1 extends HBaseValue {
			private BigDecimal v;

			public TestValue1() {
				super();
			}

			public TestValue1(BigDecimal v) {
				super();
				this.v = v;
			}

			public TestValue1(byte[] vArr) {
				super();
				fromBytes(vArr);
			}

			public BigDecimal getV() {
				return v;
			}

			@Override
			public byte[] toBytes() {
				return Bytes.toBytes(v);
			}

			@Override
			public void fromBytes(byte[] bytes) {
				this.v = Bytes.toBigDecimal(bytes);
			}

		}

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
