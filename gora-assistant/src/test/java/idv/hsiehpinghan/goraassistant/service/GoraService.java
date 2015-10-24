package idv.hsiehpinghan.goraassistant.service;

import idv.hsiehpinghan.goraassistant.entity.Gora;
import idv.hsiehpinghan.goraassistant.repository.GoraRepository;

import java.io.IOException;

import org.apache.gora.query.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoraService {
	@Autowired
	private GoraRepository repository;

	public void put(Long key, Gora entity) {
		repository.put(key, entity);
	}

	public Gora get(Long key) {
		return repository.get(key);
	}

	public Result<Long, Gora> query(Long key) {
		return repository.query(key);
	}

	public Result<Long, Gora> query(Long startKey, long limit) {
		return repository.query(startKey, limit);
	}

	public Result<Long, Gora> query(Long key, String... fields) {
		return repository.query(key, fields);
	}

	public boolean delete(Long key) {
		return repository.delete(key);
	}

	public boolean exist(Long key) throws IOException, Exception {
		return repository.exist(key);
	}
}
