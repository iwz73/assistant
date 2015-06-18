package idv.hsiehpinghan.springmvcassistant.initializer;

import idv.hsiehpinghan.springmvcassistant.configuration.SpringConfiguration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// setProfiles("test", "production");
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.setServletContext(servletContext);
		applicationContext.register(SpringConfiguration.class);
		addServlets(servletContext, applicationContext);
	}

	private void setProfiles(String... profiles) {
		if (profiles == null) {
			return;
		}
		String key = "spring.profiles.active";
		String p = System.getProperty(key);
		if (p == null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0, size = profiles.length; i < size; ++i) {
				if (i != 0) {
					sb.append(",");
				}
				sb.append(profiles[i]);
			}
			System.setProperty(key, sb.toString());
		}
	}

	private void addServlets(ServletContext servletContext,
			AnnotationConfigWebApplicationContext applicationContext) {
		ServletRegistration.Dynamic dispatcherServlet = servletContext
				.addServlet("dispatcherServlet", new DispatcherServlet(
						applicationContext));
		dispatcherServlet.addMapping("/");
		dispatcherServlet.setLoadOnStartup(1);
	}

	// private void addListener(ServletContext servletContext,
	// AnnotationConfigWebApplicationContext applicationContext) {
	// servletContext.addListener(new
	// ContextLoaderListener(applicationContext));
	// }
}
