package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyBidirectionFromEntity {
	@Id
	private Integer id;

	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<ManyToManyBidirectionToEntity> tos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ManyToManyBidirectionToEntity> getTos() {
		return tos;
	}

	public void setTos(Collection<ManyToManyBidirectionToEntity> tos) {
		this.tos = tos;
	}

	public void addTo(ManyToManyBidirectionToEntity to) {
		if (this.tos == null) {
			this.tos = new ArrayList<ManyToManyBidirectionToEntity>();
		}
		this.tos.add(to);
	}
}
