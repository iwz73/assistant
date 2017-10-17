package idv.hsiehpinghan.springbootstarterwebassistant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springbootstarterwebassistant.entity.BasicEntity;

public interface BasicRepository extends JpaRepository<BasicEntity, Integer> {
	List<BasicEntity> findByString(String string);
}
