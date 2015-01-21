package idv.hsiehpinghan.hdfsassistant.suit;

import idv.hsiehpinghan.objectutility.utility.ClassUtility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.testng.annotations.BeforeSuite;

public class TestngSuitSetting {
	private static ApplicationContext applicationContext;
	private static String userName;

	@BeforeSuite()
	public void beforeSuite() throws Exception {
		Class<?>[] clsArr = ClassUtility.getAnnotatedClasses(
				"idv.hsiehpinghan", Configuration.class);
		applicationContext = new AnnotationConfigApplicationContext(clsArr);
		userName = applicationContext.getEnvironment().getProperty("USER");
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static String getUserName() {
		return userName;
	}
}
