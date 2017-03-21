package idv.hsiehpinghan.hibernatesearchormassistant.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Entity
@Indexed
public class IndexedEmbeddedOneToManyBidirectionOneEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	private Integer id;
	@Field
	private String stringValue;
	@OneToMany(mappedBy = "one", cascade = CascadeType.ALL)
	@IndexedEmbedded
	private Collection<IndexedEmbeddedOneToManyBidirectionManyEntity> many;

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

	public Collection<IndexedEmbeddedOneToManyBidirectionManyEntity> getMany() {
		return many;
	}

	public void setMany(Collection<IndexedEmbeddedOneToManyBidirectionManyEntity> many) {
		this.many = many;
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
		IndexedEmbeddedOneToManyBidirectionOneEntity other = (IndexedEmbeddedOneToManyBidirectionOneEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
