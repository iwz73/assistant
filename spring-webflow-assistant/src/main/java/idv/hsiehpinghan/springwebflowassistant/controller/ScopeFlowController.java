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
import idv.hsiehpinghan.springwebflowassistant.vo.ViewScopeVo;

@Controller
@RequestMapping(value = "/scopeFlow")
public class ScopeFlowController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/scopeFlow/index";
	}

	public void initScope() {
		RequestContext requestContext = RequestContextHolder.getRequestContext();
		requestContext.getRequestScope().put("vo", generateRequestScopeVo());
		requestContext.getFlashScope().put("vo", generateFlashScopeVo());
		requestContext.getViewScope().put("vo", generateViewScopeVo());
		requestContext.getConversationScope().put("vo", generateConversationScopeVo());
		requestContext.getFlowScope().put("vo", generateFlowScopeVo());
	}

	public RequestScopeVo generateRequestScopeVo() {
		RequestScopeVo vo = new RequestScopeVo();
		vo.setValue("Request Scope Vo");
		return vo;
	}

	public FlashScopeVo generateFlashScopeVo() {
		FlashScopeVo vo = new FlashScopeVo();
		vo.setValue("Flash Scope Vo");
		return vo;
	}

	public ViewScopeVo generateViewScopeVo() {
		ViewScopeVo vo = new ViewScopeVo();
		vo.setValue("View Scope Vo");
		return vo;
	}

	public ConversationScopeVo generateConversationScopeVo() {
		ConversationScopeVo vo = new ConversationScopeVo();
		vo.setValue("Conversation Scope Vo");
		return vo;
	}

	public FlowScopeVo generateFlowScopeVo() {
		FlowScopeVo vo = new FlowScopeVo();
		vo.setValue("Flow Scope Vo");
		return vo;
	}
}
