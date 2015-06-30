package idv.hsiehpinghan.springsocialfacebookassistant.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(final ServletContext servletContext)
			throws ServletException {
		// registerListener(servletContext);
		registerDispatcherServlet(servletContext);
	}

	private AnnotationConfigWebApplicationContext createApplicationContext(
			final Class<?>... annotationClasses) {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(annotationClasses);
		return applicationContext;
	}

	private void registerListener(final ServletContext servletContext) {
		AnnotationConfigWebApplicationContext applicationContext = createApplicationContext();
		ContextLoaderListener contextLoaderListener = new ContextLoaderListener(
				applicationContext);
		servletContext.addListener(contextLoaderListener);
	}

	private void registerDispatcherServlet(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext applicationContext = createApplicationContext(idv.hsiehpinghan.springsocialfacebookassistant.configuration.SpringConfiguration.class);
		DispatcherServlet dispatcherServlet = new DispatcherServlet(
				applicationContext);
		ServletRegistration.Dynamic registration = servletContext.addServlet(
				"dispatcherServlet", dispatcherServlet);
		registration.addMapping("/");
		registration.setLoadOnStartup(1);
	}

}
