package idv.hsiehpinghan.springdatajpaassistatnt.service;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ManyToManyBidirectionToEntity;
import idv.hsiehpinghan.springdatajpaassistatnt.repository.IManyToManyBidirectionToRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToManyBidirectionToService {

	@Autowired
	private IManyToManyBidirectionToRepository repository;

	public ManyToManyBidirectionToEntity save(
			ManyToManyBidirectionToEntity entity) {
		return repository.save(entity);
	}

}
