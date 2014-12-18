package idv.hsiehpinghan.hbaseassistant.abstractclass;

import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseRowKey;

public abstract class HBaseTable {

	private HBaseRowKey rowKey;

	public HBaseTable() {
		super();
	}

	public HBaseTable(HBaseRowKey rowKey) {
		super();
		this.rowKey = rowKey;
	}
	
	public HBaseRowKey getRowKey() {
		return rowKey;
	}

	public void setRowKey(HBaseRowKey rowKey) {
		this.rowKey = rowKey;
	}

}
