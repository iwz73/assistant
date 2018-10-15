package idv.hsiehpinghan.neo4jassistant.node;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Node_1_0 {
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

}
