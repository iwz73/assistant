package idv.hsiehpinghan.springsocialfacebookassistant.suit;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.testng.annotations.BeforeSuite;

public class TestngSuitSetting {
	private static ApplicationContext applicationContext;

	@BeforeSuite()
	public void beforeSuite() throws Exception {
		Class<?>[] clsArr = getAnnotatedClasses("idv.hsiehpinghan", Configuration.class);
		applicationContext = new AnnotationConfigApplicationContext(clsArr);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	private static Class<?>[] getAnnotatedClasses(String basePackage, Class<? extends Annotation> annotation)
			throws ClassNotFoundException {
		return getClassesByFilter(basePackage, new AnnotationTypeFilter(annotation));
	}

	private static Class<?>[] getClassesByFilter(String basePackage, TypeFilter filter) throws ClassNotFoundException {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(filter);
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (BeanDefinition beanDef : scanner.findCandidateComponents(basePackage)) {
			Class<?> clazz = Class.forName(beanDef.getBeanClassName());
			classes.add(clazz);
		}
		Class<?>[] clsArr = new Class<?>[classes.size()];
		classes.toArray(clsArr);
		return clsArr;
	}
}
