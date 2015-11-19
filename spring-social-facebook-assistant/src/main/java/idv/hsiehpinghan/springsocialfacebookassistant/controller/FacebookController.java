package idv.hsiehpinghan.springsocialfacebookassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/facebook")
public class FacebookController {
	@RequestMapping(value = "/facebookLogin", method = RequestMethod.GET)
	public String facebookLogin() {
		return "/facebook/facebookLogin";
	}

	@RequestMapping(value = "/facebookAutoLogin", method = RequestMethod.GET)
	public String facebookAutoLogin() {
		return "/facebook/facebookAutoLogin";
	}

	@RequestMapping(value = "/socialPlugin", method = RequestMethod.GET)
	public String socialPlugin() {
		return "/facebook/socialPlugin";
	}
}
