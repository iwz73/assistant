package idv.hsiehpinghan.querydsljpaassistant.repository;

import idv.hsiehpinghan.querydsljpaassistant.entity.BasicTypeEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicTypeRepository extends
		JpaRepository<BasicTypeEntity, Integer>,
		QueryDslPredicateExecutor<BasicTypeEntity> {

}
