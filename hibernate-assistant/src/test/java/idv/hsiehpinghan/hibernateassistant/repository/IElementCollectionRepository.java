package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEntity;

public interface IElementCollectionRepository {
	void save(ElementCollectionEntity entity);

	ElementCollectionEntity findById(int id);
}
