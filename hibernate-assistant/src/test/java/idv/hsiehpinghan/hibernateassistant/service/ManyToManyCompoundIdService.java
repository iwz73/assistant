package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyCompoundIdFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyCompoundIdFromIdEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyCompoundIdToEntity;
import idv.hsiehpinghan.hibernateassistant.repository.ManyToManyCompoundIdRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToManyCompoundIdService {
	@Autowired
	private ManyToManyCompoundIdRepository repository;

	public void saveOrUpdate(ManyToManyCompoundIdFromEntity entity) {
		repository.saveOrUpdate(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ManyToManyCompoundIdFromEntity findOne(
			ManyToManyCompoundIdFromIdEntity id) {
		ManyToManyCompoundIdFromEntity entity = repository.findOne(id);
		entity.getTos().size();
		for (ManyToManyCompoundIdToEntity to : entity.getTos()) {
			to.getFroms().size();
		}
		return entity;
	}

}
