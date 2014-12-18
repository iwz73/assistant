package idv.hsiehpinghan.hbaseassistant.interfaces;

public interface HBaseValue {
	byte[] toBytes();
	HBaseValue fromBytes(byte[] bytes);
}
