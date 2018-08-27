package idv.hsiehpinghan.kafkaassistant.vo;

public class UpperCaseJsonVo {
	private String _string;

	public UpperCaseJsonVo() {
		super();
	}

	public UpperCaseJsonVo(String _string) {
		super();
		this._string = _string;
	}

	public String get_string() {
		return _string;
	}

	public void set_string(String _string) {
		this._string = _string;
	}

	public static Builder builder(JsonVo jsonVo) {
		return new Builder(jsonVo);
	}

	public static final class Builder {
		private JsonVo jsonVo;

		public Builder() {
			super();
		}

		public Builder(JsonVo jsonVo) {
			super();
			this.jsonVo = jsonVo;
		}

		public UpperCaseJsonVo build() {
			String _string = jsonVo.get_string().toUpperCase();
			return new UpperCaseJsonVo(_string);
		}

	}
}
