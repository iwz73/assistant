package idv.hsiehpinghan.goraassistant.repository;

import java.io.IOException;

import org.apache.gora.filter.Filter;
import org.apache.gora.query.Query;
import org.apache.gora.query.Result;
import org.apache.gora.store.DataStore;
import org.apache.nutch.storage.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WebPageRepository {
	@Autowired
	private DataStore<String, WebPage> dataStore;

	public void put(String key, WebPage entity) {
		dataStore.put(key, entity);
		dataStore.flush();
	}

	public WebPage get(String key) {
		WebPage entity = dataStore.get(key);
		return entity;
	}

	public Result<String, WebPage> query(String key) {
		Query<String, WebPage> query = dataStore.newQuery();
		query.setKey(key);
		return query.execute();
	}

	public Result<String, WebPage> query(String startKey, long limit) {
		Query<String, WebPage> query = dataStore.newQuery();
		query.setStartKey(startKey);
		query.setLimit(limit);
		return query.execute();
	}

	public Result<String, WebPage> query(String key, String... fields) {
		Query<String, WebPage> query = dataStore.newQuery();
		query.setKey(key);
		query.setFields(fields);
		return query.execute();
	}

	public Result<String, WebPage> query(Filter<String, WebPage> filter, String... fields) {
		Query<String, WebPage> query = dataStore.newQuery();
		query.setFilter(filter);
		query.setFields(fields);
		return query.execute();
	}

	public boolean delete(String key) {
		boolean result = dataStore.delete(key);
		dataStore.flush();
		return result;
	}

	public boolean exist(String key) throws IOException, Exception {
		String[] fields = new String[] {};
		Result<String, WebPage> result = query(key, fields);
		return result.next();
	}
}
