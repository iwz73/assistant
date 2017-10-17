package idv.hsiehpinghan.springbootstarterwebassistant.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import idv.hsiehpinghan.springbootstarterwebassistant.criteria.BasicTypeCriteria;
import idv.hsiehpinghan.springbootstarterwebassistant.entity.BasicEntity;
import idv.hsiehpinghan.springbootstarterwebassistant.property.ConfigurationProperty;
import idv.hsiehpinghan.springbootstarterwebassistant.service.BasicService;

@Controller
@RequestMapping("/basic")
@ConfigurationProperties("prefix")
public class BasicController {
	private final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private ConfigurationProperty configurationProperty;

	@Autowired
	private BasicService service;

	@RequestMapping(value = "/index")
	public String index() {
		LOGGER.info("index");
		return "basic/index";
	}

	@RequestMapping(value = "/save")
	public String save(BasicTypeCriteria criteria) {
		String string = criteria.getString();
		BasicEntity entity = new BasicEntity(string);
		service.save(entity);
		return "redirect:findByString/" + string;
	}

	@RequestMapping(value = "/findByString/{string}")
	public String findByString(@PathVariable("string") String string, Model model) {
		String property = configurationProperty.getProperty();
		model.addAttribute("property", property);
		List<BasicEntity> entities = service.findByString(string);
		model.addAttribute("entities", entities);
		return "basic/result";
	}

}
