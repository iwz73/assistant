package idv.hsiehpinghan.seleniumassistant.webdriver;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("htmlUnit_with_javascript")
public class HtmlUnitDriverWithJavascriptExtension extends
		HtmlUnitDriverExtension {
	public HtmlUnitDriverWithJavascriptExtension() {
		super(true);
	}
}
