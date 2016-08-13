package idv.hsiehpinghan.goraassistant.service;

import java.io.IOException;

import org.apache.gora.filter.Filter;
import org.apache.gora.query.Result;
import org.apache.gora.store.DataStore;
import org.apache.gora.util.GoraException;
import org.apache.nutch.storage.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.hsiehpinghan.goraassistant.repository.WebPageRepository;
import idv.hsiehpinghan.goraassistant.utility.ThreadLoalUtility;

@Service
public class WebPageService {
	// @Autowired
	// private Configuration configuration;
	@Autowired
	private WebPageRepository repository;

	public void put(String key, WebPage entity) throws GoraException {
		DataStore<String, WebPage> dataStore = ThreadLoalUtility.getWebPageDataStore();
		repository.put(dataStore, key, entity);
		dataStore.flush();
	}

	public WebPage get(String key) throws GoraException {
		DataStore<String, WebPage> dataStore = ThreadLoalUtility.getWebPageDataStore();
		WebPage result = repository.get(dataStore, key);
		dataStore.flush();
		return result;
	}

	public Result<String, WebPage> query(String key) throws GoraException {
		DataStore<String, WebPage> dataStore = ThreadLoalUtility.getWebPageDataStore();
		Result<String, WebPage> result = repository.query(dataStore, key);
		dataStore.flush();
		return result;
	}

	public Result<String, WebPage> query(String startKey, long limit) throws GoraException {
		DataStore<String, WebPage> dataStore = ThreadLoalUtility.getWebPageDataStore();
		Result<String, WebPage> result = repository.query(dataStore, startKey, limit);
		dataStore.flush();
		return result;
	}

	public Result<String, WebPage> query(String key, String... fields) throws GoraException {
		DataStore<String, WebPage> dataStore = ThreadLoalUtility.getWebPageDataStore();
		Result<String, WebPage> result = repository.query(dataStore, key, fields);
		dataStore.flush();
		return result;
	}

	public Result<String, WebPage> query(Filter<String, WebPage> filter, String... fields) throws GoraException {
		DataStore<String, WebPage> dataStore = ThreadLoalUtility.getWebPageDataStore();
		Result<String, WebPage> result = repository.query(dataStore, filter, fields);
		dataStore.flush();
		return result;
	}

	public boolean delete(String key) throws GoraException {
		DataStore<String, WebPage> dataStore = ThreadLoalUtility.getWebPageDataStore();
		boolean result = repository.delete(dataStore, key);
		dataStore.flush();
		return result;
	}

	public boolean exist(String key) throws IOException, Exception {
		DataStore<String, WebPage> dataStore = ThreadLoalUtility.getWebPageDataStore();
		boolean result = repository.exist(dataStore, key);
		dataStore.flush();
		return result;
	}

}
