package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;

@Entity
@SecondaryTable(name = "embeddedMultipleTableSecondaryTable", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
public class EmbeddedMultipleTableContainerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "address", column = @Column(name = "addr", table = "embeddedMultipleTableSecondaryTable")) })
	private EmbeddedMultipleTableEmbeddableEntity embeddable;

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

	public EmbeddedMultipleTableEmbeddableEntity getEmbeddable() {
		return embeddable;
	}

	public void setEmbeddable(EmbeddedMultipleTableEmbeddableEntity embeddable) {
		this.embeddable = embeddable;
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
		EmbeddedMultipleTableContainerEntity other = (EmbeddedMultipleTableContainerEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
