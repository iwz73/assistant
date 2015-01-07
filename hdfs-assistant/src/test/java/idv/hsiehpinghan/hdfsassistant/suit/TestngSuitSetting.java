package idv.hsiehpinghan.hdfsassistant.suit;

import java.io.IOException;

import idv.hsiehpinghan.packageutility.utility.PackageUtility;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.BeforeSuite;

public class TestngSuitSetting {
	private static ApplicationContext applicationContext;
	private static String userName;
	
	@BeforeSuite()
	public void beforeSuite() throws IOException {
		String[] pkgs = PackageUtility.getSpringConfigurationPackages();
		applicationContext = new AnnotationConfigApplicationContext(pkgs);
		userName = applicationContext.getEnvironment().getProperty("USER");
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	
	public static String getUserName() {
		return userName;
	}
}
