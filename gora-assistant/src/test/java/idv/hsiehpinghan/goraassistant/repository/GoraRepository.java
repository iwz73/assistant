package idv.hsiehpinghan.goraassistant.repository;

import java.io.IOException;

import org.apache.gora.filter.Filter;
import org.apache.gora.query.Query;
import org.apache.gora.query.Result;
import org.apache.gora.store.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.goraassistant.entity.Gora;

@Repository
public class GoraRepository {
	@Autowired
	@Qualifier("goraDataStore")
	private DataStore<String, Gora> dataStore;

	public void put(String key, Gora entity) {
		dataStore.put(key, entity);
	}

	public Gora get(String key) {
		Gora entity = dataStore.get(key);
		return entity;
	}

	public Result<String, Gora> query(String key) {
		Query<String, Gora> query = dataStore.newQuery();
		query.setKey(key);
		return query.execute();
	}

	public Result<String, Gora> query(String startKey, long limit) {
		Query<String, Gora> query = dataStore.newQuery();
		query.setStartKey(startKey);
		query.setLimit(limit);
		return query.execute();
	}

	public Result<String, Gora> query(String key, String... fields) {
		Query<String, Gora> query = dataStore.newQuery();
		query.setKey(key);
		query.setFields(fields);
		return query.execute();
	}

	public Result<String, Gora> query(Filter<String, Gora> filter) {
		Query<String, Gora> query = dataStore.newQuery();
		query.setFilter(filter);
		return query.execute();
	}

	public boolean delete(String key) {
		boolean result = dataStore.delete(key);
		dataStore.flush();
		return result;
	}

	public boolean exist(String key) throws IOException, Exception {
		String[] fields = new String[] {};
		Result<String, Gora> result = query(key, fields);
		return result.next();
	}
}
