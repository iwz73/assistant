package idv.hsiehpinghan.jacksonassistant.vo;

import java.util.List;

public class JsonVo {
	private Boolean _boolean;
	private Integer _integer;
	private Float _float;
	private String _string;
	private _Object _object;
	private List<String> _array;
	private List<_Object> _object_array;
	private String _null;

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

	@Override
	public String toString() {
		return "JsonVo [_boolean=" + _boolean + ", _integer=" + _integer
				+ ", _float=" + _float + ", _string=" + _string + ", _object="
				+ _object + ", _array=" + _array + ", _object_array="
				+ _object_array + ", _null=" + _null + "]";
	}

	public static class _Object {
		private Integer _integer;
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
