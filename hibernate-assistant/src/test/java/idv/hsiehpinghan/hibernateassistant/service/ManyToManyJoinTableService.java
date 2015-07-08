package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyJoinTableFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyJoinTableToEntity;
import idv.hsiehpinghan.hibernateassistant.repository.ManyToManyJoinTableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ManyToManyJoinTableService {
	@Autowired
	private ManyToManyJoinTableRepository repository;

	public void saveOrUpdate(ManyToManyJoinTableFromEntity entity) {
		repository.saveOrUpdate(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ManyToManyJoinTableFromEntity findOne(int id) {
		ManyToManyJoinTableFromEntity entity = repository.findOne(id);
		entity.getTos().size();
		for (ManyToManyJoinTableToEntity to : entity.getTos()) {
			to.getFroms().size();
		}
		return entity;
	}

}
