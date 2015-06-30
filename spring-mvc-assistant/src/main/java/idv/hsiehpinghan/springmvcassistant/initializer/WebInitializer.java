package idv.hsiehpinghan.springmvcassistant.initializer;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(final ServletContext servletContext)
			throws ServletException {
		// registerListener(servletContext);
		registerDispatcherServlet(servletContext);
		registerCharacterEncodingFilter(servletContext);
	}

	private void registerListener(final ServletContext servletContext) {
		AnnotationConfigWebApplicationContext applicationContext = createApplicationContext();
		ContextLoaderListener contextLoaderListener = new ContextLoaderListener(
				applicationContext);
		servletContext.addListener(contextLoaderListener);
	}

	private void registerDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext applicationContext = createApplicationContext(idv.hsiehpinghan.springmvcassistant.configuration.SpringConfiguration.class);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(
				applicationContext);
		ServletRegistration.Dynamic registration = servletContext.addServlet(
				"dispatcherServlet", dispatcherServlet);
		registration.addMapping("/");
		registration.setLoadOnStartup(1);
		setMultipartConfigElement(registration);
	}

	private void setMultipartConfigElement(
			ServletRegistration.Dynamic registration) {
		String location = null;
		long maxFileSize = 1024 * 1024 * 5;
		long maxRequestSize = -1L;
		int fileSizeThreshold = 1024 * 1024;
		MultipartConfigElement config = new MultipartConfigElement(location,
				maxFileSize, maxRequestSize, fileSizeThreshold);
		registration.setMultipartConfig(config);
	}
	
	private void registerCharacterEncodingFilter(ServletContext servletContext) {
		FilterRegistration.Dynamic characterEncodingFilter = servletContext
				.addFilter("characterEncodingFilter",
						new CharacterEncodingFilter());
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.setInitParameter("forceEncoding", "true");
		characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
	}

	private AnnotationConfigWebApplicationContext createApplicationContext(
			final Class<?>... annotationClasses) {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.getEnvironment().setActiveProfiles("local");
		applicationContext.register(annotationClasses);
		return applicationContext;
	}

}
