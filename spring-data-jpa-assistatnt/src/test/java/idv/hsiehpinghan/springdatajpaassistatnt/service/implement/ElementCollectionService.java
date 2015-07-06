package idv.hsiehpinghan.springdatajpaassistatnt.service.implement;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ElementCollectionEntity;
import idv.hsiehpinghan.springdatajpaassistatnt.repository.IElementCollectionRepository;
import idv.hsiehpinghan.springdatajpaassistatnt.service.IElementCollectionService;

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
	public ElementCollectionEntity save(ElementCollectionEntity entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public ElementCollectionEntity findOne(Integer id) {
		return repository.findOne(id);
	}
}
