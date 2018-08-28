package idv.hsiehpinghan.kafkaassistant.processor;

import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

import idv.hsiehpinghan.kafkaassistant.vo.AggregateJsonVo;
import idv.hsiehpinghan.kafkaassistant.vo.JsonVo;

public class AggregateJsonVoProcessor extends AbstractProcessor<Long, JsonVo> {
	private static final long PUNCTUATIONS_INTERVAL_MILLI_SECOND = 500;
	private ProcessorContext context;
	private KeyValueStore<Integer, AggregateJsonVo> aggregateStore;

	@Override
	@SuppressWarnings("unchecked")
	public void init(ProcessorContext context) {
		this.context = context;
		this.context.schedule(PUNCTUATIONS_INTERVAL_MILLI_SECOND);
		aggregateStore = (KeyValueStore<Integer, AggregateJsonVo>) this.context.getStateStore("aggregate_store");
	}

	public void process(Long key, JsonVo jsonVo) {
		Integer _integer = jsonVo.get_integer();
		AggregateJsonVo aggregateJsonVo = aggregateStore.get(_integer);
		if (aggregateJsonVo == null) {
			aggregateJsonVo = new AggregateJsonVo.Builder(jsonVo).build();
		} else {
			aggregateJsonVo.aggregate(jsonVo);
		}
		aggregateStore.put(_integer, aggregateJsonVo);
		this.context.commit();
	}

	@Override
	public void punctuate(long timestamp) {
		KeyValueIterator<Integer, AggregateJsonVo> iter = aggregateStore.all();
		while (iter.hasNext()) {
			AggregateJsonVo aggregateJsonVo = iter.next().value;
			this.context.forward(aggregateJsonVo.get_integer(), aggregateJsonVo);
		}
	}

}
