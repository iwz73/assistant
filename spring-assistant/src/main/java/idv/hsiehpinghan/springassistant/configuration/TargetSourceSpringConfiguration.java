package idv.hsiehpinghan.springassistant.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("targetSourceSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.springassistant.pool" })
public class TargetSourceSpringConfiguration {

}
