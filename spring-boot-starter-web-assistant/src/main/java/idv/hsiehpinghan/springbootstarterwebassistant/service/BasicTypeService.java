package idv.hsiehpinghan.springbootstarterwebassistant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.hsiehpinghan.springbootstarterwebassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.springbootstarterwebassistant.repository.BasicTypeRepository;

@Service
public class BasicTypeService {
	@Autowired
	private BasicTypeRepository repository;

	public void save(BasicTypeEntity entity) {
		repository.save(entity);
	}

	public List<BasicTypeEntity> findByString(String string) {
		return repository.findByString(string);
	}
}
