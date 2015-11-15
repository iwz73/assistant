package idv.hsiehpinghan.springdatajpaassistatnt.repository;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.BasicTypeEntity;

public interface ICustomerizedRepository {
	BasicTypeEntity findById(Integer id);
}
