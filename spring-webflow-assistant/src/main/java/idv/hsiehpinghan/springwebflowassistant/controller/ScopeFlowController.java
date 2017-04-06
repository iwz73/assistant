package idv.hsiehpinghan.springwebflowassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;

import idv.hsiehpinghan.springwebflowassistant.vo.ConversationScopeVo;
import idv.hsiehpinghan.springwebflowassistant.vo.FlashScopeVo;
import idv.hsiehpinghan.springwebflowassistant.vo.FlowScopeVo;
import idv.hsiehpinghan.springwebflowassistant.vo.RequestScopeVo;
import idv.hsiehpinghan.springwebflowassistant.vo.ScopeFlowVo;
import idv.hsiehpinghan.springwebflowassistant.vo.ViewScopeVo;

@Controller
@RequestMapping(value = "/scopeFlow")
public class ScopeFlowController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/scopeFlow/index";
	}

	public ScopeFlowVo generateScopeFlowVo() {
		ScopeFlowVo vo = new ScopeFlowVo();
		return vo;
	}

	public void initScope(ScopeFlowVo vo) {
		RequestContext requestContext = RequestContextHolder.getRequestContext();
		String requestScopeValue = vo.getRequestScopeValue();
		RequestScopeVo requestScopeVo = generateRequestScopeVo(requestScopeValue);
		requestContext.getRequestScope().put("requestScopeVo", requestScopeVo);
		String flashScopeValue = vo.getFlashScopeValue();
		FlashScopeVo flashScopeVo = generateFlashScopeVo(flashScopeValue);
		requestContext.getFlashScope().put("flashScopeVo", flashScopeVo);
		String viewScopeValue = vo.getViewScopeValue();
		ViewScopeVo viewScopeVo = generateViewScopeVo(viewScopeValue);
		requestContext.getViewScope().put("viewScopeVo", viewScopeVo);
		String conversationScopeValue = vo.getConversationScopeValue();
		ConversationScopeVo conversationScopeVo = generateConversationScopeVo(conversationScopeValue);
		requestContext.getConversationScope().put("conversationScopeVo", conversationScopeVo);
		String flowScopeValue = vo.getFlowScopeValue();
		FlowScopeVo flowScopeVo = generateFlowScopeVo(flowScopeValue);
		requestContext.getFlowScope().put("flowScopeVo", flowScopeVo);
	}

	public RequestScopeVo generateRequestScopeVo(String requestScopeValue) {
		RequestScopeVo vo = new RequestScopeVo();
		vo.setValue(requestScopeValue);
		return vo;
	}

	public FlashScopeVo generateFlashScopeVo(String flashScopeValue) {
		FlashScopeVo vo = new FlashScopeVo();
		vo.setValue(flashScopeValue);
		return vo;
	}

	public ViewScopeVo generateViewScopeVo(String viewScopeValue) {
		ViewScopeVo vo = new ViewScopeVo();
		vo.setValue(viewScopeValue);
		return vo;
	}

	public ConversationScopeVo generateConversationScopeVo(String conversationScopeValue) {
		ConversationScopeVo vo = new ConversationScopeVo();
		vo.setValue(conversationScopeValue);
		return vo;
	}

	public FlowScopeVo generateFlowScopeVo(String flowScopeValue) {
		FlowScopeVo vo = new FlowScopeVo();
		vo.setValue(flowScopeValue);
		return vo;
	}
}
