package idv.hsiehpinghan.jacksonassistant.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConstructorJsonVo {
	private Integer _integer;
	private String _string;

	@JsonCreator
	private ConstructorJsonVo(@JsonProperty("integer") Integer _integer,
			@JsonProperty("string") String _string) {
		this._integer = _integer;
		this._string = _string;
	}

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

}
