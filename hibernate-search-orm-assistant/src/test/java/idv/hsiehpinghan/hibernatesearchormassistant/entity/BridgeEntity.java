package idv.hsiehpinghan.hibernatesearchormassistant.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

import idv.hsiehpinghan.hibernatesearchormassistant.bridge.EmbeddedIdTwoWayFieldBridgeBridge;
import idv.hsiehpinghan.hibernatesearchormassistant.bridge.StringBridgeBridge;

@Entity
@Indexed
public class BridgeEntity implements Serializable {
	public static final String DEFAULT_FIELD = "string";
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	@DocumentId
	@FieldBridge(impl = EmbeddedIdTwoWayFieldBridgeBridge.class)
	private BridgeEntityId id;
	@Field
	@FieldBridge(impl = StringBridgeBridge.class)
	private double stringBridgeDouble;

	public BridgeEntityId getId() {
		return id;
	}

	public void setId(BridgeEntityId id) {
		this.id = id;
	}

	public double getStringBridgeDouble() {
		return stringBridgeDouble;
	}

	public void setStringBridgeDouble(double stringBridgeDouble) {
		this.stringBridgeDouble = stringBridgeDouble;
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
		BridgeEntity other = (BridgeEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Embeddable
	public static class BridgeEntityId implements Serializable {
		private static final long serialVersionUID = 1L;
		private String firstName;
		private String lastName;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
			BridgeEntityId other = (BridgeEntityId) obj;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			return true;
		}

	}
}
