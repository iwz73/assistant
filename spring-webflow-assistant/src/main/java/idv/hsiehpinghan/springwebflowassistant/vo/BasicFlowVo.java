package idv.hsiehpinghan.springwebflowassistant.vo;

import java.io.Serializable;
import java.util.LinkedHashSet;

public class BasicFlowVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer categoryId;
	private Integer itemId;
	private LinkedHashSet<Integer> selectedItemIds = new LinkedHashSet<>();

	public void resetItem() {
		itemId = null;
		selectedItemIds = new LinkedHashSet<>();
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public LinkedHashSet<Integer> getSelectedItemIds() {
		return selectedItemIds;
	}

	public void setSelectedItemIds(LinkedHashSet<Integer> selectedItemIds) {
		this.selectedItemIds = selectedItemIds;
	}

}
