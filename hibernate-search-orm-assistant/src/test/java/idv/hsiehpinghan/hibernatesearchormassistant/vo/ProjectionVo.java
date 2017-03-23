package idv.hsiehpinghan.hibernatesearchormassistant.vo;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Explanation;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.BasicTypeEntity;

public class ProjectionVo {
	private String projectionString;
	private BasicTypeEntity __HSearch_This;
	private Document __HSearch_Document;
	private Float __HSearch_Score;
	private Integer __HSearch_id;
	private Integer __HSearch_DocumentId;
	private Explanation __HSearch_Explanation;
	private Class<BasicTypeEntity> _hibernate_class;

	public String getProjectionString() {
		return projectionString;
	}

	public void setProjectionString(String projectionString) {
		this.projectionString = projectionString;
	}

	public BasicTypeEntity get__HSearch_This() {
		return __HSearch_This;
	}

	public void set__HSearch_This(BasicTypeEntity __HSearch_This) {
		this.__HSearch_This = __HSearch_This;
	}

	public Document get__HSearch_Document() {
		return __HSearch_Document;
	}

	public void set__HSearch_Document(Document __HSearch_Document) {
		this.__HSearch_Document = __HSearch_Document;
	}

	public Float get__HSearch_Score() {
		return __HSearch_Score;
	}

	public void set__HSearch_Score(Float __HSearch_Score) {
		this.__HSearch_Score = __HSearch_Score;
	}

	public Integer get__HSearch_id() {
		return __HSearch_id;
	}

	public void set__HSearch_id(Integer __HSearch_id) {
		this.__HSearch_id = __HSearch_id;
	}

	public Integer get__HSearch_DocumentId() {
		return __HSearch_DocumentId;
	}

	public void set__HSearch_DocumentId(Integer __HSearch_DocumentId) {
		this.__HSearch_DocumentId = __HSearch_DocumentId;
	}

	public Explanation get__HSearch_Explanation() {
		return __HSearch_Explanation;
	}

	public void set__HSearch_Explanation(Explanation __HSearch_Explanation) {
		this.__HSearch_Explanation = __HSearch_Explanation;
	}

	public Class<BasicTypeEntity> get_hibernate_class() {
		return _hibernate_class;
	}

	public void set_hibernate_class(Class<BasicTypeEntity> _hibernate_class) {
		this._hibernate_class = _hibernate_class;
	}

}
