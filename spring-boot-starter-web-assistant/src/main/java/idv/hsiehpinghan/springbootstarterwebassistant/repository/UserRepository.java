package idv.hsiehpinghan.springbootstarterwebassistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springbootstarterwebassistant.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
