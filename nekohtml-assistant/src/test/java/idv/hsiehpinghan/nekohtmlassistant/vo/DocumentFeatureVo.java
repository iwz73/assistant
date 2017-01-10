package idv.hsiehpinghan.nekohtmlassistant.vo;

import java.util.HashMap;
import java.util.Map;

public class DocumentFeatureVo {
	private Map<String, Integer> posterityNodeNameCountMap = new HashMap<>();

	public Map<String, Integer> getPosterityNodeNameCountMap() {
		return posterityNodeNameCountMap;
	}

	public void setPosterityNodeNameCountMap(Map<String, Integer> posterityNodeNameCountMap) {
		this.posterityNodeNameCountMap = posterityNodeNameCountMap;
	}

	@Override
	public String toString() {
		return "DocumentFeatureVo [posterityNodeNameCountMap=" + posterityNodeNameCountMap + "]";
	}

}
