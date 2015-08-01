package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.BasicTypeEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QBasicTypeEntity;
import idv.hsiehpinghan.querydsljpaassistant.repository.BasicTypeRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicTypeService {
	private final int SIZE = 2;

	@Autowired
	private BasicTypeRepository repository;

	public BasicTypeEntity save(BasicTypeEntity entity) {
		return repository.save(entity);
	}

	public BasicTypeEntity findOne(Integer id) {
		return repository.findOne(id);
	}

	public BasicTypeEntity findOne1(Integer id) {
		QBasicTypeEntity qEntity = QBasicTypeEntity.basicTypeEntity;
		return repository.findOne(qEntity.id.eq(id));

	}

	public long countByString(String string) {
		QBasicTypeEntity qEntity = QBasicTypeEntity.basicTypeEntity;
		return repository.count(qEntity.string.eq(string));
	}

	public boolean exists(String string) {
		QBasicTypeEntity qEntity = QBasicTypeEntity.basicTypeEntity;
		return repository.exists(qEntity.string.eq(string));
	}

	public List<BasicTypeEntity> findAll() {
		return repository.findAll();
	}

	public Iterable<BasicTypeEntity> findAllDescentById(String string) {
		QBasicTypeEntity qEntity = QBasicTypeEntity.basicTypeEntity;
		QSort sort = new QSort(qEntity.id.desc());
		return repository.findAll(qEntity.string.eq(string), sort);
	}

	public Page<BasicTypeEntity> findAllWithPage(String string, int page) {
		QBasicTypeEntity qEntity = QBasicTypeEntity.basicTypeEntity;
		Pageable pageable = new PageRequest(page, SIZE);
		return repository.findAll(qEntity.string.eq(string), pageable);
	}

	public void delete(Integer id) {
		repository.delete(id);
	}

}
