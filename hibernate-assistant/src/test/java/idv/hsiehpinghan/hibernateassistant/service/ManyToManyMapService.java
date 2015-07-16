package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapToEntity;
import idv.hsiehpinghan.hibernateassistant.repository.ManyToManyMapRepository;

import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToManyMapService {
	@Autowired
	private ManyToManyMapRepository repository;

	public void saveOrUpdate(ManyToManyMapFromEntity entity) {
		repository.saveOrUpdate(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ManyToManyMapFromEntity findOne(int id) {
		ManyToManyMapFromEntity entity = repository.findOne(id);
		entity.getTos().size();
		for (Entry<String, ManyToManyMapToEntity> entry : entity.getTos()
				.entrySet()) {
			entry.getValue().getFroms().size();
		}
		return entity;
	}

}
