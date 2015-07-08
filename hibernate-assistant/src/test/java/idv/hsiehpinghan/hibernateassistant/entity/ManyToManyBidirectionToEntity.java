package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyBidirectionToEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToMany(mappedBy = "to")
	private Collection<ManyToManyBidirectionFromEntity> froms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ManyToManyBidirectionFromEntity> getFroms() {
		return froms;
	}

	public void setFroms(Collection<ManyToManyBidirectionFromEntity> froms) {
		this.froms = froms;
	}

}
