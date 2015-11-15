package idv.hsiehpinghan.springdatajpaassistatnt.entity;

import java.util.ArrayList;
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
	private String name;

	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<ManyToManyBidirectionToEntity> tos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManyToManyBidirectionFromEntity other = (ManyToManyBidirectionFromEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
