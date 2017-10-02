package idv.hsiehpinghan.springsecurityassistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.springsecurityassistant.entity.UserEntity;
import idv.hsiehpinghan.springsecurityassistant.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository repository;

	public void saveOrUpdate(UserEntity entity) {
		repository.saveOrUpdate(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserEntity findOne(String username) {
		return repository.findOne(username);
	}

}
