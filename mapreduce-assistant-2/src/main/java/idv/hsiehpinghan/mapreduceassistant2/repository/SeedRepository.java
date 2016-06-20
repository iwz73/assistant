package idv.hsiehpinghan.mapreduceassistant2.repository;

import java.io.IOException;

import org.apache.gora.query.Query;
import org.apache.gora.query.Result;
import org.apache.gora.store.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.nutch.storage.Seed;

@Repository
public class SeedRepository {
	@Autowired
	private DataStore<String, Seed> seedDataStore;

	public void put(String key, Seed entity) {
		seedDataStore.put(key, entity);
		seedDataStore.flush();
	}

	public Seed get(String key) {
		Seed entity = seedDataStore.get(key);
		return entity;
	}

	public Result<String, Seed> query(String key) {
		Query<String, Seed> query = seedDataStore.newQuery();
		query.setKey(key);
		return query.execute();
	}

	public Result<String, Seed> query(String startKey, long limit) {
		Query<String, Seed> query = seedDataStore.newQuery();
		query.setStartKey(startKey);
		query.setLimit(limit);
		return query.execute();
	}

	public Result<String, Seed> query(String key, String... fields) {
		Query<String, Seed> query = seedDataStore.newQuery();
		query.setKey(key);
		query.setFields(fields);
		return query.execute();
	}

	public boolean delete(String key) {
		boolean result = seedDataStore.delete(key);
		seedDataStore.flush();
		return result;
	}

	public boolean exist(String key) throws IOException, Exception {
		String[] fields = new String[] {};
		Result<String, Seed> result = query(key, fields);
		return result.next();
	}
}
