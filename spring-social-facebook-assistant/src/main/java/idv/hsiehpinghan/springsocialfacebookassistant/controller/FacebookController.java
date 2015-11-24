package idv.hsiehpinghan.springsocialfacebookassistant.controller;

import idv.hsiehpinghan.springsocialfacebookassistant.criteria.LoginInfoCriteria;
import idv.hsiehpinghan.springsocialfacebookassistant.service.InfoService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/facebook")
public class FacebookController {
	@Autowired
	private InfoService infoService;

	@RequestMapping(value = "/facebookLogin", method = RequestMethod.GET)
	public String facebookLogin() {
		return "/facebook/facebookLogin";
	}

	@RequestMapping(value = "/facebookAutoLogin", method = RequestMethod.GET)
	public String facebookAutoLogin() {
		return "/facebook/facebookAutoLogin";
	}

	@ResponseBody
	@RequestMapping(value = "/loginInfo", method = RequestMethod.GET)
	public String loginInfo(LoginInfoCriteria criteria) throws IOException {
		String accessToken = criteria.getAccessToken();
		return infoService.getLoginInfo(accessToken);
	}

	@RequestMapping(value = "/socialPlugin", method = RequestMethod.GET)
	public String socialPlugin() {
		return "/facebook/socialPlugin";
	}
}
