package idv.hsiehpinghan.springbatchassistant.processor;

import org.springframework.batch.item.ItemProcessor;

import idv.hsiehpinghan.springbatchassistant.entity.HibernateEntity;

public class HibernateProcessor implements ItemProcessor<HibernateEntity, HibernateEntity> {

	@Override
	public HibernateEntity process(HibernateEntity entity) throws Exception {
		entity.setId(null);
		return entity;
	}

}