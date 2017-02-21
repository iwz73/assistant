package idv.hsiehpinghan.springwebflowassistant.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import idv.hsiehpinghan.springwebflowassistant.vo.BasicFlowVo;

@Controller
@RequestMapping(value = "/basicFlow")
public class BasicFlowController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/basicFlow/index";
	}

	public BasicFlowVo generateBasicFlowVo() {
		BasicFlowVo vo = new BasicFlowVo();
		vo.setCategoryId(3);
		return vo;
	}

	public Map<Integer, String> generateCategoryMap() {
		Map<Integer, String> ageMap = new HashMap<Integer, String>();
		for (int i = 0; i < 10; ++i) {
			ageMap.put(i, "categoryId_" + i);
		}
		return ageMap;
	}

	public Map<Integer, String> generateItemMap(BasicFlowVo vo) {
		Integer categoryId = vo.getCategoryId();
		Map<Integer, String> itemMap = new HashMap<Integer, String>();
		for (int i = 0; i < 10; ++i) {
			itemMap.put(i, "categoryId_" + categoryId + "_itemId_" + i);
		}
		return itemMap;
	}
}
