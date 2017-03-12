package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyBidirectionToEntity;
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

	public void saveOrUpdate(ManyToManyBidirectionFromEntity entity) {
		repository.saveOrUpdate(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ManyToManyBidirectionFromEntity findOne(int id) {
		ManyToManyBidirectionFromEntity entity = repository.findOne(id);
		entity.getTos().size();
		for (ManyToManyBidirectionToEntity to : entity.getTos()) {
			to.getFroms().size();
		}
		return entity;
	}

}
