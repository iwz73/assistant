package idv.hsiehpinghan.springbootstarterwebassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

	@RequestMapping(value = "/index")
	public String index() {
		return "index";
	}

}
