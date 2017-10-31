package idv.hsiehpinghan.springcloudstarterassistant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springcloudstarterassistant.entity.BasicEntity;
import idv.hsiehpinghan.springcloudstarterassistant.repository.BasicRepository;

@Service
@Transactional
public class BasicService {
	@Autowired
	private BasicRepository repository;

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<BasicEntity> findAll() {
		return repository.findAll();
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public BasicEntity findOne(Integer id) {
		return repository.findOne(id);
	}

	public void save(BasicEntity entity) {
		Integer id = entity.getId();
		BasicEntity oldEntity = repository.findOne(id);
		if (oldEntity != null) {
			throw new RuntimeException(String.format("oldEntity(%s) exists !!!", oldEntity));
		}
		repository.save(entity);
	}

	public void update(BasicEntity entity) {
		Integer id = entity.getId();
		BasicEntity oldEntity = repository.findOne(id);
		if (oldEntity == null) {
			throw new RuntimeException(String.format("entity(%s) not exists !!!", entity));
		}
		repository.save(entity);
	}

	public void delete(Integer id) {
		BasicEntity oldEntity = repository.findOne(id);
		if (oldEntity == null) {
			throw new RuntimeException(String.format("oldEntity(%s) not exists !!!", oldEntity));
		}
		repository.delete(id);
	}
}
