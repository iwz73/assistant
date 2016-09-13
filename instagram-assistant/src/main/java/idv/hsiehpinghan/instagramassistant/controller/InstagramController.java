package idv.hsiehpinghan.instagramassistant.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.instagramassistant.criteria.ServerSideAssessTokenCriteria;
import idv.hsiehpinghan.instagramassistant.service.TokenService;

@Controller
@RequestMapping(value = "/instagram")
public class InstagramController {
	@Autowired
	private Environment environment;
	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/clientSideAuthentication", method = RequestMethod.GET)
	public ModelAndView clientSideAuthentication() {
		ModelAndView mv = new ModelAndView("/instagram/clientSideAuthentication");
		String clientId = environment.getRequiredProperty("clientId");
		String clientSideRedirectUri = environment.getRequiredProperty("clientSideRedirectUri");
		mv.addObject("clientId", clientId);
		mv.addObject("clientSideRedirectUri", clientSideRedirectUri);
		return mv;
	}

	@RequestMapping(value = "/serverSideAuthentication", method = RequestMethod.GET)
	public ModelAndView serverSideAuthentication() {
		ModelAndView mv = new ModelAndView("/instagram/serverSideAuthentication");
		String clientId = environment.getRequiredProperty("clientId");
		String serverSideRedirectUri = environment.getRequiredProperty("serverSideRedirectUri");
		mv.addObject("clientId", clientId);
		mv.addObject("serverSideRedirectUri", serverSideRedirectUri);
		return mv;
	}

	@RequestMapping(value = "/serverSideAssessToken", method = RequestMethod.GET)
	public ModelAndView serverSideAssessToken(ServerSideAssessTokenCriteria criteria) throws IOException {
		ModelAndView mv = new ModelAndView("/instagram/serverSideAssessToken");
		String code = criteria.getCode();
		String accessToken = tokenService.getAccessToken(code);
		mv.addObject("accessToken", accessToken);
		return mv;
	}

	@RequestMapping(value = "/serverEndpoint", method = RequestMethod.GET)
	public String serverEndpoint() {
		return "/instagram/serverEndpoint";
	}

}
