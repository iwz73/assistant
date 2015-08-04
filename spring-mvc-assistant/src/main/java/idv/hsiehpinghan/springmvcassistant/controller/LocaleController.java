package idv.hsiehpinghan.springmvcassistant.controller;

import idv.hsiehpinghan.springmvcassistant.criteria.ValidationCriteria;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/locale")
public class LocaleController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(@ModelAttribute("criteria") ValidationCriteria criteria) {
		return "/locale/index";
	}
}
