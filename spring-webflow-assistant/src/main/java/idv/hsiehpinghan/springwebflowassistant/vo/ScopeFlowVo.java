package idv.hsiehpinghan.springwebflowassistant.vo;

import java.io.Serializable;

public class ScopeFlowVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String requestScopeValue = "requestScopeValue";
	private String flashScopeValue = "flashScopeValue";
	private String viewScopeValue = "viewScopeValue";
	private String flowScopeValue = "flowScopeValue";
	private String conversationScopeValue = "conversationScopeValue";

	public String getRequestScopeValue() {
		return requestScopeValue;
	}

	public void setRequestScopeValue(String requestScopeValue) {
		this.requestScopeValue = requestScopeValue;
	}

	public String getFlashScopeValue() {
		return flashScopeValue;
	}

	public void setFlashScopeValue(String flashScopeValue) {
		this.flashScopeValue = flashScopeValue;
	}

	public String getViewScopeValue() {
		return viewScopeValue;
	}

	public void setViewScopeValue(String viewScopeValue) {
		this.viewScopeValue = viewScopeValue;
	}

	public String getFlowScopeValue() {
		return flowScopeValue;
	}

	public void setFlowScopeValue(String flowScopeValue) {
		this.flowScopeValue = flowScopeValue;
	}

	public String getConversationScopeValue() {
		return conversationScopeValue;
	}

	public void setConversationScopeValue(String conversationScopeValue) {
		this.conversationScopeValue = conversationScopeValue;
	}

}
