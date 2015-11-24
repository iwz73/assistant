package idv.hsiehpinghan.springsocialfacebookassistant.controller;

import idv.hsiehpinghan.springsocialfacebookassistant.criteria.FacebookTemplateOtherUserCriteria;
import idv.hsiehpinghan.springsocialfacebookassistant.criteria.FacebookTemplateUserCriteria;
import idv.hsiehpinghan.springsocialfacebookassistant.criteria.LoginInfoCriteria;
import idv.hsiehpinghan.springsocialfacebookassistant.service.FacebookTemplateService;
import idv.hsiehpinghan.springsocialfacebookassistant.service.InfoService;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookServiceProvider;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "/facebook")
public class FacebookController {
	@Autowired
	private InfoService infoService;
	@Autowired
	private FacebookTemplateService facebookTemplateService;

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

	@RequestMapping(value = "/facebookTemplate", method = RequestMethod.GET)
	public String facebookTemplate() {
		return "/facebook/facebookTemplate";
	}

	@ResponseBody
	@RequestMapping(value = "/facebookTemplateUser", method = RequestMethod.GET)
	public User facebookTemplateUser(FacebookTemplateUserCriteria criteria) {
		String accessToken = criteria.getAccessToken();
		return facebookTemplateService.getUser(accessToken);
	}

	@ResponseBody
	@RequestMapping(value = "/facebookTemplateOtherUser", method = RequestMethod.GET)
	public User facebookTemplateOtherUser(
			FacebookTemplateOtherUserCriteria criteria) {
		String accessToken = criteria.getAccessToken();
		String otherUserId = criteria.getOtherUserId();
		return facebookTemplateService.getOtherUser(accessToken, otherUserId);
	}
	
	@RequestMapping(value = "/facebookSigIn",method = RequestMethod.GET)
	public ModelAndView facebookSigin() {
		// TODO :not finish yet.
		FacebookServiceProvider facebookServiceProvider = new FacebookServiceProvider("", "", "");
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("http://localhost:8080/spring-social-facebook-assistant/facebook/facebookCallBack");	
		System.out.println(facebookServiceProvider.getOAuthOperations().buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE,params));
		String authorizeUrl = facebookServiceProvider.getOAuthOperations().buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE,params);
		RedirectView redirectView = new RedirectView(authorizeUrl, true, true, true);
		return new ModelAndView(redirectView);
	}

	@RequestMapping(value = "/facebookCallBack",method = RequestMethod.GET)
	public ModelAndView facebookCallBack(@RequestParam("code") String code) {
		// TODO :not finish yet.
		System.out.println(code);
		FacebookServiceProvider facebookServiceProvider = new FacebookServiceProvider("", "", "");
		AccessGrant accessGrant = facebookServiceProvider.getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/spring-social-facebook-assistant/facebook/facebookCallBack", null);
		System.out.println(ToStringBuilder.reflectionToString(accessGrant));
		return new ModelAndView("");
	}
}
