package idv.hsiehpinghan.springbootstartercacheassistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheManagerService {
	private final String CACHE_NAME = "cache_manager_cache";
	@Autowired
	private CacheManager cacheManager;

	public void put(String key, String value) {
		cacheManager.getCache(CACHE_NAME).put(key, value);
	}

	public String get(String key) {
		return cacheManager.getCache(CACHE_NAME).get(key, String.class);
	}

}
