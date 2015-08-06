package idv.hsiehpinghan.springdatajpaassistatnt.repository;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ManyToManyBidirectionFromEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IManyToManyBidirectionFromRepository extends
		JpaRepository<ManyToManyBidirectionFromEntity, Integer> {

}
