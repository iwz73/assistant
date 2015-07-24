package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.TableGenerator;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TablePerClassInheritance_0_Entity {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tableGenerator")
	@TableGenerator(name = "tableGenerator", table = "TABLE_PER_CLASS_INHERITANCE_TABLE_GENERATOR", pkColumnName = "PK_COLUMN_NAME", valueColumnName = "VALUE_COLUMN_NAME")
	private int id;
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
