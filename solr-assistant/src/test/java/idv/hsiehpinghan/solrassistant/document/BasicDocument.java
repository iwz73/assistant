package idv.hsiehpinghan.solrassistant.document;

import org.apache.solr.client.solrj.beans.Field;

public class BasicDocument {
	@Field
	private String id;
	@Field
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
