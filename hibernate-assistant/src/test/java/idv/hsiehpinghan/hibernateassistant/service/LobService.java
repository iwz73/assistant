package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.LobEntity;
import idv.hsiehpinghan.hibernateassistant.repository.LobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LobService {
	@Autowired
	private LobRepository repository;

	public void save(LobEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public LobEntity findOne(int id) {
		LobEntity entity = repository.findOne(id);
		return entity;
	}

}
