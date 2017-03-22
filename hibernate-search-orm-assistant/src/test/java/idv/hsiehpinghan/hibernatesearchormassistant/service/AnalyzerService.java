package idv.hsiehpinghan.hibernatesearchormassistant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.AnalyzerEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.repository.AnalyzerRepository;

@Service
@Transactional
public class AnalyzerService {
	@Autowired
	private AnalyzerRepository repository;

	public void save(AnalyzerEntity entity) {
		repository.save(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public AnalyzerEntity findOne(int id) {
		return repository.findOne(id);
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<AnalyzerEntity> luceneQuery(org.apache.lucene.search.Query query) {
		return repository.luceneQuery(query);
	}
}
