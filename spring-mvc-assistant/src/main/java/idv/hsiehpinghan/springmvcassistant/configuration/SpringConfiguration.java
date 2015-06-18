package idv.hsiehpinghan.springmvcassistant.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "idv.hsiehpinghan" })
public class SpringConfiguration extends WebMvcConfigurerAdapter {
	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/index.html").addResourceLocations("/");
		registry.addResourceHandler("/image/**")
				.addResourceLocations("/image/");
		registry.addResourceHandler("/javascript/**").addResourceLocations(
				"/javascript/");
	}

	@Configuration
	@Profile(value = "test")
	public static class TestSpringConfiguration {
	}

	@Configuration
	@Profile(value = "production")
	public static class ProductionSpringConfiguration {
	}
}
