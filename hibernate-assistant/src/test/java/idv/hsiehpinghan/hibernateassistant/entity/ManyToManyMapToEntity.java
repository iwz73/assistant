package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyMapToEntity {
	@Id
	private Integer id;

	@ManyToMany(mappedBy = "tos")
	private Collection<ManyToManyMapFromEntity> froms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ManyToManyMapFromEntity> getFroms() {
		return froms;
	}

	public void setFroms(Collection<ManyToManyMapFromEntity> froms) {
		this.froms = froms;
	}

	public void addFrom(ManyToManyMapFromEntity from) {
		if (this.froms == null) {
			this.froms = new ArrayList<ManyToManyMapFromEntity>();
		}
		this.froms.add(from);
	}

}
