package idv.hsiehpinghan.springdatajpaassistatnt.service;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ManyToManyBidirectionNoCascadeFromEntity;
import idv.hsiehpinghan.springdatajpaassistatnt.repository.IManyToManyBidirectionNoCascadeFromRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToManyBidirectionNoCascadeFromService {

	@Autowired
	private IManyToManyBidirectionNoCascadeFromRepository repository;

	public ManyToManyBidirectionNoCascadeFromEntity save(
			ManyToManyBidirectionNoCascadeFromEntity entity) {
		return repository.save(entity);
	}

	public ManyToManyBidirectionNoCascadeFromEntity findOne(Integer id) {
		ManyToManyBidirectionNoCascadeFromEntity entity = repository
				.findOne(id);
		entity.getTos().size();
		return entity;
	}
}
