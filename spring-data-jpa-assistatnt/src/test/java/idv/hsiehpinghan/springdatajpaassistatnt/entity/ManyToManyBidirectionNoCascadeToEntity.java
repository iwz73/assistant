package idv.hsiehpinghan.springdatajpaassistatnt.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyBidirectionNoCascadeToEntity {
	@Id
	private Long id;
	private String name;
	@ManyToMany(mappedBy = "tos")
	private Collection<ManyToManyBidirectionNoCascadeFromEntity> froms;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<ManyToManyBidirectionNoCascadeFromEntity> getFroms() {
		return froms;
	}

	public void setFroms(
			Collection<ManyToManyBidirectionNoCascadeFromEntity> froms) {
		this.froms = froms;
	}

	public void addFrom(ManyToManyBidirectionNoCascadeFromEntity from) {
		if (this.froms == null) {
			this.froms = new ArrayList<ManyToManyBidirectionNoCascadeFromEntity>();
		}
		this.froms.add(from);
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
		ManyToManyBidirectionNoCascadeToEntity other = (ManyToManyBidirectionNoCascadeToEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
