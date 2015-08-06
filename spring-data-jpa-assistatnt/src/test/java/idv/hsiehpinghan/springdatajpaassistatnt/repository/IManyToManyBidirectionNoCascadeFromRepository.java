package idv.hsiehpinghan.springdatajpaassistatnt.repository;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ManyToManyBidirectionNoCascadeFromEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IManyToManyBidirectionNoCascadeFromRepository extends
		JpaRepository<ManyToManyBidirectionNoCascadeFromEntity, Integer> {

}
