package idv.hsiehpinghan.hbaseassistant.abstractclass;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
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
				byte[] valBytes = verBytesEntry.getValue();
				if(valBytes.length == 0) {
					continue;
				}
				Date date = new Date(verBytesEntry.getKey());
				HBaseValue val = this.generateValue(valBytes);
				verMap.put(date, val);
			}
		}
	}

	/**
	 * Get versionValueMap.(If not exists, return empty set.)
	 * 
	 * @param qualifier
	 * @return
	 */
	protected Set<Entry<Date, HBaseValue>> getVersionValueSet(
			HBaseColumnQualifier qualifier) {
		return getVersionValueMap(qualifier).descendingMap().entrySet();
	}

	/**
	 * Get qualifierVersionValueSet.(If not exists, return empty set.)
	 * 
	 * @return
	 */
	public Set<Entry<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>>> getQualifierVersionValueSet() {
		return getQualifierVersionValueMap().entrySet();
	}

	public void add(HBaseColumnQualifier qualifier, Date date, HBaseValue value) {
		getVersionValueMap(qualifier).put(date, value);
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

	private NavigableMap<Date, HBaseValue> getVersionValueMap(
			HBaseColumnQualifier qualifier) {
		NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualMap = getQualifierVersionValueMap();
		if (qualMap.containsKey(qualifier) == false) {
			NavigableMap<Date, HBaseValue> verMap = new TreeMap<Date, HBaseValue>();
			qualMap.put(qualifier, verMap);
		}
		return qualMap.get(qualifier);
	}

	private NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> getQualifierVersionValueMap() {
		if (qualifierVersionValueMap == null) {
			qualifierVersionValueMap = new TreeMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>>();
		}
		return qualifierVersionValueMap;
	}
}
