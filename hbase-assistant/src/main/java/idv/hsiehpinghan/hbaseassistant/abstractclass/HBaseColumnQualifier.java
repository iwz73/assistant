package idv.hsiehpinghan.hbaseassistant.abstractclass;

import idv.hsiehpinghan.collectionutility.utility.ArrayUtility;

public abstract class HBaseColumnQualifier extends HBaseBase implements
		Comparable<HBaseColumnQualifier> {
	private byte[] bytes;

	@Override
	public int compareTo(HBaseColumnQualifier o) {
		return ArrayUtility.compareTo(this.getBytes(), o.getBytes());
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

}
