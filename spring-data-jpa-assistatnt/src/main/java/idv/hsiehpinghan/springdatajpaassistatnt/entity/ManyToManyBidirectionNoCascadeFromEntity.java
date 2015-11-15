package idv.hsiehpinghan.springdatajpaassistatnt.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ManyToManyBidirectionNoCascadeFromEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@ManyToMany
	private Collection<ManyToManyBidirectionNoCascadeToEntity> tos;

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

	public Collection<ManyToManyBidirectionNoCascadeToEntity> getTos() {
		return tos;
	}

	public void setTos(Collection<ManyToManyBidirectionNoCascadeToEntity> tos) {
		this.tos = tos;
	}

	public void addTo(ManyToManyBidirectionNoCascadeToEntity to) {
		if (this.tos == null) {
			this.tos = new ArrayList<ManyToManyBidirectionNoCascadeToEntity>();
		}
		this.tos.add(to);
	}

}
