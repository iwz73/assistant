package idv.hsiehpinghan.springdatajpaassistatnt.repository.implement;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.BasicTypeEntity;
import idv.hsiehpinghan.springdatajpaassistatnt.repository.ICustomerizedRepository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

public class IBasicTypeRepositoryImpl implements ICustomerizedRepository {
	@Autowired
	private EntityManager entityManager;

	@Override
	public BasicTypeEntity findById(Integer id) {
		return entityManager.find(BasicTypeEntity.class, id);
	}

}
