package idv.hsiehpinghan.goraassistant.repository;

import java.io.IOException;

import org.apache.gora.filter.Filter;
import org.apache.gora.query.Query;
import org.apache.gora.query.Result;
import org.apache.gora.store.DataStore;
import org.apache.nutch.storage.WebPage;
import org.springframework.stereotype.Repository;

@Repository
public class WebPageRepository {

	public void put(DataStore<String, WebPage> dataStore, String key, WebPage entity) {
		dataStore.put(key, entity);
		dataStore.flush();
	}

	public WebPage get(DataStore<String, WebPage> dataStore, String key) {
		WebPage entity = dataStore.get(key);
		return entity;
	}

	public Result<String, WebPage> query(DataStore<String, WebPage> dataStore, String key) {
		Query<String, WebPage> query = dataStore.newQuery();
		query.setKey(key);
		return query.execute();
	}

	public Result<String, WebPage> query(DataStore<String, WebPage> dataStore, String startKey, long limit) {
		Query<String, WebPage> query = dataStore.newQuery();
		query.setStartKey(startKey);
		query.setLimit(limit);
		return query.execute();
	}

	public Result<String, WebPage> query(DataStore<String, WebPage> dataStore, String key, String... fields) {
		Query<String, WebPage> query = dataStore.newQuery();
		query.setKey(key);
		query.setFields(fields);
		return query.execute();
	}

	public Result<String, WebPage> query(DataStore<String, WebPage> dataStore, Filter<String, WebPage> filter,
			String... fields) {
		Query<String, WebPage> query = dataStore.newQuery();
		query.setFilter(filter);
		query.setFields(fields);
		return query.execute();
	}

	public boolean delete(DataStore<String, WebPage> dataStore, String key) {
		boolean result = dataStore.delete(key);
		dataStore.flush();
		return result;
	}

	public boolean exist(DataStore<String, WebPage> dataStore, String key) throws IOException, Exception {
		String[] fields = new String[] {};
		Result<String, WebPage> result = query(dataStore, key, fields);
		return result.next();
	}
}
