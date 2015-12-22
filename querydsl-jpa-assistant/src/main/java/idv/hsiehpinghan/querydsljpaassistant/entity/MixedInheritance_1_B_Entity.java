package idv.hsiehpinghan.querydsljpaassistant.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class MixedInheritance_1_B_Entity extends
		MixedInheritance_0_Entity {
	private String b1;

	public String getB1() {
		return b1;
	}

	public void setB1(String b1) {
		this.b1 = b1;
	}

}
