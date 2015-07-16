package idv.hsiehpinghan.hibernateassistant.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;

@Entity
public class ManyToManyMapFromEntity {
	@Id
	private Integer id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "manyToManyMapJoinTable", joinColumns = @JoinColumn(name = "fromColumn"), inverseJoinColumns = @JoinColumn(name = "toColumn"))
	@MapKeyColumn(name = "mapKey", nullable = true)
	private Map<String, ManyToManyMapToEntity> tos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<String, ManyToManyMapToEntity> getTos() {
		return tos;
	}

	public void setTos(Map<String, ManyToManyMapToEntity> tos) {
		this.tos = tos;
	}

	public void addTo(String key, ManyToManyMapToEntity value) {
		if (this.tos == null) {
			this.tos = new HashMap<String, ManyToManyMapToEntity>();
		}
		this.tos.put(key, value);
	}
}
