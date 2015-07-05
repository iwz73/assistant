package idv.hsiehpinghan.hibernateassistant.service.implement;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEntity;
import idv.hsiehpinghan.hibernateassistant.repository.IElementCollectionRepository;
import idv.hsiehpinghan.hibernateassistant.service.IElementCollectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ElementCollectionService implements IElementCollectionService {
	@Autowired
	private IElementCollectionRepository repository;

	@Override
	public void save(ElementCollectionEntity entity) {
		repository.save(entity);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public ElementCollectionEntity findById(int id) {
		ElementCollectionEntity entity = repository.findById(id);
		entity.getElements().size();
		return entity;
	}
}
