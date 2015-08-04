package idv.hsiehpinghan.springmvcassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/controller")
public class ControllerController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/controller/index";
	}
}
