package idv.hsiehpinghan.querydsljpaassistant.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2B")
public class MixedInheritance_2_B_Entity extends MixedInheritance_1_B_Entity {
	private String b2;

	public String getB2() {
		return b2;
	}

	public void setB2(String b2) {
		this.b2 = b2;
	}

}
