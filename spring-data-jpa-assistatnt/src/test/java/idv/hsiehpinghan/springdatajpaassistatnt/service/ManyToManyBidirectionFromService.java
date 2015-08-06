package idv.hsiehpinghan.springdatajpaassistatnt.service;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.springdatajpaassistatnt.repository.IManyToManyBidirectionFromRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToManyBidirectionFromService {

	@Autowired
	private IManyToManyBidirectionFromRepository repository;

	public ManyToManyBidirectionFromEntity save(
			ManyToManyBidirectionFromEntity entity) {
		return repository.save(entity);
	}

	public ManyToManyBidirectionFromEntity findOne(Integer id) {
		ManyToManyBidirectionFromEntity entity = repository.findOne(id);
		entity.getTos().size();
		return entity;
	}

}
