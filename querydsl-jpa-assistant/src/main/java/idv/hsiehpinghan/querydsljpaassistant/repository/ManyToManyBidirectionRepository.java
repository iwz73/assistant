package idv.hsiehpinghan.querydsljpaassistant.repository;

import idv.hsiehpinghan.querydsljpaassistant.entity.ManyToManyBidirectionFromEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ManyToManyBidirectionRepository extends
		JpaRepository<ManyToManyBidirectionFromEntity, Integer>,
		QueryDslPredicateExecutor<ManyToManyBidirectionFromEntity> {

}
