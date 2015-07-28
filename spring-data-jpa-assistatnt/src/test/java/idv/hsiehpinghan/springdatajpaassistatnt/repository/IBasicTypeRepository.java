package idv.hsiehpinghan.springdatajpaassistatnt.repository;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.BasicTypeEntity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBasicTypeRepository extends
		JpaRepository<BasicTypeEntity, Integer>, ICustomerizedRepository {
	@Query("select bte from BasicTypeEntity bte where bte.string = :string")
	List<BasicTypeEntity> findByString(@Param("string") String string);

	Page<BasicTypeEntity> findByPrimativeBoolean(boolean primativeBoolean,
			Pageable pageable);

}
