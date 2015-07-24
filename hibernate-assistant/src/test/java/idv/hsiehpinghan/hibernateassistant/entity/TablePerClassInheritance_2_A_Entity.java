package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Entity;

@Entity
public class TablePerClassInheritance_2_A_Entity extends
		TablePerClassInheritance_1_B_Entity {
	private String a2;

	public String getA2() {
		return a2;
	}

	public void setA2(String a2) {
		this.a2 = a2;
	}

}
