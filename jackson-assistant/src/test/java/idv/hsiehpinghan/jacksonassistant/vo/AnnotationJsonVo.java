package idv.hsiehpinghan.jacksonassistant.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties({ "json_ignore_properties" })
public class AnnotationJsonVo {
	@JsonProperty("boolean")
	private Boolean _boolean;
	@JsonProperty("integer")
	private Integer _integer;
	@JsonProperty("float")
	private Float _float;
	@JsonProperty("string")
	private String _string;
	@JsonProperty("object")
	private _Object _object;
	@JsonProperty("array")
	private List<String> _array;
	@JsonProperty("object_array")
	private List<_Object> _object_array;
	@JsonProperty("null")
	private String _null;
	private String json_ignore_properties;
	@JsonIgnore
	private String json_ignore;

	public Boolean get_boolean() {
		return _boolean;
	}

	public void set_boolean(Boolean _boolean) {
		this._boolean = _boolean;
	}

	public Integer get_integer() {
		return _integer;
	}

	public void set_integer(Integer _integer) {
		this._integer = _integer;
	}

	public Float get_float() {
		return _float;
	}

	public void set_float(Float _float) {
		this._float = _float;
	}

	public String get_string() {
		return _string;
	}

	public void set_string(String _string) {
		this._string = _string;
	}

	public _Object get_object() {
		return _object;
	}

	public void set_object(_Object _object) {
		this._object = _object;
	}

	public List<String> get_array() {
		return _array;
	}

	public void set_array(List<String> _array) {
		this._array = _array;
	}

	public List<_Object> get_object_array() {
		return _object_array;
	}

	public void set_object_array(List<_Object> _object_array) {
		this._object_array = _object_array;
	}

	public String get_null() {
		return _null;
	}

	public void set_null(String _null) {
		this._null = _null;
	}

	public String getJson_ignore_properties() {
		return json_ignore_properties;
	}

	public void setJson_ignore_properties(String json_ignore_properties) {
		this.json_ignore_properties = json_ignore_properties;
	}

	public String getJson_ignore() {
		return json_ignore;
	}

	public void setJson_ignore(String json_ignore) {
		this.json_ignore = json_ignore;
	}

	@Override
	public String toString() {
		return "JsonVo [_boolean=" + _boolean + ", _integer=" + _integer
				+ ", _float=" + _float + ", _string=" + _string + ", _object="
				+ _object + ", _array=" + _array + ", _object_array="
				+ _object_array + ", _null=" + _null + "]";
	}

	public static class _Object {
		@JsonProperty("integer")
		private Integer _integer;
		@JsonProperty("string")
		private String _string;

		public Integer get_integer() {
			return _integer;
		}

		public void set_integer(Integer _integer) {
			this._integer = _integer;
		}

		public String get_string() {
			return _string;
		}

		public void set_string(String _string) {
			this._string = _string;
		}

		@Override
		public String toString() {
			return "_Object [_integer=" + _integer + ", _string=" + _string
					+ "]";
		}

	}

}
