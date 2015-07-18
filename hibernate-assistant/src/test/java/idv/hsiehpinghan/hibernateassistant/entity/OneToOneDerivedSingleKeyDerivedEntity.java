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

}
