package idv.hsiehpinghan.seleniumchromedriverassistant.configuration;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import idv.hsiehpinghan.seleniumchromedriverassistant.factory.ChromeDriverFactory;

@Configuration("seleniumChromeDriverAssistantSpringConfiguration")
@PropertySource("classpath:/selenium_chrome_driver_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.seleniumchromedriverassistant" })
public class SpringConfiguration implements InitializingBean {
	private boolean lifo = GenericObjectPoolConfig.DEFAULT_LIFO;
	private long maxWaitMillis = GenericObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;
	private long minEvictableIdleTimeMillis = GenericObjectPoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
	private long evictorShutdownTimeoutMillis = GenericObjectPoolConfig.DEFAULT_EVICTOR_SHUTDOWN_TIMEOUT_MILLIS;
	private long softMinEvictableIdleTimeMillis = GenericObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_TIME_MILLIS;
	private int numTestsPerEvictionRun = GenericObjectPoolConfig.DEFAULT_NUM_TESTS_PER_EVICTION_RUN;
	private String evictionPolicyClassName = GenericObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME;
	private boolean testOnCreate = GenericObjectPoolConfig.DEFAULT_TEST_ON_CREATE;
	private boolean testOnBorrow = GenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW;
	private boolean testOnReturn = GenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN;
	private boolean testWhileIdle = GenericObjectPoolConfig.DEFAULT_TEST_WHILE_IDLE;
	private long timeBetweenEvictionRunsMillis = GenericObjectPoolConfig.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS;
	private boolean blockWhenExhausted = GenericObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED;
	private int maxTotal;
	private int maxIdle;
	private int minIdle;
	@Autowired
	private Environment environment;
	@Autowired
	private ChromeDriverFactory chromeDriverFactory;

	@Override
	public void afterPropertiesSet() throws Exception {
		maxTotal = environment.getRequiredProperty("max_total", Integer.class);
		maxIdle = environment.getRequiredProperty("max_idle", Integer.class);
		minIdle = environment.getRequiredProperty("min_idle", Integer.class);
	}

	@Bean
	public GenericObjectPool<ChromeDriver> chromeDriverPool() {
		GenericObjectPoolConfig genericObjectPoolConfig = generateGenericObjectPoolConfig();
		GenericObjectPool<ChromeDriver> chromeDriverPool = new GenericObjectPool<>(chromeDriverFactory,
				genericObjectPoolConfig);
		return chromeDriverPool;
	}

	private GenericObjectPoolConfig generateGenericObjectPoolConfig() {
		GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
		genericObjectPoolConfig.setLifo(lifo);
		genericObjectPoolConfig.setMaxIdle(maxIdle);
		genericObjectPoolConfig.setMinIdle(minIdle);
		genericObjectPoolConfig.setMaxTotal(maxTotal);
		genericObjectPoolConfig.setMaxWaitMillis(maxWaitMillis);
		genericObjectPoolConfig.setBlockWhenExhausted(blockWhenExhausted);
		genericObjectPoolConfig.setTestOnCreate(testOnCreate);
		genericObjectPoolConfig.setTestOnBorrow(testOnBorrow);
		genericObjectPoolConfig.setTestOnReturn(testOnReturn);
		genericObjectPoolConfig.setTestWhileIdle(testWhileIdle);
		genericObjectPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
		genericObjectPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		genericObjectPoolConfig.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
		genericObjectPoolConfig.setEvictionPolicyClassName(evictionPolicyClassName);
		genericObjectPoolConfig.setEvictorShutdownTimeoutMillis(evictorShutdownTimeoutMillis);
		return genericObjectPoolConfig;
	}

}
