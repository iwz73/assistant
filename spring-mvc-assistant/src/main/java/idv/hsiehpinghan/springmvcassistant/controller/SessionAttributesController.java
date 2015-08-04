package idv.hsiehpinghan.springmvcassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("parameter")
@RequestMapping(value = "/sessionAttributes")
public class SessionAttributesController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/sessionAttributes/index";
	}

	@RequestMapping(value = "/setSessionParameter", method = RequestMethod.GET)
	public ModelAndView setSessionParameter(@RequestParam String parameter) {
		ModelAndView modelAndView = new ModelAndView("/sessionAttributes/index");
		modelAndView.addObject("parameter", parameter);
		return modelAndView;
	}

	@RequestMapping(value = "/clearSession", method = RequestMethod.GET)
	public String clearSession(SessionStatus status) {
		status.setComplete();
		return "/sessionAttributes/index";
	}
}
