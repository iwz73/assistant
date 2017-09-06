package idv.hsiehpinghan.springsecurityassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/common")
public class CommonController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/common/index";
	}
}
