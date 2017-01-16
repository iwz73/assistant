package idv.hsiehpinghan.nekohtmlassistant.vo;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ElementFeatureVo {
	private Stack<String> ancestorNodeNames = new Stack<>();
	private String currentNodeName;
	private Map<String, Integer> posterityNodeNameCountMap = new HashMap<>();

	public Stack<String> getAncestorNodeNames() {
		return ancestorNodeNames;
	}

	public void setAncestorNodeNames(Stack<String> ancestorNodeNames) {
		this.ancestorNodeNames = ancestorNodeNames;
	}

	public String getCurrentNodeName() {
		return currentNodeName;
	}

	public void setCurrentNodeName(String currentNodeName) {
		this.currentNodeName = currentNodeName;
	}

	public Map<String, Integer> getPosterityNodeNameCountMap() {
		return posterityNodeNameCountMap;
	}

	public void setPosterityNodeNameCountMap(Map<String, Integer> posterityNodeNameCountMap) {
		this.posterityNodeNameCountMap = posterityNodeNameCountMap;
	}

	@Override
	public String toString() {
		return "ElementFeatureVo [ancestorNodeNames=" + ancestorNodeNames + ", currentNodeName=" + currentNodeName
				+ ", posterityNodeNameCountMap=" + posterityNodeNameCountMap + "]";
	}

}
