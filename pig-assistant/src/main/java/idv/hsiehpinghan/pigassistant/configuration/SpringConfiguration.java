package idv.hsiehpinghan.pigassistant.configuration;

import org.apache.pig.ExecType;
import org.apache.pig.PigServer;
import org.apache.pig.backend.executionengine.ExecException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("pigAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.pigassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Bean
	public PigServer pigServer() throws ExecException {
		return new PigServer(ExecType.LOCAL);
	}

}
