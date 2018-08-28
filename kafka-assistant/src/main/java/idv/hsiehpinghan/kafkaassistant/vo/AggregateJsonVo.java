package idv.hsiehpinghan.kafkaassistant.vo;

public class AggregateJsonVo {
	private Integer _integer;
	private Float _float;

	public AggregateJsonVo() {
		super();
	}

	public AggregateJsonVo(Integer _integer, Float _float) {
		super();
		this._integer = _integer;
		this._float = _float;
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

	public void aggregate(JsonVo jsonVo) {
		this._float += jsonVo.get_float();
	}

	@Override
	public String toString() {
		return "AggregateJsonVo [_integer=" + _integer + ", _float=" + _float + "]";
	}

	public static final class Builder {
		private Integer _integer;
		private Float _float;

		public Builder() {
			super();
		}

		public Builder(JsonVo jsonVo) {
			super();
			this._integer = jsonVo.get_integer();
			this._float = jsonVo.get_float();
		}

		public AggregateJsonVo build() {
			return new AggregateJsonVo(_integer, _float);
		}

	}
}
