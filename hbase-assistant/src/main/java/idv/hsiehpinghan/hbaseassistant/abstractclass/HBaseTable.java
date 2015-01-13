package idv.hsiehpinghan.hbaseassistant.abstractclass;

public abstract class HBaseTable extends HBaseBase {

	private HBaseRowKey rowKey;

	protected HBaseTable() {
		super();
	}

	protected HBaseTable(HBaseRowKey rowKey) {
		super();
		this.rowKey = rowKey;
	}
	
	public String getTableName() {
		return this.getClass().getSimpleName();
	}

	public HBaseRowKey getRowKey() {
		return rowKey;
	}

	public void setRowKey(HBaseRowKey rowKey) {
		this.rowKey = rowKey;
	}

}
