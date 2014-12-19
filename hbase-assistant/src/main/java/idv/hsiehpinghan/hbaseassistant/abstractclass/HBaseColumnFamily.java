package idv.hsiehpinghan.hbaseassistant.abstractclass;

import java.util.Date;
import java.util.NavigableMap;

/**
 * HBase column family base class.
 * 
 * @author thank.hsiehpinghan
 *
 */
public abstract class HBaseColumnFamily extends HBaseBase {
	private NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualifierVersionValueMap;

	public abstract void fromMap(
			NavigableMap<byte[], NavigableMap<Long, byte[]>> valueMap);

	public HBaseColumnFamily() {
		super();
	}

	public HBaseColumnFamily(
			NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualifierVersionValueMap) {
		super();
		this.qualifierVersionValueMap = qualifierVersionValueMap;
	}

	public NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> getQualifierVersionValueMap() {
		return qualifierVersionValueMap;
	}

	public void setQualifierVersionValueMap(
			NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualifierVersionValueMap) {
		this.qualifierVersionValueMap = qualifierVersionValueMap;
	}

}
