package idv.hsiehpinghan.hbaseassistant.abstractclass;

public abstract class HBaseTable extends HBaseBase {
	public String getTableName() {
		return this.getClass().getSimpleName();
	}

	public abstract HBaseRowKey getRowKey();

	public abstract void setRowKey(HBaseRowKey rowKey);
}
