package idv.hsiehpinghan.hibernatesearchormassistant.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class IndexedEmbeddedOneToManyBidirectionManyEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@DocumentId
	private Integer id;
	@Field
	private String stringValue;
	@ManyToOne
	@ContainedIn
	private IndexedEmbeddedOneToManyBidirectionOneEntity one;

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

	public IndexedEmbeddedOneToManyBidirectionOneEntity getOne() {
		return one;
	}

	public void setOne(IndexedEmbeddedOneToManyBidirectionOneEntity one) {
		this.one = one;
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
		IndexedEmbeddedOneToManyBidirectionManyEntity other = (IndexedEmbeddedOneToManyBidirectionManyEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
