package idv.hsiehpinghan.springsocialfacebookassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/facebook")
public class FacebookController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/facebook/index";
	}

}
