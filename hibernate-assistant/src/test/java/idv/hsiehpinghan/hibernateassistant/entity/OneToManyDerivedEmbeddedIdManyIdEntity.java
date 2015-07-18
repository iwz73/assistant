package idv.hsiehpinghan.hibernateassistant.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class OneToManyDerivedEmbeddedIdManyIdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String manyKey;
	@Embedded
	private OneToManyDerivedEmbeddedIdOneIdEntity oneKey;

	public String getManyKey() {
		return manyKey;
	}

	public void setManyKey(String manyKey) {
		this.manyKey = manyKey;
	}

	public OneToManyDerivedEmbeddedIdOneIdEntity getOneKey() {
		return oneKey;
	}

	public void setOneKey(OneToManyDerivedEmbeddedIdOneIdEntity oneKey) {
		this.oneKey = oneKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((manyKey == null) ? 0 : manyKey.hashCode());
		result = prime * result + ((oneKey == null) ? 0 : oneKey.hashCode());
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
		OneToManyDerivedEmbeddedIdManyIdEntity other = (OneToManyDerivedEmbeddedIdManyIdEntity) obj;
		if (manyKey == null) {
			if (other.manyKey != null)
				return false;
		} else if (!manyKey.equals(other.manyKey))
			return false;
		if (oneKey == null) {
			if (other.oneKey != null)
				return false;
		} else if (!oneKey.equals(other.oneKey))
			return false;
		return true;
	}

}
