package idv.hsiehpinghan.springmvcassistant.controller;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/methodArgumentType")
public class MethodArgumentTypeController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/methodArgumentType/index";
	}
	
	@RequestMapping(value = "/servletRequest", method = RequestMethod.GET)
	public ModelAndView servletRequest(ServletRequest servletRequest) {
		ModelAndView modelAndView = new ModelAndView(
				"/methodArgumentType/index");
		String parameter = servletRequest.getParameter("parameter");
		modelAndView.addObject("parameter", parameter);
		return modelAndView;
	}

	@RequestMapping(value = "/httpServletRequest", method = RequestMethod.GET)
	public ModelAndView httpServletRequest(HttpServletRequest httpServletRequest) {
		ModelAndView modelAndView = new ModelAndView(
				"/methodArgumentType/index");
		String parameter = httpServletRequest.getParameter("parameter");
		modelAndView.addObject("parameter", parameter);
		return modelAndView;
	}

	@RequestMapping(value = "/multipartRequest", method = RequestMethod.POST)
	public ModelAndView multipartRequest(MultipartRequest multipartRequest) {
		ModelAndView modelAndView = new ModelAndView(
				"/methodArgumentType/index");
		MultipartFile multipartFile = multipartRequest.getFile("uploadedFile");
		modelAndView.addObject("parameter",
				"file name:" + multipartFile.getOriginalFilename()
						+ "; file size:" + multipartFile.getSize());
		return modelAndView;
	}

	@RequestMapping(value = "/multipartHttpServletRequest", method = RequestMethod.POST)
	public ModelAndView multipartHttpServletRequest(
			MultipartHttpServletRequest multipartHttpServletRequest) {
		ModelAndView modelAndView = new ModelAndView(
				"/methodArgumentType/index");
		MultipartFile multipartFile = multipartHttpServletRequest
				.getFile("uploadedFile");
		modelAndView.addObject("parameter",
				"file name:" + multipartFile.getOriginalFilename()
						+ "; file size:" + multipartFile.getSize());
		return modelAndView;
	}

	@RequestMapping(value = "/httpSession", method = RequestMethod.GET)
	public ModelAndView httpSession(HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView(
				"/methodArgumentType/index");
		modelAndView
				.addObject("parameter", "session id:" + httpSession.getId());
		return modelAndView;
	}

	@RequestMapping(value = "/webRequest", method = RequestMethod.GET)
	public ModelAndView webRequest(WebRequest webRequest) {
		ModelAndView modelAndView = new ModelAndView(
				"/methodArgumentType/index");
		String parameter = webRequest.getParameter("parameter");
		modelAndView.addObject("parameter", "parameter:" + parameter
				+ "; session id:" + webRequest.getSessionId());
		return modelAndView;
	}

	@RequestMapping(value = "/nativeWebRequest", method = RequestMethod.GET)
	public ModelAndView nativeWebRequest(NativeWebRequest nativeWebRequest) {
		ModelAndView modelAndView = new ModelAndView(
				"/methodArgumentType/index");
		String parameter = nativeWebRequest.getParameter("parameter");
		modelAndView.addObject("parameter", "parameter:" + parameter
				+ "; session id:" + nativeWebRequest.getSessionId());
		return modelAndView;
	}

	@RequestMapping(value = "/locale", method = RequestMethod.GET)
	public ModelAndView locale(Locale locale) {
		ModelAndView modelAndView = new ModelAndView(
				"/methodArgumentType/index");
		modelAndView.addObject("parameter", "language:" + locale.getLanguage());
		return modelAndView;
	}

	@RequestMapping(value = "/principal", method = RequestMethod.GET)
	public ModelAndView principal(Principal principal) {
		ModelAndView modelAndView = new ModelAndView(
				"/methodArgumentType/index");
		modelAndView.addObject("parameter", "name:"
				+ (principal == null ? null : principal.getName()));
		return modelAndView;
	}

	@RequestMapping(value = "/requestParam", method = RequestMethod.GET)
	public ModelAndView requestParam(@RequestParam Integer integerValue, @RequestParam Float floatValue, @RequestParam String stringValue) {
		ModelAndView modelAndView = new ModelAndView("/methodArgumentType/index");
		modelAndView.addObject("parameter", "integerValue:" + integerValue + "; floatValue:" + floatValue + "; stringValue:" + stringValue);
		return modelAndView;
	}
	
	@RequestMapping(value = "/redirectAttributes", method = RequestMethod.POST)
	public String redirectAttributes(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("parameter", "flashAttribute");
		return "redirect:/methodArgumentType/index";
	}

}
