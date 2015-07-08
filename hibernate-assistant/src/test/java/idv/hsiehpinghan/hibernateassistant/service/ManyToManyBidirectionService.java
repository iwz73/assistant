package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.repository.ManyToManyBidirectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToManyBidirectionService {
	@Autowired
	private ManyToManyBidirectionRepository repository;

	public void save(ManyToManyBidirectionFromEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ManyToManyBidirectionFromEntity findOne(int id) {
		ManyToManyBidirectionFromEntity entity = repository.findOne(id);
		return entity;
	}

}
