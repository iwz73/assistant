package idv.hsiehpinghan.springsocialfacebookassistant.controller;

import idv.hsiehpinghan.springsocialfacebookassistant.criteria.LoginInfoCriteria;
import idv.hsiehpinghan.springsocialfacebookassistant.criteria.LongLivedTokenCriteria;
import idv.hsiehpinghan.springsocialfacebookassistant.service.InfoService;
import idv.hsiehpinghan.springsocialfacebookassistant.service.TokenService;

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
	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/facebookLogin", method = RequestMethod.GET)
	public String facebookLogin() {
		return "/facebook/facebookLogin";
	}

	@RequestMapping(value = "/facebookAutoLogin", method = RequestMethod.GET)
	public String facebookAutoLogin() {
		return "/facebook/facebookAutoLogin";
	}

	@RequestMapping(value = "/token", method = RequestMethod.GET)
	public String token() {
		return "/facebook/token";
	}

	@ResponseBody
	@RequestMapping(value = "/longLivedToken", method = RequestMethod.GET)
	public String longLivedToken(LongLivedTokenCriteria criteria)
			throws IOException {
		String shortLivedToken = criteria.getAccessToken();
		return tokenService.getLongLivedToken(shortLivedToken);
	}

	@ResponseBody
	@RequestMapping(value = "/tokenInfo", method = RequestMethod.GET)
	public String tokenInfo() throws IOException {
		return tokenService.getAppAccessToken();
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
