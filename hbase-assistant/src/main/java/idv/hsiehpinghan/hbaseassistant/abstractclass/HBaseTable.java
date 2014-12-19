package idv.hsiehpinghan.hbaseassistant.abstractclass;

public abstract class HBaseTable extends HBaseBase {

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
