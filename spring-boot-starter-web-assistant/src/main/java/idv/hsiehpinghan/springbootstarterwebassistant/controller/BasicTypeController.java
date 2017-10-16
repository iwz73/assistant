package idv.hsiehpinghan.springbootstarterwebassistant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import idv.hsiehpinghan.springbootstarterwebassistant.criteria.BasicTypeCriteria;
import idv.hsiehpinghan.springbootstarterwebassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.springbootstarterwebassistant.service.BasicTypeService;

@Controller
@RequestMapping("/basicType")
public class BasicTypeController {
	@Autowired
	private BasicTypeService service;

	@RequestMapping(value = "/index")
	public String index() {
		return "basicType/index";
	}

	@RequestMapping(value = "/save")
	public String save(BasicTypeCriteria criteria) {
		String string = criteria.getString();
		BasicTypeEntity entity = new BasicTypeEntity(string);
		service.save(entity);
		return "redirect:findByString/" + string;
	}

	@RequestMapping(value = "/findByString/{string}")
	public String findByString(@PathVariable("string") String string, Model model) {
		List<BasicTypeEntity> entities = service.findByString(string);
		model.addAttribute("entities", entities);
		return "basicType/result";
	}

}
