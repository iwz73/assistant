package idv.hsiehpinghan.springbootstarterwebassistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springbootstarterwebassistant.entity.UserEntity;
import idv.hsiehpinghan.springbootstarterwebassistant.repository.impl.UserRepositoryImpl;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepositoryImpl repository;

	public void saveOrUpdate(UserEntity entity) {
		repository.saveOrUpdate(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserEntity findOne(String username) {
		return repository.findOne(username);
	}

}
