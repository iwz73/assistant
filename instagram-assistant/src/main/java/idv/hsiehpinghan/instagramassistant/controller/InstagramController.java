package idv.hsiehpinghan.instagramassistant.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import idv.hsiehpinghan.instagramassistant.criteria.ServerSideAssessTokenCriteria;
import idv.hsiehpinghan.instagramassistant.criteria.ServerSideEndpointResultCriteria;
import idv.hsiehpinghan.instagramassistant.service.InfoService;
import idv.hsiehpinghan.instagramassistant.service.TokenService;

@Controller
@RequestMapping(value = "/instagram")
public class InstagramController {
	@Autowired
	private Environment environment;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private InfoService infoService;

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
		String serverSideRedirectUri = environment.getRequiredProperty("serverSideRedirectUri");
		String accessTokenJson = tokenService.getAccessTokenJson(code, serverSideRedirectUri);
		mv.addObject("accessTokenJson", accessTokenJson);
		return mv;
	}

	@RequestMapping(value = "/serverSideEndpoint", method = RequestMethod.GET)
	public ModelAndView serverSideEndpoint() {
		ModelAndView mv = new ModelAndView("/instagram/serverSideEndpoint");
		String clientId = environment.getRequiredProperty("clientId");
		String serverSideEndpointRedirectUri = environment.getRequiredProperty("serverSideEndpointRedirectUri");
		mv.addObject("clientId", clientId);
		mv.addObject("serverSideEndpointRedirectUri", serverSideEndpointRedirectUri);
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = "/serverSideEndpointResult", method = RequestMethod.GET)
	public String serverSideEndpointResult(ServerSideEndpointResultCriteria criteria) throws IOException {
		String code = criteria.getCode();
		String endpoint = criteria.getEndpoint();
		String serverSideEndpointRedirectUri = environment.getRequiredProperty("serverSideEndpointRedirectUri")
				+ "?endpoint=" + endpoint;
		return infoService.getEndpointData(code, endpoint, serverSideEndpointRedirectUri);
	}
}
