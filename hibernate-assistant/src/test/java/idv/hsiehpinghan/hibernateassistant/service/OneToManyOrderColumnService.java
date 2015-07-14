package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.OneToManyOrderColumnOneEntity;
import idv.hsiehpinghan.hibernateassistant.repository.OneToManyOrderColumnRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OneToManyOrderColumnService {
	@Autowired
	private OneToManyOrderColumnRepository repository;

	public void save(OneToManyOrderColumnOneEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public OneToManyOrderColumnOneEntity findOne(int id) {
		OneToManyOrderColumnOneEntity entity = repository.findOne(id);
		entity.getMany().size();
		return entity;
	}

}
