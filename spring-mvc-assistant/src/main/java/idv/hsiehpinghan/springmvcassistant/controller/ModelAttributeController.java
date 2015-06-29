package idv.hsiehpinghan.springmvcassistant.controller;

import idv.hsiehpinghan.springmvcassistant.criteria.SpringMvcFormCriteria;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/modelAttribute")
public class ModelAttributeController {
	private final List<String> list;

	public ModelAttributeController() {
		list = new ArrayList<String>(3);
		list.add("item 1");
		list.add("item 2");
		list.add("item 3");
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(
			@ModelAttribute("criteria") SpringMvcFormCriteria criteria) {
		ModelAndView modelAndView = new ModelAndView("/modelAttribute/index");
		modelAndView.addObject("checkboxs", generateDynamicCheckboxs());
		modelAndView.addObject("radiobuttons", generateDynamicRadioButtons());
		modelAndView.addObject("select", generateDynamicSelect());
		modelAndView.addObject("options", generateDynamicOptions());
		return modelAndView;
	}

	@ModelAttribute("list")
	public List<String> getList() {
		return list;
	}

	private List<String> generateDynamicCheckboxs() {
		List<String> list = new ArrayList<String>(3);
		list.add("dynamic checkboxs 1");
		list.add("dynamic checkboxs 2");
		list.add("dynamic checkboxs 3");
		return list;
	}
	
	private List<String> generateDynamicRadioButtons() {
		List<String> list = new ArrayList<String>(3);
		list.add("dynamic radio button 1");
		list.add("dynamic radio button 2");
		list.add("dynamic radio button 3");
		return list;
	}
	
	private Map<String,String> generateDynamicSelect() {
		Map<String,String> map = new LinkedHashMap<String, String>(3);
		map.put("key_1", "value 1");
		map.put("key_2", "value 2");
		map.put("key_3", "value 3");
		return map;
	}
	
	private Map<String,String> generateDynamicOptions() {
		Map<String,String> map = new LinkedHashMap<String, String>(3);
		map.put("key_1", "value 1");
		map.put("key_2", "value 2");
		map.put("key_3", "value 3");
		return map;
	}
}
