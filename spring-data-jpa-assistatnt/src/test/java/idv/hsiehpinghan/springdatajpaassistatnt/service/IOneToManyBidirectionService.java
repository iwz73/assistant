package idv.hsiehpinghan.springdatajpaassistatnt.service;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.OneToManyBidirectionOneEntity;

public interface IOneToManyBidirectionService {
	OneToManyBidirectionOneEntity save(OneToManyBidirectionOneEntity entity);

	OneToManyBidirectionOneEntity findOne(Integer id);
}
