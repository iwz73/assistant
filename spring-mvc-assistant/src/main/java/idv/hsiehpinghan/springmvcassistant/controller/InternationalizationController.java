package idv.hsiehpinghan.springmvcassistant.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/internationalization")
public class InternationalizationController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView("/internationalization/index");
		Object language = httpSession.getAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE");
		if (language != null) {
			mv.addObject("currentInternationalize", language);
		}
		return mv;
	}

}
