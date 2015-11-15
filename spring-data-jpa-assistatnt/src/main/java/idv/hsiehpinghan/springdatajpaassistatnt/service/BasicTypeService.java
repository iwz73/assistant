package idv.hsiehpinghan.springdatajpaassistatnt.service;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.BasicTypeEntity;
import idv.hsiehpinghan.springdatajpaassistatnt.repository.IBasicTypeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicTypeService {
	private final int PAGE_SIZE = 5;

	@Autowired
	private IBasicTypeRepository repository;

	public BasicTypeEntity save(BasicTypeEntity entity) {
		return repository.save(entity);
	}

	public List<BasicTypeEntity> findByString(String string) {
		return repository.findByString(string);
	}

	public Page<BasicTypeEntity> findByPrimativeBoolean(
			boolean primativeBoolean, int page) {
		Pageable pageable = new PageRequest(page, PAGE_SIZE);
		return repository.findByPrimativeBoolean(primativeBoolean, pageable);
	}

	public BasicTypeEntity findById(Integer id) {
		return repository.findById(id);
	}
}
