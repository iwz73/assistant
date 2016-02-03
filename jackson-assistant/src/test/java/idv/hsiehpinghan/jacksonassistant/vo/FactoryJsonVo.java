package idv.hsiehpinghan.jacksonassistant.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FactoryJsonVo {
	private Integer _integer;
	private String _string;

	@JsonCreator
	public FactoryJsonVo build(@JsonProperty("integer") Integer _integer,
			@JsonProperty("string") String _string) {
		FactoryJsonVo jsonVo = new FactoryJsonVo();
		jsonVo.set_integer(_integer);
		jsonVo.set_string(_string);
		return jsonVo;
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
