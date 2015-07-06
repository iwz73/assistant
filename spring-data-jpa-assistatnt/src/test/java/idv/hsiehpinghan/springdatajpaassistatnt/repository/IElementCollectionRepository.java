package idv.hsiehpinghan.springdatajpaassistatnt.repository;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ElementCollectionEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IElementCollectionRepository extends
		JpaRepository<ElementCollectionEntity, Integer> {

}
