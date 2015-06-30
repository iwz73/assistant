package org.springframework.social.facebook.api.impl;

import idv.hsiehpinghan.springsocialfacebookassistant.suit.TestngSuitSetting;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.social.facebook.api.AchievementOperations;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FacebookTemplateTest {
	private FacebookTemplate template;

	@BeforeClass
	public void beforeClass() throws IOException {
//		ApplicationContext applicationContext = TestngSuitSetting
//				.getApplicationContext();
		template = new FacebookTemplate("");
	}

	@Test
	public void achievementOperations() {
//		AchievementOperations operations = template.achievementOperations();
		System.err.println("ttt");
		
	}
}
