package idv.hsiehpinghan.springjdbcassistant.entity;

import java.io.Serializable;
import java.util.Set;

public class OneEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private Set<ManyEntity> many;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ManyEntity> getMany() {
		return many;
	}

	public void setMany(Set<ManyEntity> many) {
		this.many = many;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		OneEntity other = (OneEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
