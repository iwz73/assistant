package idv.hsiehpinghan.springcloudstarterassistant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import idv.hsiehpinghan.springcloudstarterassistant.criteria.BasicCreateCriteria;
import idv.hsiehpinghan.springcloudstarterassistant.criteria.BasicUpdateCriteria;
import idv.hsiehpinghan.springcloudstarterassistant.entity.BasicEntity;
import idv.hsiehpinghan.springcloudstarterassistant.service.BasicService;
import idv.hsiehpinghan.springcloudstarterassistant.utility.ConvertUtility;

@RestController
@RequestMapping("/basic")
public class BasicController {
	private final String NO_CONTENT = "no content";
	@Autowired
	private BasicService service;

	@PostMapping(value = "/create", consumes = "application/json")
	public String create(@RequestBody BasicCreateCriteria criteria) {
		BasicEntity entity = ConvertUtility.convertToBasicEntity(criteria);
		service.save(entity);
		return NO_CONTENT;
	}

	@GetMapping(value = "/read/{id}")
	public BasicEntity read(@PathVariable("id") Integer id) {
		BasicEntity entity = service.findOne(id);
		return entity;
	}

	@GetMapping(value = "/read")
	public List<BasicEntity> readAll() {
		List<BasicEntity> entities = service.findAll();
		return entities;
	}

	@PutMapping(value = "/update", consumes = "application/json")
	public String update(@RequestBody BasicUpdateCriteria criteria) {
		BasicEntity entity = ConvertUtility.convertToBasicEntity(criteria);
		service.update(entity);
		return NO_CONTENT;
	}

	@DeleteMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		service.delete(id);
		return NO_CONTENT;
	}

}
