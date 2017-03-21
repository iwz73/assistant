package idv.hsiehpinghan.hibernatesearchormassistant.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class IndexedEmbeddedManyToManyBidirectionToEntity {
	@Id
	@DocumentId
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	@Field
	private String stringValue;
	@ContainedIn
	@ManyToMany(mappedBy = "tos")
	private Collection<IndexedEmbeddedManyToManyBidirectionFromEntity> froms;

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

	public Collection<IndexedEmbeddedManyToManyBidirectionFromEntity> getFroms() {
		return froms;
	}

	public void setFroms(Collection<IndexedEmbeddedManyToManyBidirectionFromEntity> froms) {
		this.froms = froms;
	}

	public void addFrom(IndexedEmbeddedManyToManyBidirectionFromEntity from) {
		if (this.froms == null) {
			this.froms = new ArrayList<IndexedEmbeddedManyToManyBidirectionFromEntity>();
		}
		this.froms.add(from);
	}

}
