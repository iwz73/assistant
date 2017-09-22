package idv.hsiehpinghan.springsecurityassistant.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String rolename;

	@ManyToMany(mappedBy = "users")
	private Collection<UserEntity> Users;

	public RoleEntity() {
		super();
	}

	public RoleEntity(String rolename, Collection<UserEntity> users) {
		super();
		this.rolename = rolename;
		Users = users;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Collection<UserEntity> getUsers() {
		return Users;
	}

	public void setUsers(Collection<UserEntity> users) {
		Users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rolename == null) ? 0 : rolename.hashCode());
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
		RoleEntity other = (RoleEntity) obj;
		if (rolename == null) {
			if (other.rolename != null)
				return false;
		} else if (!rolename.equals(other.rolename))
			return false;
		return true;
	}

}
