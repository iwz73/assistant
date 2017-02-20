package idv.hsiehpinghan.springwebflowassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import idv.hsiehpinghan.springwebflowassistant.vo.BasicFlowVo;

@Controller
//@RequestMapping(value = "/basicFlow")
public class BasicFlowController {

	// @RequestMapping(value = "/index", method = RequestMethod.GET)
	// public String index() {
	// return "/controller/index";
	// }

	public BasicFlowVo generateBasicFlowVo() {
		BasicFlowVo vo = new BasicFlowVo();
		vo.setName("basicVo");
		vo.setAge(3);
		return vo;
	}
}
