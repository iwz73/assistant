package idv.hsiehpinghan.rassistant.configuration;

import org.rosuda.JRI.Rengine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("rAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.rassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Bean
	public Rengine rengine() {
		if (Rengine.versionCheck() == false) {
			throw new RuntimeException("Version check fail !!!");
		}
		return new Rengine(new String[] { "--save" }, false, null);
	}
}
