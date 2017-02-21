package idv.hsiehpinghan.springwebflowassistant.vo;

import java.io.Serializable;

public class BasicFlowVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer categoryId;
	private Integer itemId;

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

}
