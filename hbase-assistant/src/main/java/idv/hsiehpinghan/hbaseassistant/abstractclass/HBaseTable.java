package idv.hsiehpinghan.hbaseassistant.abstractclass;

public abstract class HBaseTable extends HBaseBase implements
		Comparable<HBaseTable> {

	@Override
	public int compareTo(HBaseTable o) {
		return this.getRowKey().compareTo(o.getRowKey());
	}

	public String getTableName() {
		return this.getClass().getSimpleName();
	}

	public abstract HBaseRowKey getRowKey();

	public abstract void setRowKey(HBaseRowKey rowKey);
}
