package org.springframework.social.facebook.api.impl;

import java.io.IOException;

import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.UserOperations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FacebookTemplateTest {
	private final String ACCESS_TOKEN = "CAACEdEose0cBAGfzd7SaSL5Q0eoAQ9RkbEZC0f9vaNXaad7UcyNALGiflgZCyZCIpGoR9RIdGp9vqaz2GhmWGZBsU27ZAC5mk520gB9F92GcYWsivSA6eGZCSZCqq1sCpRreSJb6Ns3eM38VufpXtIrzkq99IpwcrwMdiyTZCMLuPEai8vKq9r0iViOrIZCArfn1omP0luNh67LXqFEZCs2kZAfOsJsYiSZCPo8ZD";
	private FacebookTemplate template;

	@BeforeClass
	public void beforeClass() throws IOException {
		// ApplicationContext applicationContext = TestngSuitSetting
		// .getApplicationContext();
		template = new FacebookTemplate(ACCESS_TOKEN);
	}

	@Test
	public void userOperations() {
		UserOperations userOperations = template.userOperations();
		testUser(userOperations);
	}
	
	private void testUser(UserOperations userOperations) {
		User user = userOperations.getUserProfile();
		Assert.assertEquals(user.getName(), "Hsiehpinghan Thank");
	}
}
