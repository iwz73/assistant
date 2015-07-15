package idv.hsiehpinghan.hibernateassistant.entity;

import idv.hsiehpinghan.hibernateassistant.enumeration.Enumeration;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;

@Entity
public class ElementCollectionEnumerationMapEntity4 {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ElementCollection
	@CollectionTable(name = "enumerationMap")
	@MapKeyEnumerated(EnumType.STRING)
	@MapKeyColumn(name = "key")
	@Column(name = "value")
	private Map<Enumeration, String> map;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<Enumeration, String> getMap() {
		return map;
	}

	public void setMap(Map<Enumeration, String> map) {
		this.map = map;
	}

}
