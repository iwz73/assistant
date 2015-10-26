package idv.hsiehpinghan.goraassistant.repository;

import idv.hsiehpinghan.goraassistant.entity.Gora;

import java.io.IOException;

import org.apache.gora.query.Query;
import org.apache.gora.query.Result;
import org.apache.gora.store.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("hbase")
public class GoraRepository {
	@Autowired
	private DataStore<Long, Gora> dataStore;

	public void put(Long key, Gora entity) {
		dataStore.put(key, entity);
		dataStore.flush();
	}

	public Gora get(Long key) {
		Gora entity = dataStore.get(key);
		return entity;
	}

	public Result<Long, Gora> query(Long key) {
		Query<Long, Gora> query = dataStore.newQuery();
		query.setKey(key);
		return query.execute();
	}

	public Result<Long, Gora> query(Long startKey, long limit) {
		Query<Long, Gora> query = dataStore.newQuery();
		query.setStartKey(startKey);
		query.setLimit(limit);
		return query.execute();
	}

	public Result<Long, Gora> query(Long key, String... fields) {
		Query<Long, Gora> query = dataStore.newQuery();
		query.setKey(key);
		query.setFields(fields);
		return query.execute();
	}

	public boolean delete(Long key) {
		boolean result = dataStore.delete(key);
		dataStore.flush();
		return result;
	}

	public boolean exist(Long key) throws IOException, Exception {
		String[] fields = new String[] {};
		Result<Long, Gora> result = query(key, fields);
		return result.next();
	}
}
