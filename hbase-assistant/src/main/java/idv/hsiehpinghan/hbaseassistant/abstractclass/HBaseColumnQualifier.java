package idv.hsiehpinghan.hbaseassistant.abstractclass;

import org.apache.commons.lang3.builder.CompareToBuilder;

public abstract class HBaseColumnQualifier extends HBaseBase implements
		Comparable<HBaseColumnQualifier> {
	private byte[] bytes;

	/**
	 * Overwrite compare to get customerize order.
	 */
	@Override
	public int compareTo(HBaseColumnQualifier o) {
		return CompareToBuilder.reflectionCompare(this, o);
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

}
