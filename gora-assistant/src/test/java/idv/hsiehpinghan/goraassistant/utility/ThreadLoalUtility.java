package idv.hsiehpinghan.goraassistant.utility;

import org.apache.gora.store.DataStore;
import org.apache.gora.store.DataStoreFactory;
import org.apache.gora.util.GoraException;
import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.storage.WebPage;
import org.apache.nutch.util.NutchConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import idv.hsiehpinghan.goraassistant.entity.Gora;

public class ThreadLoalUtility {
	public static final Logger LOGGER = LoggerFactory.getLogger(ThreadLoalUtility.class);
	private static ThreadLocal<DataStore<String, Gora>> goraDataStoreThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<DataStore<String, WebPage>> webPageDataStoreThreadLocal = new ThreadLocal<>();

	public static DataStore<String, Gora> getGoraDataStore() {
		DataStore<String, Gora> goraDataStore = goraDataStoreThreadLocal.get();
		if (goraDataStore == null) {
			synchronized (goraDataStoreThreadLocal) {
				goraDataStore = goraDataStoreThreadLocal.get();
				if (goraDataStore == null) {
					try {
						Configuration configuration = NutchConfiguration.create();
						goraDataStore = DataStoreFactory.getDataStore(String.class, Gora.class, configuration);
						LOGGER.info(String.format("Thread(%s) create ThreadLocal(%s)", Thread.currentThread().getName(),
								Gora.class.getName()));
					} catch (GoraException e) {
						LOGGER.error("get data store fail !!!", e);
						return null;
					}
					goraDataStoreThreadLocal.set(goraDataStore);
				}
			}
		}
		return goraDataStore;
	}

	public static DataStore<String, WebPage> getWebPageDataStore() {
		DataStore<String, WebPage> webPageDataStore = webPageDataStoreThreadLocal.get();
		if (webPageDataStore == null) {
			synchronized (webPageDataStoreThreadLocal) {
				webPageDataStore = webPageDataStoreThreadLocal.get();
				if (webPageDataStore == null) {
					try {
						Configuration configuration = NutchConfiguration.create();
						webPageDataStore = DataStoreFactory.getDataStore(String.class, WebPage.class, configuration);
						LOGGER.info(String.format("Thread(%s) create ThreadLocal(%s)", Thread.currentThread().getName(),
								WebPage.class.getName()));
					} catch (GoraException e) {
						LOGGER.error("get data store fail !!!", e);
						return null;
					}
					webPageDataStoreThreadLocal.set(webPageDataStore);
				}
			}
		}
		return webPageDataStore;
	}
}
