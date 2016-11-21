package idv.hsiehpinghan.springbatchassistant.aggregator;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.batch.item.file.transform.LineAggregator;

import idv.hsiehpinghan.springbatchassistant.vo.FlatFileJsonVo;

public class FlatFileJsonLineAggregator implements LineAggregator<FlatFileJsonVo> {
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String aggregate(FlatFileJsonVo item) {
		try {
			return objectMapper.writeValueAsString(item);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
