package idv.hsiehpinghan.kafkaassistant.processor;

import org.apache.kafka.streams.processor.AbstractProcessor;

import idv.hsiehpinghan.kafkaassistant.vo.JsonVo;
import idv.hsiehpinghan.kafkaassistant.vo.UpperCaseJsonVo;

public class UpperCaseJsonVoProcessor extends AbstractProcessor<Long, JsonVo> {

	@Override
	public void process(Long key, JsonVo jsonVo) {
		UpperCaseJsonVo upperCaseJsonVo = new UpperCaseJsonVo.Builder(jsonVo).build();
		context().forward(key, upperCaseJsonVo);
		context().commit();
	}

}
