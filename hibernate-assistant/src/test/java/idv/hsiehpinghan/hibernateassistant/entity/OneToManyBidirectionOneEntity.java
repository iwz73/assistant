package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OneToManyBidirectionOneEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "one", cascade = CascadeType.ALL)
	private Collection<OneToManyBidirectionManyEntity> many;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<OneToManyBidirectionManyEntity> getMany() {
		return many;
	}

	public void setMany(Collection<OneToManyBidirectionManyEntity> many) {
		this.many = many;
	}

}
