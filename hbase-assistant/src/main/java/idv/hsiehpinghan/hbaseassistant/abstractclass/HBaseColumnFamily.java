package idv.hsiehpinghan.hbaseassistant.abstractclass;

import java.util.Date;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * HBase column family base class.
 * 
 * @author thank.hsiehpinghan
 *
 */
public abstract class HBaseColumnFamily extends HBaseBase {
	private HBaseTable table;
	private NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualifierVersionValueMap;

	protected HBaseColumnFamily(HBaseTable table) {
		super();
		this.table = table;
	}

	protected HBaseColumnFamily(
			NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualifierVersionValueMap,
			HBaseTable table) {
		super();
		this.table = table;
		this.qualifierVersionValueMap = qualifierVersionValueMap;
	}

	public void fromMap(
			NavigableMap<byte[], NavigableMap<Long, byte[]>> qualBytesMap) {
		for (Map.Entry<byte[], NavigableMap<Long, byte[]>> qualBytesEntry : qualBytesMap
				.entrySet()) {
			HBaseColumnQualifier qual = this
					.generateColumnQualifier(qualBytesEntry.getKey());
			NavigableMap<Long, byte[]> verBytesMap = qualBytesEntry.getValue();
			NavigableMap<Date, HBaseValue> verMap = getVersionValueMap(qual);
			for (Map.Entry<Long, byte[]> verBytesEntry : verBytesMap.entrySet()) {
				Date date = new Date(verBytesEntry.getKey());
				HBaseValue val = this.generateValue(verBytesEntry.getValue());
				verMap.put(date, val);
			}
		}
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

	public HBaseTable getTable() {
		return table;
	}

	protected abstract HBaseColumnQualifier generateColumnQualifier(
			byte[] qualifierBytes);

	protected abstract HBaseValue generateValue(byte[] valueBytes);
}
