package idv.hsiehpinghan.springmvcassistant.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "idv.hsiehpinghan.springmvcassistant" })
public class SpringConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/index.html").addResourceLocations("/");
		registry.addResourceHandler("/image/**")
				.addResourceLocations("/image/");
		registry.addResourceHandler("/javascript/**").addResourceLocations(
				"/javascript/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/controller/addViewControllers")
				.setViewName("/controller/addViewControllers");
	}

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean(name = "/controller/parameterizableViewController")
	public Controller parameterizableViewController() {
		ParameterizableViewController controller = new ParameterizableViewController();
		controller.setViewName("/controller/parameterizableViewController");
		return controller;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	@Configuration
	@Profile(value = "local")
	public static class LocalSpringConfiguration {
	}

	@Configuration
	@Profile(value = "test")
	public static class TestSpringConfiguration {
	}

	@Configuration
	@Profile(value = "uat")
	public static class UatSpringConfiguration {
	}

	@Configuration
	@Profile(value = "prod")
	public static class ProdSpringConfiguration {
	}
}
