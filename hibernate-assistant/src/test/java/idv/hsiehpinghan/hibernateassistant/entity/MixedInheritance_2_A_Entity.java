package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2A")
public class MixedInheritance_2_A_Entity extends MixedInheritance_1_B_Entity {
	private String a2;

	public String getA2() {
		return a2;
	}

	public void setA2(String a2) {
		this.a2 = a2;
	}

}
