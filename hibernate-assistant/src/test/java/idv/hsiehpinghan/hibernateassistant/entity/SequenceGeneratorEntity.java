package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class SequenceGeneratorEntity {
	@Id
	@SequenceGenerator(name = "sequenceGenerator", sequenceName = "SEQUENCE_GENERATOR_SEQUENCE_NAME")
	@GeneratedValue(generator = "sequenceGenerator")
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
