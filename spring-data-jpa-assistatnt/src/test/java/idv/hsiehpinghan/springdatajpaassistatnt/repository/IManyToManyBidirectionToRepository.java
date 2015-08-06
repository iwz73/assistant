package idv.hsiehpinghan.springdatajpaassistatnt.repository;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ManyToManyBidirectionToEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IManyToManyBidirectionToRepository extends
		JpaRepository<ManyToManyBidirectionToEntity, Integer> {

}
