package idv.hsiehpinghan.springbatchassistant.processor;

import org.springframework.batch.item.ItemProcessor;

import idv.hsiehpinghan.springbatchassistant.entity.HibernateEntity;

public class HibernateTransactionProcessor implements ItemProcessor<HibernateEntity, HibernateEntity> {

	@Override
	public HibernateEntity process(HibernateEntity entity) throws Exception {
		int id = entity.getId();
		if (id == 5) {
			throw new RuntimeException("id == 5 !!!");
		}
		if (id < 0) {
			return null;
		}
		entity.setId(entity.getId() - 100);
		return entity;
	}

}