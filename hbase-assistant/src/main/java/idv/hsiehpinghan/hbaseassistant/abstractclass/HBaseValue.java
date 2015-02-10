package idv.hsiehpinghan.hbaseassistant.abstractclass;

public abstract class HBaseValue extends HBaseBase {
	private byte[] bytes;

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
}
