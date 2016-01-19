package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EDUCATION_EXPERIENCE")
public class EducationExperience implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Classes taken
	 */
	@ManyToMany
	@Column(name = "classes")
	@JoinTable(name = "J_EDUCATION_EXPERIENCE_CLASSES", joinColumns = @JoinColumn(name = "education_experience_id"), inverseJoinColumns = @JoinColumn(name = "experience_id"))
	private List<Experience> classes;
	/**
	 * Facebook Pages representing subjects studied
	 */
	@ManyToMany
	@Column(name = "concentration")
	@JoinTable(name = "J_EDUCATION_EXPERIENCE_CONCENTRATION", joinColumns = @JoinColumn(name = "education_experience_id"), inverseJoinColumns = @JoinColumn(name = "reference_id"))
	private List<Reference> concentration;
	/**
	 * The Facebook Page for the degree obtained
	 */
	@ManyToOne
	@JoinColumn(name = "degree")
	private Reference degree;
	/**
	 * The Facebook Page for this school
	 */
	@ManyToOne
	@JoinColumn(name = "school")
	private Reference school;
	/**
	 * The type of educational institution
	 */
	private String type;
	/**
	 * People tagged who went to school with this person
	 */
	@ManyToMany
	@Column(name = "with")
	@JoinTable(name = "J_EDUCATION_EXPERIENCE_WITH", joinColumns = @JoinColumn(name = "education_experience_id"), inverseJoinColumns = @JoinColumn(name = "reference_id"))
	private List<Reference> with;
	/**
	 * Facebook Page for the year this person graduated
	 */
	@ManyToOne
	@JoinColumn(name = "year")
	private Reference year;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Experience> getClasses() {
		return classes;
	}

	public void setClasses(List<Experience> classes) {
		this.classes = classes;
	}

	public List<Reference> getConcentration() {
		return concentration;
	}

	public void setConcentration(List<Reference> concentration) {
		this.concentration = concentration;
	}

	public Reference getDegree() {
		return degree;
	}

	public void setDegree(Reference degree) {
		this.degree = degree;
	}

	public Reference getSchool() {
		return school;
	}

	public void setSchool(Reference school) {
		this.school = school;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Reference> getWith() {
		return with;
	}

	public void setWith(List<Reference> with) {
		this.with = with;
	}

	public Reference getYear() {
		return year;
	}

	public void setYear(Reference year) {
		this.year = year;
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
		EducationExperience other = (EducationExperience) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
