package idv.hsiehpinghan.springbatchassistant.mapper;

import java.util.Map;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.JsonLineMapper;

import idv.hsiehpinghan.springbatchassistant.vo.FlatFileJsonVo;
import idv.hsiehpinghan.springbatchassistant.vo.FlatFileJsonVo.SubMapFlatFileJsonVo;

public class FlatFileJsonLineMapper implements LineMapper<FlatFileJsonVo> {
	private JsonLineMapper delegate;

	@Override
	public FlatFileJsonVo mapLine(String line, int lineNumber) throws Exception {
		Map<String, Object> map = delegate.mapLine(line, lineNumber);
		FlatFileJsonVo vo = processFlatFileJsonVo(map, lineNumber);
		return vo;
	}

	public void setDelegate(JsonLineMapper delegate) {
		this.delegate = delegate;
	}

	private FlatFileJsonVo processFlatFileJsonVo(Map<String, Object> map, int lineNumber) {
		FlatFileJsonVo vo = new FlatFileJsonVo();
		vo.setLineNumber(lineNumber);
		Long longValue = ((Integer) map.get("longValue")).longValue();
		vo.setLongValue(longValue);
		String stringValue = (String) map.get("stringValue");
		vo.setStringValue(stringValue);
		SubMapFlatFileJsonVo subMapVo = processSubMapFlatFileJsonVo(map);
		vo.setSubMapFlatFileJsonVo(subMapVo);
		return vo;
	}

	private SubMapFlatFileJsonVo processSubMapFlatFileJsonVo(Map<String, Object> map) {
		@SuppressWarnings("unchecked")
		Map<String, Object> mapValue = (Map<String, Object>) map.get("mapValue");
		Float floatValue = ((Double) mapValue.get("floatValue")).floatValue();
		SubMapFlatFileJsonVo subMapVo = new SubMapFlatFileJsonVo();
		subMapVo.setFloatValue(floatValue);
		return subMapVo;
	}
}
