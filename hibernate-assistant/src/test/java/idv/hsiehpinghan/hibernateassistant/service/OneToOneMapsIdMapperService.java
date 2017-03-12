package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOneMapsIdMapperEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToOneMapsIdMapperRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToOneMapsIdMapperService {
	@Autowired
	private OneToOneMapsIdMapperRepository repository;

	public void save(OneToOneMapsIdMapperEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public OneToOneMapsIdMapperEntity findOne(int i) {
		OneToOneMapsIdMapperEntity entity = repository.findOne(i);
		return entity;
	}
}
