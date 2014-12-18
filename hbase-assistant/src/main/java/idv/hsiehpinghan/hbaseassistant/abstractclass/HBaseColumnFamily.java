package idv.hsiehpinghan.hbaseassistant.abstractclass;

import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseValue;

import java.util.Date;
import java.util.Map;

/**
 * HBase column family base class.
 * 
 * @author thank.hsiehpinghan
 *
 */
public abstract class HBaseColumnFamily {
	private Map<HBaseColumnQualifier, Map<Date, HBaseValue>> valueMap;

	public HBaseColumnFamily() {
		super();
	}

	public HBaseColumnFamily(
			Map<HBaseColumnQualifier, Map<Date, HBaseValue>> valueMap) {
		super();
		this.valueMap = valueMap;
	}

	public Map<HBaseColumnQualifier, Map<Date, HBaseValue>> getValueMap() {
		return valueMap;
	}

	public void setValueMap(
			Map<HBaseColumnQualifier, Map<Date, HBaseValue>> valueMap) {
		this.valueMap = valueMap;
	}

}
