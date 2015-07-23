package idv.hsiehpinghan.springdatajpaassistatnt.service.implement;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.OneToManyBidirectionOneEntity;
import idv.hsiehpinghan.springdatajpaassistatnt.repository.IOneToManyBidirectionRepository;
import idv.hsiehpinghan.springdatajpaassistatnt.service.IOneToManyBidirectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToManyBidirectionService implements
		IOneToManyBidirectionService {
	@Autowired
	private IOneToManyBidirectionRepository repository;

	@Override
	public OneToManyBidirectionOneEntity save(
			OneToManyBidirectionOneEntity entity) {
		return repository.saveAndFlush(entity);
//		return repository.save(entity);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public OneToManyBidirectionOneEntity findOne(Integer id) {
		OneToManyBidirectionOneEntity entity = repository.findOne(id);
		entity.getMany_1().size();
		entity.getMany_2().size();
		return entity;
	}

}
