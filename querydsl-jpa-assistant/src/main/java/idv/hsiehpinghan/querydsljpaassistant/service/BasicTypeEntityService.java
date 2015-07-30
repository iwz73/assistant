package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.querydsljpaassistant.repository.BasicTypeEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicTypeEntityService {
	@Autowired
	private BasicTypeEntityRepository repository;

	public BasicTypeEntity save(BasicTypeEntity entity) {
		return repository.save(entity);
	}

	public BasicTypeEntity findOne(Integer id) {
		return repository.findOne(id);
	}

	public void delete(Integer id) {
		repository.delete(id);
	}
}
