package idv.hsiehpinghan.springcloudstarterassistant.utility;

import idv.hsiehpinghan.springcloudstarterassistant.criteria.BasicCriteria;
import idv.hsiehpinghan.springcloudstarterassistant.entity.BasicEntity;

public class ConvertUtility {
	public static BasicEntity convertToBasicEntity(BasicCriteria criteria) {
		Integer id = criteria.getId();
		String string = criteria.getString();
		BasicEntity entity = new BasicEntity(id, string);
		return entity;
	}
}
