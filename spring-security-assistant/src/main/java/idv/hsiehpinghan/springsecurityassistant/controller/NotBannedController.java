package idv.hsiehpinghan.springsecurityassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/not_banned")
public class NotBannedController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/not_banned/index";
	}
}
