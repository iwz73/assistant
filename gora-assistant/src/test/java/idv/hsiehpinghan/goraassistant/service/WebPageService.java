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

/**
 * PLEASE REFERENCE GoraService for implement.
 * 
 * @author thank
 *
 */
@Service
public class WebPageService {
	@Autowired
	private DataStore<String, WebPage> webPageDataStore;
	@Autowired
	private WebPageRepository repository;

	public void put(String key, WebPage entity) throws GoraException {
		repository.put(webPageDataStore, key, entity);
	}

	public WebPage get(String key) throws GoraException {
		WebPage result = repository.get(webPageDataStore, key);
		return result;
	}

	public Result<String, WebPage> query(String key) throws GoraException {
		Result<String, WebPage> result = null;
		result = repository.query(webPageDataStore, key);
		return result;
	}

	public Result<String, WebPage> query(String startKey, long limit) throws GoraException {
		Result<String, WebPage> result = repository.query(webPageDataStore, startKey, limit);
		return result;
	}

	public Result<String, WebPage> query(String key, String... fields) throws GoraException {
		Result<String, WebPage> result = repository.query(webPageDataStore, key, fields);
		return result;
	}

	public Result<String, WebPage> query(Filter<String, WebPage> filter, String... fields) throws GoraException {
		Result<String, WebPage> result = repository.query(webPageDataStore, filter, fields);
		return result;
	}

	public boolean delete(String key) throws GoraException {
		boolean result = repository.delete(webPageDataStore, key);
		return result;
	}

	public boolean exist(String key) throws IOException, Exception {
		boolean result = repository.exist(webPageDataStore, key);
		return result;
	}

}
