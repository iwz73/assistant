package idv.hsiehpinghan.hibernatesearchormassistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.BridgeEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.repository.BridgeEntityRepository;

@Service
@Transactional
public class BridgeEntityService {
	@Autowired
	private BridgeEntityRepository repository;

	public void save(BridgeEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BridgeEntity findOne(BridgeEntity.BridgeEntityId id) {
		BridgeEntity entity = repository.findOne(id);
		return entity;
	}

}
