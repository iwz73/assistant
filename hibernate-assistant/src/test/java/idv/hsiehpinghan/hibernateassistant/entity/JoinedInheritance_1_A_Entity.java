package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1A")
public class JoinedInheritance_1_A_Entity extends JoinedInheritance_0_Entity {
	private String a1;

	public String getA1() {
		return a1;
	}

	public void setA1(String a1) {
		this.a1 = a1;
	}

}
