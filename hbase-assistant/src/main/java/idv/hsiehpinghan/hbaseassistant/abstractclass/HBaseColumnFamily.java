package idv.hsiehpinghan.hbaseassistant.abstractclass;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

/**
 * HBase column family base class.
 * 
 * @author thank.hsiehpinghan
 *
 */
public abstract class HBaseColumnFamily extends HBaseBase {
	private HBaseTable entity;
	private NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualifierVersionValueMap;

	protected HBaseColumnFamily(HBaseTable entity) {
		super();
		this.entity = entity;
	}

	protected HBaseColumnFamily(
			NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualifierVersionValueMap,
			HBaseTable entity) {
		super();
		this.entity = entity;
		this.qualifierVersionValueMap = qualifierVersionValueMap;
	}

	public String getColumnFamilyName() {
		return StringUtils.uncapitalize(this.getClass().getSimpleName());
	}

	public void setMap(
			NavigableMap<byte[], NavigableMap<Long, byte[]>> qualBytesMap) {
		for (Map.Entry<byte[], NavigableMap<Long, byte[]>> qualBytesEntry : qualBytesMap
				.entrySet()) {
			HBaseColumnQualifier qual = this
					.generateColumnQualifier(qualBytesEntry.getKey());
			NavigableMap<Long, byte[]> verBytesMap = qualBytesEntry.getValue();
			NavigableMap<Date, HBaseValue> verMap = getVersionValueMap(qual,
					true);
			for (Map.Entry<Long, byte[]> verBytesEntry : verBytesMap.entrySet()) {
				byte[] valBytes = verBytesEntry.getValue();
				if (valBytes.length == 0) {
					continue;
				}
				Date date = new Date(verBytesEntry.getKey());
				HBaseValue val = this.generateValue(valBytes);
				verMap.put(date, val);
			}
		}
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
		getVersionValueMap(qualifier, true).put(date, value);
	}

	public void setQualifierVersionValueMap(
			NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualifierVersionValueMap) {
		this.qualifierVersionValueMap = qualifierVersionValueMap;
	}

	/**
	 * Get latest qualifier and value as map.
	 * 
	 * @return
	 */
	public Map<HBaseColumnQualifier, HBaseValue> getLatestQualifierAndValueAsMap() {
		Set<Entry<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>>> qualSet = getQualifierVersionValueSet();
		Map<HBaseColumnQualifier, HBaseValue> map = new TreeMap<HBaseColumnQualifier, HBaseValue>();
		for (Entry<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualEnt : qualSet) {
			HBaseColumnQualifier qual = qualEnt.getKey();
			for (Entry<Date, HBaseValue> verEnt : qualEnt.getValue()
					.descendingMap().entrySet()) {
				HBaseValue val = verEnt.getValue();
				map.put(qual, val);
				break;
			}
		}
		return map;
	}

	/**
	 * Get latest qualifier and value as descending set.
	 * 
	 * @return
	 */
	public Set<Entry<HBaseColumnQualifier, HBaseValue>> getLatestQualifierAndValueAsDescendingSet() {
		return getLatestQualifierAndValueAsDescendingMap().entrySet();
	}

	/**
	 * Get latest qualifier and value as set.
	 * 
	 * @return
	 */
	public Set<Entry<HBaseColumnQualifier, HBaseValue>> getLatestQualifierAndValueAsSet() {
		return getLatestQualifierAndValueAsMap().entrySet();
	}

	public HBaseTable getEntity() {
		return entity;
	}

	protected Set<Entry<Date, HBaseValue>> getVersionValueSet(
			HBaseColumnQualifier qualifier) {
		NavigableMap<Date, HBaseValue> map = getVersionValueMap(qualifier,
				false);
		if (map == null) {
			return null;
		}
		return map.descendingMap().entrySet();
	}

	protected HBaseValue getLatestValue(HBaseColumnQualifier qualifier) {
		Set<Entry<Date, HBaseValue>> verSet = getVersionValueSet(qualifier);
		if (verSet == null) {
			return null;
		}
		for (Entry<Date, HBaseValue> verEnt : verSet) {
			return verEnt.getValue();
		}
		return null;
	}

	protected abstract HBaseColumnQualifier generateColumnQualifier(
			byte[] qualifierBytes);

	protected NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> getQualifierVersionValueMap() {
		if (qualifierVersionValueMap == null) {
			qualifierVersionValueMap = new TreeMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>>();
		}
		return qualifierVersionValueMap;
	}

	protected abstract HBaseValue generateValue(byte[] valueBytes);

	private NavigableMap<Date, HBaseValue> getVersionValueMap(
			HBaseColumnQualifier qualifier, boolean createVerMapIsNotExists) {
		NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualMap = getQualifierVersionValueMap();
		if (qualMap.containsKey(qualifier) == false) {
			if (createVerMapIsNotExists) {
				NavigableMap<Date, HBaseValue> verMap = new TreeMap<Date, HBaseValue>();
				qualMap.put(qualifier, verMap);
			} else {
				return null;
			}
		}
		return qualMap.get(qualifier);
	}

	private Map<HBaseColumnQualifier, HBaseValue> getLatestQualifierAndValueAsDescendingMap() {
		return ((TreeMap<HBaseColumnQualifier, HBaseValue>) getLatestQualifierAndValueAsMap())
				.descendingMap();
	}
}
