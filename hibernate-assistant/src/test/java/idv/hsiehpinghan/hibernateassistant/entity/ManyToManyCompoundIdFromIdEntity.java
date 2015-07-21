package idv.hsiehpinghan.hibernateassistant.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ManyToManyCompoundIdFromIdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String fromKey1;
	private String fromKey2;

	public String getFromKey1() {
		return fromKey1;
	}

	public void setFromKey1(String fromKey1) {
		this.fromKey1 = fromKey1;
	}

	public String getFromKey2() {
		return fromKey2;
	}

	public void setFromKey2(String fromKey2) {
		this.fromKey2 = fromKey2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fromKey1 == null) ? 0 : fromKey1.hashCode());
		result = prime * result
				+ ((fromKey2 == null) ? 0 : fromKey2.hashCode());
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
		ManyToManyCompoundIdFromIdEntity other = (ManyToManyCompoundIdFromIdEntity) obj;
		if (fromKey1 == null) {
			if (other.fromKey1 != null)
				return false;
		} else if (!fromKey1.equals(other.fromKey1))
			return false;
		if (fromKey2 == null) {
			if (other.fromKey2 != null)
				return false;
		} else if (!fromKey2.equals(other.fromKey2))
			return false;
		return true;
	}

}
