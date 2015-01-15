package idv.hsiehpinghan.hbaseassistant.abstractclass;

import org.apache.commons.lang3.builder.CompareToBuilder;

public abstract class HBaseColumnQualifier extends HBaseBase implements
		Comparable<HBaseColumnQualifier> {
	public abstract byte[] toBytes();

	public abstract void fromBytes(byte[] bytes);

	/**
	 * Overwrite compare to get customerize order.
	 */
	@Override
	public int compareTo(HBaseColumnQualifier o) {
		return CompareToBuilder.reflectionCompare(this, o);
	}

}
