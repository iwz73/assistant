package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyBidirectionOneEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToManyBidirectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToManyBidirectionService {
	@Autowired
	private OneToManyBidirectionRepository repository;

	public void save(OneToManyBidirectionOneEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public OneToManyBidirectionOneEntity findOne(int id) {
		OneToManyBidirectionOneEntity entity = repository.findOne(id);
		entity.getMany().size();
		return entity;
	}

}
