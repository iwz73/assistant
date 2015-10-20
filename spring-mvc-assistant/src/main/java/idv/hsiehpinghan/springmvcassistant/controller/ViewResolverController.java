package idv.hsiehpinghan.springmvcassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/viewResolver")
public class ViewResolverController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/viewResolver/index";
	}

	@RequestMapping(value = "/excel/{fileName}", method = RequestMethod.GET)
	public ModelAndView excel(@PathVariable String fileName) {
		ModelAndView mv = new ModelAndView("xlsExcelView");
		String sheetName = "MySheetName";
		mv.addObject("sheetName", sheetName);
		return mv;
	}
}
