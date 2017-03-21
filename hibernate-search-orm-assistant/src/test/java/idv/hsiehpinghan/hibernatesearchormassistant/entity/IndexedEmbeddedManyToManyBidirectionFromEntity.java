package idv.hsiehpinghan.hibernatesearchormassistant.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Entity
@Indexed
public class IndexedEmbeddedManyToManyBidirectionFromEntity {
	@Id
	@DocumentId
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	@Field
	private String stringValue;

	@IndexedEmbedded
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "indexedEmbeddedManyToManyBidirectionJoinTable", joinColumns = @JoinColumn(name = "indexedEmbeddedManyToManyBidirectionFromEntityId"), inverseJoinColumns = @JoinColumn(name = "indexedEmbeddedManyToManyBidirectionToEntityId"))
	private Collection<IndexedEmbeddedManyToManyBidirectionToEntity> tos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public Collection<IndexedEmbeddedManyToManyBidirectionToEntity> getTos() {
		return tos;
	}

	public void setTos(Collection<IndexedEmbeddedManyToManyBidirectionToEntity> tos) {
		this.tos = tos;
	}

	public void addTo(IndexedEmbeddedManyToManyBidirectionToEntity to) {
		if (this.tos == null) {
			this.tos = new ArrayList<IndexedEmbeddedManyToManyBidirectionToEntity>();
		}
		this.tos.add(to);
	}
}
