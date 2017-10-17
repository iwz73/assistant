package idv.hsiehpinghan.springbootstarterwebassistant.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import idv.hsiehpinghan.springbootstarterwebassistant.condition.TestCondition;
import idv.hsiehpinghan.springbootstarterwebassistant.controller.BasicController;

@Conditional(TestCondition.class)
public class SpringConditionalConfiguration {
	// private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean
	@ConditionalOnBean({ BasicController.class })
	public String conditionalOnBean() {
		System.err.println("conditionalOnBean");
		return "conditionalOnBean";
	}

	@Bean
	@ConditionalOnMissingBean({ Void.class })
	public String conditionalOnMissingBean() {
		System.err.println("conditionalOnMissingBean");
		return "conditionalOnMissingBean";
	}

	@Bean
	@ConditionalOnClass({ Integer.class })
	public String conditionalOnClass() {
		System.err.println("conditionalOnClass");
		return "conditionalOnClass";
	}

	@Bean
	@ConditionalOnMissingClass({ "org.junit.Test" })
	public String conditionalOnMissingClass() {
		System.err.println("conditionalOnMissingClass");
		return "conditionalOnMissingClass";
	}

	@Bean
	@ConditionalOnExpression("${something.enabled}==true")
	public String conditionalOnExpression() {
		System.err.println("conditionalOnExpression");
		return "conditionalOnExpression";
	}

	@Bean
	@ConditionalOnJava(ConditionalOnJava.JavaVersion.EIGHT)
	public String conditionalOnJava() {
		System.err.println("conditionalOnJava");
		return "conditionalOnJava";
	}

	@Bean
	@ConditionalOnProperty(prefix = "something", value = "enabled", havingValue = "true")
	public String conditionalOnProperty() {
		System.err.println("conditionalOnProperty");
		return "conditionalOnProperty";
	}

	@Bean
	@ConditionalOnResource(resources = "classpath:/static/style.css")
	public String conditionalOnResource() {
		System.err.println("conditionalOnResource");
		return "conditionalOnResource";
	}

	@Bean
	@ConditionalOnWebApplication
	public String conditionalOnWebApplication() {
		System.err.println("conditionalOnWebApplication");
		return "conditionalOnWebApplication";
	}

}
