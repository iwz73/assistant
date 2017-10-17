package idv.hsiehpinghan.springbootstarterwebassistant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springbootstarterwebassistant.entity.BasicTypeEntity;

public interface BasicTypeRepository extends JpaRepository<BasicTypeEntity, Integer> {
	List<BasicTypeEntity> findByString(String string);
}
