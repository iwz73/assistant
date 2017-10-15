package idv.hsiehpinghan.springbootstartersecurityassistant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/common")
public class CommonController {
	private final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		LOGGER.debug("index");
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
