package idv.hsiehpinghan.springassistant.configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration("scheduleScheduledThreadSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.springassistant.schedule.scheduledthread" })
public class ScheduleScheduledThreadSpringConfiguration {
	private final int CORE_POOL_SIZE = 3;

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		ExecutorService executorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
		return executorService;
	}
}
