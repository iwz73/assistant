package idv.hsiehpinghan.springdatajpaassistatnt.repository;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ElementCollectionEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IElementCollectionRepository extends
		JpaRepository<ElementCollectionEntity, Integer> {

}
