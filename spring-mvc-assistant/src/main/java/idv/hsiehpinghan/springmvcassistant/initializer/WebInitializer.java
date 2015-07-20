package idv.hsiehpinghan.springmvcassistant.initializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { idv.hsiehpinghan.springmvcassistant.configuration.SpringConfiguration.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] { characterEncodingFilter };
	}

	@Override
	protected void customizeRegistration(
			ServletRegistration.Dynamic registration) {
		registration.setMultipartConfig(generateMultipartConfigElement());
	}

	private MultipartConfigElement generateMultipartConfigElement() {
		String location = null;
		long maxFileSize = 1024 * 1024 * 5;
		long maxRequestSize = -1L;
		int fileSizeThreshold = 1024 * 1024;
		return new MultipartConfigElement(location, maxFileSize,
				maxRequestSize, fileSizeThreshold);
	}

}
