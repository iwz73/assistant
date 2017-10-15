package idv.hsiehpinghan.springbootstartersecurityassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/common")
public class CommonController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "common/index";
	}

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String loginPage() {
		return "common/loginPage";
	}

	@RequestMapping(value = "/loginFailPage", method = RequestMethod.GET)
	public String loginFailPage() {
		return "common/loginFailPage";
	}

	@RequestMapping(value = "/logoutPage", method = RequestMethod.GET)
	public String logoutPage() {
		return "common/logoutPage";
	}
}
