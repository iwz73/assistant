package idv.hsiehpinghan.springdatajpaassistatnt.repository;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.OneToManyBidirectionOneEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOneToManyBidirectionRepository extends
		JpaRepository<OneToManyBidirectionOneEntity, Integer> {

}
