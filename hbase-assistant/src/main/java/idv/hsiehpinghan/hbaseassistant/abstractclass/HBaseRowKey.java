package idv.hsiehpinghan.hbaseassistant.abstractclass;

public abstract class HBaseRowKey extends HBaseBase {
	private HBaseTable table;

	public HBaseRowKey(HBaseTable table) {
		this.table = table;
		this.table.setRowKey(this);
	}

	public HBaseTable getTable() {
		return table;
	}

	public String getTableName() {
		return table.getTableName();
	}

	public abstract byte[] toBytes();

	public abstract void fromBytes(byte[] bytes);

}
