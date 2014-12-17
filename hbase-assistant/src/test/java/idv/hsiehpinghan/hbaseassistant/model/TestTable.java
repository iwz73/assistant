package idv.hsiehpinghan.hbaseassistant.model;

import idv.hsiehpinghan.datatypeutility.utility.ArrayUtility;
import idv.hsiehpinghan.datatypeutility.utility.IntegerUtility;
import idv.hsiehpinghan.hbaseassistant.annotation.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseQualifier;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseValue;

import java.math.BigDecimal;
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
@HBaseTable("TestTable")
public class TestTable {
	private Key rowKey;
	private ColFam colFam;

	public TestTable() {
		super();
	}

	public TestTable(Key rowKey, ColFam colFam) {
		super();
		this.rowKey = rowKey;
		this.colFam = colFam;
	}

	public Key getRowKey() {
		return rowKey;
	}

	public void setRowKey(Key rowKey) {
		this.rowKey = rowKey;
	}

	public ColFam getColFam() {
		return colFam;
	}

	public void setColFam(ColFam colFam) {
		this.colFam = colFam;
	}

	/**
	 * Row key.
	 * 
	 * @author thank.hsiehpinghan
	 *
	 */
	public static class Key implements HBaseRowKey {
		static {
			int idLength = 10;
			int idBegin = 0;
			int idEnd = idBegin + idLength;
			int orderBegin = idEnd;
			int orderEnd = idEnd + IntegerUtility.INT_BYTE_AMOUNT;
			ID_LENGTH = idLength;
			ID_BEGIN = idBegin;
			ID_END = idEnd;
			ORDER_BEGIN = orderBegin;
			ORDER_END = orderEnd;
		}
		private static final int ID_LENGTH;
		private static final int ID_BEGIN;
		private static final int ID_END;
		private static final int ORDER_BEGIN;
		private static final int ORDER_END;
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
	public static class ColFam implements HBaseColumnFamily {
		private Map<TestQualifier1, TestValue1> map = new HashMap<TestQualifier1, TestValue1>();

		public void add(String s, BigDecimal v) {
			TestQualifier1 q = new TestQualifier1(s);
			TestValue1 val = new TestValue1(v);
			map.put(q, val);
		}

		/**
		 * Qualifier.
		 * 
		 * @author thank.hsiehpinghan
		 *
		 */
		public static class TestQualifier1 implements HBaseQualifier {
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
		public static class TestValue1 implements HBaseValue {
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
