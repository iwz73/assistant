package idv.hsiehpinghan.ehcacheassistant.assistant;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Component
public class EhcacheAssistant {
	private final String CACHE_NAME = "result_catch";
	@Autowired
	private CacheManager cacheManager;

	public void put(Serializable key, Serializable value) {
		cacheManager.getCache(CACHE_NAME).put(new Element(key, value));
	}

	public Object get(Serializable key) {
		Element element = cacheManager.getCache(CACHE_NAME).get(key);
		if (element == null) {
			return null;
		}
		return element.getObjectValue();
	}
}
