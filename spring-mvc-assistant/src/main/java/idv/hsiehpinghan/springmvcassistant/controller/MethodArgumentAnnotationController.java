package idv.hsiehpinghan.springmvcassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.springmvcassistant.criteria.Criteria;

@Controller
@RequestMapping(value = "/methodArgumentAnnotation")
public class MethodArgumentAnnotationController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/methodArgumentAnnotation/index";
	}

	@RequestMapping(value = "/pathVariable/{integerValue}", method = RequestMethod.GET)
	public ModelAndView pathVariable(@PathVariable("integerValue") Integer integerValue) {
		ModelAndView modelAndView = new ModelAndView("/methodArgumentAnnotation/index");
		modelAndView.addObject("parameter", "integerValue:" + integerValue);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/requestBody", method = RequestMethod.POST)
	public Criteria requestBody(@RequestBody Criteria criteria) {
		return criteria;
	}

	@RequestMapping(value = "/requestHeader")
	public ModelAndView requestHeader(@RequestHeader(value = "Accept-Language") String acceptLanguage) {
		ModelAndView modelAndView = new ModelAndView("/methodArgumentAnnotation/index");
		modelAndView.addObject("parameter", "acceptLanguage:" + acceptLanguage);
		return modelAndView;
	}

	@RequestMapping(value = "/cookieValue")
	public ModelAndView cookieValue(@CookieValue(value = "cookieValue") String cookieValue) {
		ModelAndView modelAndView = new ModelAndView("/methodArgumentAnnotation/index");
		modelAndView.addObject("parameter", "cookieValue:" + cookieValue);
		return modelAndView;
	}
}
