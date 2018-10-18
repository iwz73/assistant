package idv.hsiehpinghan.neo4jassistant.relationship;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;

public class BaseRelationship {
	@Id
	@GeneratedValue
	private Long nativeGraphId;

	public Long getNativeGraphId() {
		return nativeGraphId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nativeGraphId == null) ? 0 : nativeGraphId.hashCode());
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
		BaseRelationship other = (BaseRelationship) obj;
		if (nativeGraphId == null) {
			if (other.nativeGraphId != null)
				return false;
		} else if (!nativeGraphId.equals(other.nativeGraphId))
			return false;
		return true;
	}

}
