package idv.hsiehpinghan.springdatajpaassistatnt.service;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ManyToManyBidirectionNoCascadeToEntity;
import idv.hsiehpinghan.springdatajpaassistatnt.repository.IManyToManyBidirectionNoCascadeToRepository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToManyBidirectionNoCascadeToService {

	@Autowired
	private IManyToManyBidirectionNoCascadeToRepository repository;

	public ManyToManyBidirectionNoCascadeToEntity save(
			ManyToManyBidirectionNoCascadeToEntity entity) {
		return repository.save(entity);
	}

	public List<ManyToManyBidirectionNoCascadeToEntity> save(
			Collection<ManyToManyBidirectionNoCascadeToEntity> entities) {
		return repository.save(entities);
	}
}
