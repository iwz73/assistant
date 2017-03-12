package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapKeyOneEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToManyMapKeyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToManyMapKeyService {
	@Autowired
	private OneToManyMapKeyRepository repository;

	public void save(OneToManyMapKeyOneEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public OneToManyMapKeyOneEntity findOne(int id) {
		OneToManyMapKeyOneEntity entity = repository.findOne(id);
		entity.getMany().size();
		return entity;
	}

}
