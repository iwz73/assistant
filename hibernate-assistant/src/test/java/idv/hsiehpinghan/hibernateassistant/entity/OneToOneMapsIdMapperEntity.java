package idv.hsiehpinghan.hibernateassistant.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class OneToOneMapsIdMapperEntity {
	@Id
	private int id;
	@MapsId
	@OneToOne
	@JoinColumn(name = "mapsId")
	private OneToOneMapsIdMainEntity main;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OneToOneMapsIdMainEntity getMain() {
		return main;
	}

	public void setMain(OneToOneMapsIdMainEntity main) {
		this.main = main;
	}

}
