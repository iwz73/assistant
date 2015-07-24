package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class JoinedInheritance_1_B_Entity extends
		JoinedInheritance_0_Entity {
	private String b1;

	public String getB1() {
		return b1;
	}

	public void setB1(String b1) {
		this.b1 = b1;
	}

}
