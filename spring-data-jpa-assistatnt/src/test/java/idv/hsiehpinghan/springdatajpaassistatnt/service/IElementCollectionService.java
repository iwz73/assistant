package idv.hsiehpinghan.springdatajpaassistatnt.service;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ElementCollectionEntity;

public interface IElementCollectionService {
	ElementCollectionEntity save(ElementCollectionEntity entity);

	ElementCollectionEntity findOne(Integer id);
}
