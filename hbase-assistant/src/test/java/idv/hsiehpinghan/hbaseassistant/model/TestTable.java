package idv.hsiehpinghan.hbaseassistant.model;

import idv.hsiehpinghan.datatypeutility.utility.ArrayUtility;
import idv.hsiehpinghan.datatypeutility.utility.IntegerUtility;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseValue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Test table.
 * 
 * @author thank.hsiehpinghan
 *
 */
public class TestTable extends HBaseTable {
	private HBaseColumnFamily cf1;

	public TestTable() {
		super();
	}

	public TestTable(Key rowKey, HBaseColumnFamily cf1) {
		super(rowKey);
		this.cf1 = cf1;
	}

	public HBaseColumnFamily getCf1() {
		return cf1;
	}

	public void setCf1(HBaseColumnFamily cf1) {
		this.cf1 = cf1;
	}

	/**
	 * Row key.
	 * 
	 * @author thank.hsiehpinghan
	 *
	 */
	public class Key implements HBaseRowKey {
		private static final int ID_LENGTH = 10;
		private static final int ID_BEGIN = 0;
		private static final int ID_END = 10;
		private static final int ORDER_BEGIN = 10;
		private static final int ORDER_END = 10 + IntegerUtility.INT_BYTE_AMOUNT;
		private String id;
		private int order;

		public Key() {
			super();
		}

		public Key(String id, int order) {
			super();
			this.id = id;
			this.order = order;
		}

		public Key(byte[] rowKey) {
			super();
			this.id = Bytes.toString(ArrayUtils.subarray(rowKey, ID_BEGIN,
					ID_END));
			this.order = Bytes.toInt(ArrayUtils.subarray(rowKey, ORDER_BEGIN,
					ORDER_END));
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
	}

	/**
	 * Column family.
	 * 
	 * @author thank.hsiehpinghan
	 *
	 */
	public class ColFam extends HBaseColumnFamily {
		// private Map<TestQualifier1, Map<Date, TestValue1>> map;

		public void add(String s, Date d, BigDecimal v) {
			Map<HBaseColumnQualifier, Map<Date, HBaseValue>> map = getValueMap();
			if (map == null) {
				map = new HashMap<HBaseColumnQualifier, Map<Date, HBaseValue>>();
				setValueMap(map);
			}
			Map<Date, HBaseValue> innerM = map.get(s);
			if (innerM == null) {
				innerM = new HashMap<Date, HBaseValue>();
			}
			TestQualifier1 qual = new TestQualifier1(s);
			TestValue1 val = new TestValue1(v);
			innerM.put(d, val);
			map.put(qual, innerM);
		}

		/**
		 * Qualifier.
		 * 
		 * @author thank.hsiehpinghan
		 *
		 */
		public class TestQualifier1 implements HBaseColumnQualifier {
			private static final int LEN = 10;
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
				this.s = Bytes.toString(sArr);
			}

			public String getS() {
				return s;
			}

			@Override
			public byte[] toBytes() {
				return Bytes.toBytes(StringUtils.leftPad(s, LEN));
			}
		}

		/**
		 * Value.
		 * 
		 * @author thank.hsiehpinghan
		 *
		 */
		public class TestValue1 implements HBaseValue {
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
				this.v = Bytes.toBigDecimal(vArr);
			}

			public BigDecimal getV() {
				return v;
			}

			@Override
			public byte[] toBytes() {
				return Bytes.toBytes(v);
			}

		}
	}

}
