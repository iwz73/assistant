package idv.hsiehpinghan.springbootstartercacheassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringBootStarterCacheAssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterCacheAssistantApplication.class, args);
	}
}
