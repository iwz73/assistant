package idv.hsiehpinghan.springmvcassistant.viewresolver;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class MapViewResolver implements ViewResolver {
	private Map<String, View> views = new HashMap<String, View>();

	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		return this.views.get(viewName);
	}

	public void putView(String viewName, View view) {
		views.put(viewName, view);
	}

}