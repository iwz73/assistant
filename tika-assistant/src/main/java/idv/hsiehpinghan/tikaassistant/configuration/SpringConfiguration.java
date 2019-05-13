package idv.hsiehpinghan.tikaassistant.configuration;

import org.apache.tika.Tika;
import org.apache.tika.parser.html.HtmlParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "idv.hsiehpinghan.tikaassistant" })
public class SpringConfiguration {

	@Bean
	public Tika tika() {
		return new Tika();
	}

	@Bean
	public HtmlParser htmlParser() {
		return new HtmlParser();
	}
}
