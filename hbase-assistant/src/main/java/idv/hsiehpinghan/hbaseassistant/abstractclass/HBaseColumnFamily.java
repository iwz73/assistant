package idv.hsiehpinghan.hbaseassistant.abstractclass;

import java.util.Date;
import java.util.NavigableMap;
import java.util.TreeMap;

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

	/**
	 * Get versionValueMap.(If not exists, create it and return.)
	 * 
	 * @param qualifier
	 * @return
	 */
	public NavigableMap<Date, HBaseValue> getVersionValueMap(
			HBaseColumnQualifier qualifier) {
		NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualMap = getQualifierVersionValueMap();
		NavigableMap<Date, HBaseValue> verMap = qualMap.get(qualifier);
		if (verMap == null) {
			verMap = new TreeMap<Date, HBaseValue>();
			qualMap.put(qualifier, verMap);
		}
		return verMap;
	}

	/**
	 * Get qualifierVersionValueMap.(If not exists, create it and return.)
	 * 
	 * @return
	 */
	public NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> getQualifierVersionValueMap() {
		if (qualifierVersionValueMap == null) {
			qualifierVersionValueMap = new TreeMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>>();
		}
		return qualifierVersionValueMap;
	}

	public void setQualifierVersionValueMap(
			NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualifierVersionValueMap) {
		this.qualifierVersionValueMap = qualifierVersionValueMap;
	}

}
