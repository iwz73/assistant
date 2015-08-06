package idv.hsiehpinghan.springdatajpaassistatnt.repository;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ManyToManyBidirectionNoCascadeToEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IManyToManyBidirectionNoCascadeToRepository extends
		JpaRepository<ManyToManyBidirectionNoCascadeToEntity, Integer> {

}
