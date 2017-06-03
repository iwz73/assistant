package idv.hsiehpinghan.springassistant.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration("scheduleSingleThreadSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.springassistant.schedule.singlethread" })
public class ScheduleSingleThreadSpringConfiguration {
}
