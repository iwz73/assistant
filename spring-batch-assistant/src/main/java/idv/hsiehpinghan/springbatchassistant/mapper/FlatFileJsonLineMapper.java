package idv.hsiehpinghan.springbatchassistant.mapper;

import java.util.Map;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.JsonLineMapper;

import idv.hsiehpinghan.springbatchassistant.vo.FlatFileJsonVo;

public class FlatFileJsonLineMapper implements LineMapper<FlatFileJsonVo> {
	private JsonLineMapper delegate;

	@Override
	public FlatFileJsonVo mapLine(String line, int lineNumber) throws Exception {
		Map<String, Object> map = delegate.mapLine(line, lineNumber);
		FlatFileJsonVo vo = new FlatFileJsonVo();
		vo.setLineNumber(lineNumber);
		Long longValue = ((Integer)map.get("longValue")).longValue();
		vo.setLongValue(longValue);
		String stringValue = (String) map.get("stringValue");
		vo.setStringValue(stringValue);
		return vo;
	}

	public void setDelegate(JsonLineMapper delegate) {
		this.delegate = delegate;
	}
}
