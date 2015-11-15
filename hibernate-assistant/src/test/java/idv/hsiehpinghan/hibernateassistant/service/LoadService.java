package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.LoadManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.LoadOneEntity;
import idv.hsiehpinghan.hibernateassistant.repository.LoadRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoadService {
	@Autowired
	private LoadRepository repository;

	public Long save(LoadOneEntity entity) {
		return repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public Collection<LoadManyEntity> findLoadManyEntities(long id) {
		LoadOneEntity entity = repository.load(id);
		entity.getMany().size();
		return entity.getMany();
	}

}
