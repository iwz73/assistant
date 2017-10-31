package idv.hsiehpinghan.springcloudstarterassistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import idv.hsiehpinghan.springcloudstarterassistant.entity.BasicEntity;

public interface BasicRepository extends JpaRepository<BasicEntity, Integer> {
}
