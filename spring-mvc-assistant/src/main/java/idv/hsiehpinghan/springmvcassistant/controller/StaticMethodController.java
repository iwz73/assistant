package idv.hsiehpinghan.springmvcassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/staticMethod")
public class StaticMethodController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("/staticMethod/index");
		modelAndView.addObject("parameter", 3);
		return modelAndView;
	}

}
