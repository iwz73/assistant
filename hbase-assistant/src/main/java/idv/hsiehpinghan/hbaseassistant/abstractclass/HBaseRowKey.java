package idv.hsiehpinghan.hbaseassistant.abstractclass;

public abstract class HBaseRowKey extends HBaseBase {
	private HBaseTable entity;
	private byte[] bytes;

	public HBaseRowKey(HBaseTable entity) {
		this.entity = entity;
		this.entity.setRowKey(this);
	}

	public HBaseTable getEntity() {
		return entity;
	}

	public String getTableName() {
		return entity.getTableName();
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
