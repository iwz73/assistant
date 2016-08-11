package idv.hsiehpinghan.goraassistant.service;

import java.io.IOException;

import org.apache.gora.query.Result;
import org.apache.gora.store.DataStore;
import org.apache.gora.store.DataStoreFactory;
import org.apache.gora.util.GoraException;
import org.apache.hadoop.conf.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.hsiehpinghan.goraassistant.entity.Gora;
import idv.hsiehpinghan.goraassistant.repository.GoraRepository;

@Service
public class GoraService {
	@Autowired
	private Configuration configuration;
	@Autowired
	private GoraRepository repository;

	public void put(String key, Gora entity) throws GoraException {
		DataStore<String, Gora> dataStore = getDataStore();
		repository.put(dataStore, key, entity);
		dataStore.flush();
	}

	public Gora get(String key) throws GoraException {
		DataStore<String, Gora> dataStore = getDataStore();
		Gora result = repository.get(dataStore, key);
		dataStore.flush();
		return result;
	}

	public Result<String, Gora> query(String key) throws GoraException {
		DataStore<String, Gora> dataStore = getDataStore();
		Result<String, Gora> result = repository.query(dataStore, key);
		dataStore.flush();
		return result;
	}

	public Result<String, Gora> query(String startKey, long limit) throws GoraException {
		DataStore<String, Gora> dataStore = getDataStore();
		Result<String, Gora> result = repository.query(dataStore, startKey, limit);
		dataStore.flush();
		return result;
	}

	public Result<String, Gora> query(String key, String... fields) throws GoraException {
		DataStore<String, Gora> dataStore = getDataStore();
		Result<String, Gora> result = repository.query(dataStore, key, fields);
		dataStore.flush();
		return result;
	}

	public boolean delete(String key) throws GoraException {
		DataStore<String, Gora> dataStore = getDataStore();
		boolean result = repository.delete(dataStore, key);
		dataStore.flush();
		return result;
	}

	public boolean exist(String key) throws GoraException, IOException, Exception {
		DataStore<String, Gora> dataStore = getDataStore();
		boolean result = repository.exist(dataStore, key);
		dataStore.flush();
		return result;
	}

	private DataStore<String, Gora> getDataStore() throws GoraException {
		return DataStoreFactory.getDataStore(String.class, Gora.class, configuration);
	}
}
