package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyJoinTableToEntity {
	@Id
	private Integer id;

	@ManyToMany(mappedBy = "tos")
	private Collection<ManyToManyJoinTableFromEntity> froms;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ManyToManyJoinTableFromEntity> getFroms() {
		return froms;
	}

	public void setFroms(Collection<ManyToManyJoinTableFromEntity> froms) {
		this.froms = froms;
	}

	public void addFrom(ManyToManyJoinTableFromEntity from) {
		if (this.froms == null) {
			this.froms = new ArrayList<ManyToManyJoinTableFromEntity>();
		}
		this.froms.add(from);
	}

}
