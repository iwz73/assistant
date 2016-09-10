package idv.hsiehpinghan.instagramassistant.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@EnableWebMvc
@Configuration
@PropertySource("classpath:/instagram_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.instagramassistant" })
public class SpringConfiguration extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/index.html").addResourceLocations("/");
		registry.addResourceHandler("/image/**").addResourceLocations("/image/");
		registry.addResourceHandler("/javascript/**").addResourceLocations("/javascript/");
	}

	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

}
