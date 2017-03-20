package idv.hsiehpinghan.hibernatesearchormassistant.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.NumericField;

@Entity
@Indexed
public class IndexedEmbeddedEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@DocumentId
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Integer id;
	@IndexedEmbedded
	private EmbeddableObject embeddableObject;
	@IndexedEmbedded
	@ElementCollection
	private Collection<EmbeddableCollection> embeddableCollections;
	@ElementCollection
	@CollectionTable(name = "integerStringMap")
	@MapKeyColumn(name = "[key]")
	@Column(name = "value")
	@Field
	@IndexedEmbedded
	private Map<Integer, String> integerStringMap;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EmbeddableObject getEmbeddableObject() {
		return embeddableObject;
	}

	public void setEmbeddableObject(EmbeddableObject embeddableObject) {
		this.embeddableObject = embeddableObject;
	}

	public Collection<EmbeddableCollection> getEmbeddableCollections() {
		return embeddableCollections;
	}

	public void setEmbeddableCollections(Collection<EmbeddableCollection> embeddableCollections) {
		this.embeddableCollections = embeddableCollections;
	}

	public Map<Integer, String> getIntegerStringMap() {
		return integerStringMap;
	}

	public void setIntegerStringMap(Map<Integer, String> integerStringMap) {
		this.integerStringMap = integerStringMap;
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
		IndexedEmbeddedEntity other = (IndexedEmbeddedEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Embeddable
	public static class EmbeddableObject {
		@Field
		private String name;
		@Field
		@NumericField
		private Long age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getAge() {
			return age;
		}

		public void setAge(Long age) {
			this.age = age;
		}

	}

	@Embeddable
	public static class EmbeddableCollection {
		@Field
		private String name;
		@Field
		@NumericField
		private Long age;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getAge() {
			return age;
		}

		public void setAge(Long age) {
			this.age = age;
		}

	}
}
