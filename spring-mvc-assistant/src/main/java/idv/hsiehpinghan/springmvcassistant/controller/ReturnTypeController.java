package idv.hsiehpinghan.springmvcassistant.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.springmvcassistant.dto.Data;

@Controller
@RequestMapping(value = "/returnType")
public class ReturnTypeController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "returnType/index";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Collection<Data> list() {
		return generateList();
	}

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public ModelAndView map() {
		ModelAndView mv = new ModelAndView();
		Map<String, Data> map = generateMap();
		mv.setViewName("returnType/map");
		mv.addObject("map", map);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/fileSystemResource/{fileSystemResource:.+}", method = RequestMethod.GET)
	public FileSystemResource fileSystemResource(@PathVariable("fileSystemResource") String fileSystemResource) {
		return new FileSystemResource("/etc/" + fileSystemResource);
	}

	@ResponseBody
	@RequestMapping(value = "/classPathResource/{classPathResource:.+}", method = RequestMethod.GET)
	public ClassPathResource classPathResource(@PathVariable("classPathResource") String classPathResource) {
		return new ClassPathResource(classPathResource);
	}

	@ResponseBody
	@RequestMapping(value = "/json", method = RequestMethod.GET, produces = { "application/json" })
	public Collection<Data> json() {
		return generateList();
	}

	private Collection<Data> generateList() {
		return generateList(3);
	}

	private Map<String, Data> generateMap() {
		return generateMap(3);
	}

	private Collection<Data> generateList(int amount) {
		List<Data> datas = new ArrayList<Data>(amount);
		for (int i = 0; i < 3; ++i) {
			Integer integerValue = i;
			Float floatValue = Float.valueOf("" + i + "." + i);
			String stringValue = String.valueOf("string_" + i);
			Date dateValue = Calendar.getInstance().getTime();
			Data data = new Data(integerValue, floatValue, stringValue, dateValue);
			datas.add(data);
		}
		return datas;
	}

	private Map<String, Data> generateMap(int amount) {
		Map<String, Data> datas = new LinkedHashMap<String, Data>(amount);
		for (int i = 0; i < 3; ++i) {
			Integer integerValue = i;
			Float floatValue = Float.valueOf("" + i + "." + i);
			String stringValue = String.valueOf("string_" + i);
			Date dateValue = Calendar.getInstance().getTime();
			Data data = new Data(integerValue, floatValue, stringValue, dateValue);
			datas.put(String.valueOf(i), data);
		}
		return datas;
	}
}
