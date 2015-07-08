package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class OneToManyUnidirectionOneEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "OneToManyUnidirectionOneEntity_OneToManyUnidirectionManyEntity", joinColumns = @JoinColumn(name = "OneToManyUnidirectionOneEntityId"), inverseJoinColumns = @JoinColumn(name = "OneToManyUnidirectionManyEntityId"))
	private Collection<OneToManyUnidirectionManyEntity> many;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<OneToManyUnidirectionManyEntity> getMany() {
		return many;
	}

	public void setMany(Collection<OneToManyUnidirectionManyEntity> many) {
		this.many = many;
	}

}
