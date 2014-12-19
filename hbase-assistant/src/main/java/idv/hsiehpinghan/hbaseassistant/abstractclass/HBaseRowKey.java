package idv.hsiehpinghan.hbaseassistant.abstractclass;


public abstract class HBaseRowKey extends HBaseBase {
	public abstract byte[] toBytes();

	public abstract void fromBytes(byte[] bytes);

}
