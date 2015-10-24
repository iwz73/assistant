package idv.hsiehpinghan.goraassistant.service;

import idv.hsiehpinghan.goraassistant.repository.WebPageRepository;

import java.io.IOException;

import org.apache.gora.query.Result;
import org.apache.nutch.storage.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebPageService {
	@Autowired
	private WebPageRepository repository;

	public void put(String key, WebPage entity) {
		repository.put(key, entity);
	}

	public WebPage get(String key) {
		return repository.get(key);
	}

	public Result<String, WebPage> query(String key) {
		return repository.query(key);
	}

	public Result<String, WebPage> query(String startKey, long limit) {
		return repository.query(startKey, limit);
	}

	public Result<String, WebPage> query(String key, String... fields) {
		return repository.query(key, fields);
	}

	public boolean delete(String key) {
		return repository.delete(key);
	}

	public boolean exist(String key) throws IOException, Exception {
		return repository.exist(key);
	}
}
