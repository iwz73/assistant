package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapEmbeddableFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapEmbeddableToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapEmbeddableValueObject;
import idv.hsiehpinghan.hibernateassistant.repository.ManyToManyMapEmbeddableRepository;

import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToManyMapEmbeddableService {
	@Autowired
	private ManyToManyMapEmbeddableRepository repository;

	public void save(ManyToManyMapEmbeddableFromEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ManyToManyMapEmbeddableFromEntity findOne(int id) {
		ManyToManyMapEmbeddableFromEntity entity = repository.findOne(id);
		entity.getTos().size();
		for (Entry<ManyToManyMapEmbeddableValueObject, ManyToManyMapEmbeddableToEntity> to : entity
				.getTos().entrySet()) {
			to.getValue().getFroms().size();
		}
		return entity;
	}

}
