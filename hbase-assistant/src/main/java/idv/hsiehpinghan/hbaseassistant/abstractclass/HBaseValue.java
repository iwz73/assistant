package idv.hsiehpinghan.hbaseassistant.abstractclass;

public abstract class HBaseValue extends HBaseBase {
	public abstract byte[] toBytes();

	public abstract void fromBytes(byte[] bytes);

}
