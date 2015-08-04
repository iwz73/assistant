package idv.hsiehpinghan.springmvcassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/methodArgumentAnnotation")
public class MethodArgumentAnnotationController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/methodArgumentAnnotation/index";
	}

	@RequestMapping(value = "/pathVariable/{integerValue}", method = RequestMethod.GET)
	public ModelAndView pathVariable(
			@PathVariable("integerValue") Integer integerValue) {
		ModelAndView modelAndView = new ModelAndView(
				"/methodArgumentAnnotation/index");
		modelAndView.addObject("parameter", "integerValue:" + integerValue);
		return modelAndView;
	}

}
