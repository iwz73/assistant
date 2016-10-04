package idv.hsiehpinghan.ehcacheassistant.configuration;

import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.CacheManager;

@Configuration
@ComponentScan(basePackages = { "idv.hsiehpinghan.ehcacheassistant" })
public class SpringConfiguration {

	@Bean
	public CacheManager cacheManager() {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("ehcache.xml");
		return CacheManager.create(inputStream);
	}
}