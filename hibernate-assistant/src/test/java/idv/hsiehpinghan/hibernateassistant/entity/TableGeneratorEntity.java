package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class TableGeneratorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGenerator")
	@TableGenerator(name = "tableGenerator", table = "TABLE_GENERATOR_TABLE", pkColumnName = "TABLE_GENERATOR_PK_COLUMN_NAME", valueColumnName = "TABLE_GENERATOR_VALUE_COLUMN_NAME", pkColumnValue = "TABLE_GENERATOR_PK_COLUMN_VALUE", initialValue = 100, allocationSize = 10)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
