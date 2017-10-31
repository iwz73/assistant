package idv.hsiehpinghan.springbootstarterwebassistant.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.springbootstarterwebassistant.criteria.BasicTypeCriteria;
import idv.hsiehpinghan.springbootstarterwebassistant.entity.BasicEntity;
import idv.hsiehpinghan.springbootstarterwebassistant.property.ConfigurationProperty;
import idv.hsiehpinghan.springbootstarterwebassistant.service.BasicService;

@Controller
@RequestMapping("/basic")
@ConfigurationProperties("prefix")
public class BasicController {
	private final Logger LOGGER = LoggerFactory.getLogger(BasicController.class);

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
		return String.format("redirect:findByString/%s", string);
	}

	@RequestMapping(value = "/findByString/{string}")
	public ModelAndView findByString(@PathVariable("string") String string) {
		String property = configurationProperty.getProperty();
		ModelAndView mv = new ModelAndView("basic/result");
		mv.addObject("property", property);
		List<BasicEntity> entities = service.findByString(string);
		mv.addObject("entities", entities);
		return mv;
	}

}
