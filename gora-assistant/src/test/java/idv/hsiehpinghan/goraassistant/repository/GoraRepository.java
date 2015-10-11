package idv.hsiehpinghan.goraassistant.repository;

import org.apache.gora.persistency.Persistent;
import org.apache.gora.query.Query;
import org.apache.gora.query.Result;
import org.apache.gora.store.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GoraRepository<K, T extends Persistent> {
	@Autowired
	private DataStore<K, T> dataStore;

	public void put(K key, T entity) {
		dataStore.put(key, entity);
		dataStore.flush();
	}

	public T get(K key) {
		T entity = dataStore.get(key);
		return entity;
	}

	public Result<K, T> query(K key) {
		Query<K, T> query = dataStore.newQuery();
		query.setKey(key);
		return query.execute();
	}

	public Result<K, T> query(K startKey, long limit) {
		Query<K, T> query = dataStore.newQuery();
		query.setStartKey(startKey);
		query.setLimit(limit);
		return query.execute();
	}

	public Result<K, T> query(K key, String... fields) {
		Query<K, T> query = dataStore.newQuery();
		query.setKey(key);
		query.setFields(fields);
		return query.execute();
	}

	public boolean delete(K key) {
		boolean result = dataStore.delete(key);
		dataStore.flush();
		return result;
	}

	public boolean exist(K key) {
		String[] fields = new String[] {};
		Result<K, T> result = query(key, fields);
		return result != null;
	}
}
