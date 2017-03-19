package idv.hsiehpinghan.hibernatesearchormassistant.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;

import idv.hsiehpinghan.hibernatesearchormassistant.bridge.FieldBridgeBridge;
import idv.hsiehpinghan.hibernatesearchormassistant.bridge.StringBridgeBridge;
import idv.hsiehpinghan.hibernatesearchormassistant.bridge.TwoWayFieldBridgeBridge;
import idv.hsiehpinghan.hibernatesearchormassistant.bridge.TwoWayStringBridgeBridge;

@Entity
@Indexed
public class BridgeEntity implements Serializable {
	public static final String DEFAULT_FIELD = "string";
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	@DocumentId
	@FieldBridge(impl = TwoWayFieldBridgeBridge.class)
	private BridgeEntityId id;
	@ElementCollection
	@Field
	@FieldBridge(impl = FieldBridgeBridge.class)
	private Map<String, Integer> fieldBridgeBridgeMap;
	@Field
	@FieldBridge(impl = StringBridgeBridge.class, params = { @Parameter(name = "round", value = "10.0"),
			@Parameter(name = "length", value = "7"), @Parameter(name = "pad", value = "0") })
	private double stringBridgeDouble;
	@Field
	@FieldBridge(impl = TwoWayStringBridgeBridge.class, params = { @Parameter(name = "round", value = "10.0"),
			@Parameter(name = "length", value = "7"), @Parameter(name = "pad", value = "0") })
	private double twoWayStringBridgeBridgeDouble;

	public BridgeEntityId getId() {
		return id;
	}

	public void setId(BridgeEntityId id) {
		this.id = id;
	}

	public Map<String, Integer> getFieldBridgeBridgeMap() {
		return fieldBridgeBridgeMap;
	}

	public void setFieldBridgeBridgeMap(Map<String, Integer> fieldBridgeBridgeMap) {
		this.fieldBridgeBridgeMap = fieldBridgeBridgeMap;
	}

	public double getStringBridgeDouble() {
		return stringBridgeDouble;
	}

	public void setStringBridgeDouble(double stringBridgeDouble) {
		this.stringBridgeDouble = stringBridgeDouble;
	}

	public double getTwoWayStringBridgeBridgeDouble() {
		return twoWayStringBridgeBridgeDouble;
	}

	public void setTwoWayStringBridgeBridgeDouble(double twoWayStringBridgeBridgeDouble) {
		this.twoWayStringBridgeBridgeDouble = twoWayStringBridgeBridgeDouble;
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
