package idv.hsiehpinghan.rassistant.result;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class ResultBase {
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
