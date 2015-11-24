package idv.hsiehpinghan.springsocialfacebookassistant.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;

@Service
public class FacebookTemplateService implements InitializingBean {
	private String appNamespace;
	private String appId;
	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		appNamespace = environment.getRequiredProperty("appNamespace");
		appId = environment.getRequiredProperty("appId");
	}

	public boolean isAuthorized(String accessToken) {
		FacebookTemplate facebookTemplate = generateFacebookTemplate(accessToken);
		return facebookTemplate.isAuthorized();
	}

	public User getUser(String accessToken) {
		FacebookTemplate facebookTemplate = generateFacebookTemplate(accessToken);
		UserOperations userOperations = facebookTemplate.userOperations();
		return userOperations.getUserProfile();
	}

	public User getOtherUser(String accessToken, String otherUserId) {
		FacebookTemplate facebookTemplate = generateFacebookTemplate(accessToken);
		UserOperations userOperations = facebookTemplate.userOperations();
		return userOperations.getUserProfile(otherUserId);
	}

	private FacebookTemplate generateFacebookTemplate(String accessToken) {
		return new FacebookTemplate(accessToken, appNamespace, appId);
	}
}
