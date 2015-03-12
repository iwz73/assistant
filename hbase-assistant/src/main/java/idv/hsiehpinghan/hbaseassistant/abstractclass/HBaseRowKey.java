package idv.hsiehpinghan.hbaseassistant.abstractclass;

import idv.hsiehpinghan.collectionutility.utility.ArrayUtility;

public abstract class HBaseRowKey extends HBaseBase implements
		Comparable<HBaseRowKey> {
	private HBaseTable entity;
	private byte[] bytes;

	@Override
	public int compareTo(HBaseRowKey o) {
		return ArrayUtility.compareTo(this.getBytes(), o.getBytes());
	}

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

	public String getHexString() {
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("\\\\x%02X", b));
		}
		return sb.toString();
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
