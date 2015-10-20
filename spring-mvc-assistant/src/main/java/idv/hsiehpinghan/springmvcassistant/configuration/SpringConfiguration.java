package idv.hsiehpinghan.springmvcassistant.configuration;

import idv.hsiehpinghan.springmvcassistant.view.XlsExcelView;
import idv.hsiehpinghan.springmvcassistant.viewresolver.MapViewResolver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.ParameterizableViewController;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
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

//	@Bean
//	@Autowired
//	public ContentNegotiatingViewResolver contentNegotiatingViewResolver() {
//		ContentNegotiatingViewResolver contentNegotiatingViewResolver = new ContentNegotiatingViewResolver();
//		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
//		viewResolvers.add(xlsViewResolver());
//		viewResolvers.add(urlBasedViewResolver());
//		contentNegotiatingViewResolver.setViewResolvers(viewResolvers);
//		return contentNegotiatingViewResolver;
//	}

	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true).defaultContentType(
				MediaType.TEXT_HTML);
	}

    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
        resolvers.add(xlsViewResolver());
        resolvers.add(urlBasedViewResolver());        
        resolver.setViewResolvers(resolvers);
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

	private UrlBasedViewResolver urlBasedViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	private ViewResolver xlsViewResolver() {
		MapViewResolver viewResolver = new MapViewResolver();
		viewResolver.putView("xlsExcelView", new XlsExcelView());
		return viewResolver;
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
