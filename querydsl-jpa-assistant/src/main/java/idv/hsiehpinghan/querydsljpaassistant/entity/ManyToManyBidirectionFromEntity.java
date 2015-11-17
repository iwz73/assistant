package idv.hsiehpinghan.querydsljpaassistant.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
}
