package idv.hsiehpinghan.hibernateassistant.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OneToManyDerivedEmbeddedIdOneIdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String oneKey1;
	private String oneKey2;

	public String getOneKey1() {
		return oneKey1;
	}

	public void setOneKey1(String oneKey1) {
		this.oneKey1 = oneKey1;
	}

	public String getOneKey2() {
		return oneKey2;
	}

	public void setOneKey2(String oneKey2) {
		this.oneKey2 = oneKey2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oneKey1 == null) ? 0 : oneKey1.hashCode());
		result = prime * result + ((oneKey2 == null) ? 0 : oneKey2.hashCode());
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
		OneToManyDerivedEmbeddedIdOneIdEntity other = (OneToManyDerivedEmbeddedIdOneIdEntity) obj;
		if (oneKey1 == null) {
			if (other.oneKey1 != null)
				return false;
		} else if (!oneKey1.equals(other.oneKey1))
			return false;
		if (oneKey2 == null) {
			if (other.oneKey2 != null)
				return false;
		} else if (!oneKey2.equals(other.oneKey2))
			return false;
		return true;
	}

}
