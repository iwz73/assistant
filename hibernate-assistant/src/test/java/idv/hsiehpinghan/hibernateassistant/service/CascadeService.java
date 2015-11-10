package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.CascadeEntity;
import idv.hsiehpinghan.hibernateassistant.repository.CascadeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CascadeService {
	@Autowired
	private CascadeRepository repository;

	public void persist(CascadeEntity entity) {
		repository.persist(entity);
	}

	public void merge(CascadeEntity entity) {
		repository.merge(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public CascadeEntity get(long id) {
		CascadeEntity entity = repository.get(id);
		entity.getCascadePersistEntities().size();
		entity.getCascadeMergeEntities().size();
		return entity;
	}

}
