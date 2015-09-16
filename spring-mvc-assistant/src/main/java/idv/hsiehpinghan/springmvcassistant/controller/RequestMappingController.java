package idv.hsiehpinghan.springmvcassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/requestMapping")
public class RequestMappingController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/requestMapping/index";
	}

	@ResponseBody
	@RequestMapping(value = "/produces", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public String produces() {
		return "<html><body>@RequestMapping produces 测试</body></html>";
	}
}
