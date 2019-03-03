package idv.hsiehpinghan.hibernateassistant.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OneToOneDerivedSingleKeyDerivedEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@OneToOne
	@JoinColumn(name = "derivedId")
	private OneToOneDerivedSingleKeyMainEntity main;

	public OneToOneDerivedSingleKeyMainEntity getMain() {
		return main;
	}

	public void setMain(OneToOneDerivedSingleKeyMainEntity main) {
		this.main = main;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((main == null) ? 0 : main.hashCode());
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
		OneToOneDerivedSingleKeyDerivedEntity other = (OneToOneDerivedSingleKeyDerivedEntity) obj;
		if (main == null) {
			if (other.main != null)
				return false;
		} else if (!main.equals(other.main))
			return false;
		return true;
	}

}
