package idv.hsiehpinghan.springmvcassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/controller")
public class ConsumesAndProducesController {

//	@RequestMapping(value = "/consumesAndProducesController", method = RequestMethod.GET, consumes="application/json", produces="application/xml")
	@RequestMapping(value = "/consumesAndProducesController", method = RequestMethod.GET)
//	public String index(@RequestBody String requestBody) {
	public String index(String requestBody) {
		return "/consumesAndProducesController";
	}

}
