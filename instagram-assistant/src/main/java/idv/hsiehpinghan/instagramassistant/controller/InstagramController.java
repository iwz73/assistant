package idv.hsiehpinghan.instagramassistant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import idv.hsiehpinghan.instagramassistant.criteria.ServerSideGetCodeCriteria;

@Controller
@RequestMapping(value = "/instagram")
public class InstagramController {

	@RequestMapping(value = "/clientSideAuthentication", method = RequestMethod.GET)
	public String clientSideAuthentication() {
		return "/instagram/clientSideAuthentication";
	}

	@RequestMapping(value = "/serverSideAuthentication", method = RequestMethod.GET)
	public String serverSideAuthentication() {
		return "/instagram/serverSideAuthentication";
	}

	@RequestMapping(value = "/serverSideGetCode", method = RequestMethod.GET)
	public String serverSideGetCode(ServerSideGetCodeCriteria criteria) {
		return "/instagram/serverSideGetCode";
	}

}
