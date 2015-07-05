package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEntity;

public interface IElementCollectionService {
	void save(ElementCollectionEntity entity);

	ElementCollectionEntity findById(int id);
}
