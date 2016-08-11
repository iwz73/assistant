package idv.hsiehpinghan.goraassistant.repository;

import java.io.IOException;

import org.apache.gora.query.Query;
import org.apache.gora.query.Result;
import org.apache.gora.store.DataStore;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.goraassistant.entity.Gora;

@Repository
public class GoraRepository {
	public void put(DataStore<String, Gora> dataStore, String key, Gora entity) {
		dataStore.put(key, entity);
	}

	public Gora get(DataStore<String, Gora> dataStore, String key) {
		Gora entity = dataStore.get(key);
		return entity;
	}

	public Result<String, Gora> query(DataStore<String, Gora> dataStore, String key) {
		Query<String, Gora> query = dataStore.newQuery();
		query.setKey(key);
		return query.execute();
	}

	public Result<String, Gora> query(DataStore<String, Gora> dataStore, String startKey, long limit) {
		Query<String, Gora> query = dataStore.newQuery();
		query.setStartKey(startKey);
		query.setLimit(limit);
		return query.execute();
	}

	public Result<String, Gora> query(DataStore<String, Gora> dataStore, String key, String... fields) {
		Query<String, Gora> query = dataStore.newQuery();
		query.setKey(key);
		query.setFields(fields);
		return query.execute();
	}

	public boolean delete(DataStore<String, Gora> dataStore, String key) {
		boolean result = dataStore.delete(key);
		dataStore.flush();
		return result;
	}

	public boolean exist(DataStore<String, Gora> dataStore, String key) throws IOException, Exception {
		String[] fields = new String[] {};
		Result<String, Gora> result = query(dataStore, key, fields);
		return result.next();
	}
}
