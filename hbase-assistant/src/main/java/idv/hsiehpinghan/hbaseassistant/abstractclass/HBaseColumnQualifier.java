package idv.hsiehpinghan.hbaseassistant.abstractclass;


public abstract class HBaseColumnQualifier extends HBaseBase implements
		Comparable<HBaseColumnQualifier> {
	public abstract byte[] toBytes();

	public abstract void fromBytes(byte[] bytes);

	/**
	 * Overwrite compare to get customerize order.
	 */
	@Override
	public abstract int compareTo(HBaseColumnQualifier o);


}
