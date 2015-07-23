package idv.hsiehpinghan.springdatajpaassistatnt.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OneToManyBidirectionOneEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@OneToMany(mappedBy = "one", cascade = CascadeType.PERSIST)
	private Collection<OneToManyBidirectionManyEntity_1> many_1;

	@OneToMany(mappedBy = "one", cascade = CascadeType.PERSIST)
	private Collection<OneToManyBidirectionManyEntity_2> many_2;

	@OneToOne(cascade = CascadeType.ALL)
	private OneToOneBidirectionToEntity to;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<OneToManyBidirectionManyEntity_1> getMany_1() {
		return many_1;
	}

	public void setMany_1(Collection<OneToManyBidirectionManyEntity_1> many_1) {
		this.many_1 = many_1;
	}

	public Collection<OneToManyBidirectionManyEntity_2> getMany_2() {
		return many_2;
	}

	public void setMany_2(Collection<OneToManyBidirectionManyEntity_2> many_2) {
		this.many_2 = many_2;
	}

	public OneToOneBidirectionToEntity getTo() {
		return to;
	}

	public void setTo(OneToOneBidirectionToEntity to) {
		this.to = to;
	}

}
