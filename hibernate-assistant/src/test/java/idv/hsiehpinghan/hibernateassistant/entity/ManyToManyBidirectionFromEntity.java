package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyBidirectionFromEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<ManyToManyBidirectionToEntity> to;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ManyToManyBidirectionToEntity> getTo() {
		return to;
	}

	public void setTo(Collection<ManyToManyBidirectionToEntity> to) {
		this.to = to;
	}

}
