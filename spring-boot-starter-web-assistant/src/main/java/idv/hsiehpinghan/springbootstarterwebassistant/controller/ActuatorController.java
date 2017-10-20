package idv.hsiehpinghan.springbootstarterwebassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/actuator")
public class ActuatorController {
	@RequestMapping(value = "/shutdown", method = RequestMethod.GET)
	public String index() {
		return "actuator/shutdown";
	}
}
