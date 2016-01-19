package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "PROJECT")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * ID
	 */
	@Id
	private String id;
	/**
	 * Name
	 */
	private String name;
	/**
	 * Description
	 */
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String description;
	/**
	 * End date
	 */
	private String endDate;
	/**
	 * Start date
	 */
	private String startDate;
	/**
	 * From
	 */
	@ManyToOne
	@JoinColumn(name = "[from]")
	private Reference from;
	/**
	 * Tagged users
	 */
	@ManyToMany
	@Column(name = "with")
	@JoinTable(name = "J_PROJECT_WITH", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "reference_id"))
	private List<Reference> with;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Reference getFrom() {
		return from;
	}

	public void setFrom(Reference from) {
		this.from = from;
	}

	public List<Reference> getWith() {
		return with;
	}

	public void setWith(List<Reference> with) {
		this.with = with;
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
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
