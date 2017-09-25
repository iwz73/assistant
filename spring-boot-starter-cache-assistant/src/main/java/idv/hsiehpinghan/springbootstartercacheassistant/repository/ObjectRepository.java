package idv.hsiehpinghan.springbootstartercacheassistant.repository;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "object_cache")
public interface ObjectRepository {
	@Cacheable
	String getString(String string);

	int getNewStringCount();
}
