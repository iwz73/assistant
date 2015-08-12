package idv.hsiehpinghan.goraassistant.service;

import idv.hsiehpinghan.goraassistant.entity.Gora;
import idv.hsiehpinghan.goraassistant.repository.GoraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoraService {
	@Autowired
	private GoraRepository repository;

	public void put(Long key, Gora entity) {
		repository.put(key, entity);
	}
}
