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
@Table(name = "WORK_ENTRY")
public class WorkEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Employer
	 */
	@ManyToOne
	@JoinColumn(name = "employer")
	private Reference employer;
	/**
	 * End date
	 */
	@Column(name = "end_date")
	private String endDate;
	/**
	 * Location
	 */
	@ManyToOne
	@JoinColumn(name = "location")
	private Page location;
	/**
	 * Position
	 */
	@ManyToOne
	@JoinColumn(name = "position")
	private Page position;
	/**
	 * Start date
	 */
	@Column(name = "start_date")
	private String startDate;
	/**
	 * Projects
	 */
	@ManyToMany
	@Column(name = "projects")
	@JoinTable(name = "J_WORK_ENTRY_PROJECTS", joinColumns = @JoinColumn(name = "work_entry_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
	private List<Project> projects;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reference getEmployer() {
		return employer;
	}

	public void setEmployer(Reference employer) {
		this.employer = employer;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Page getLocation() {
		return location;
	}

	public void setLocation(Page location) {
		this.location = location;
	}

	public Page getPosition() {
		return position;
	}

	public void setPosition(Page position) {
		this.position = position;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
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
		WorkEntry other = (WorkEntry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
