package idv.hsiehpinghan.goraassistant.repository;

import idv.hsiehpinghan.goraassistant.entity.Gora;

import org.apache.gora.store.DataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GoraRepository {
	@Autowired
	private DataStore<Long, Gora> dataStore;

	public void put(Long key, Gora entity) {
		dataStore.put(key, entity);
		dataStore.flush();
	}

	public Gora get(Long key) {
		return dataStore.get(key);
	}
}
