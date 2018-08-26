package idv.hsiehpinghan.kafkaassistant2.processor;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.AbstractProcessor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;

public class BasicProcessor extends AbstractProcessor<Long, String> {
	private static final String STATE_STORE_NAME = "stateStore";
	private static final long PUNCTUATE_INTERVAL_MILLISECONDS = 1000;
	private ProcessorContext processorContext;
	private KeyValueStore<Long, String> keyValueStore;
	
	@Override
	@SuppressWarnings("unchecked")
	public void init(ProcessorContext processorContext) {
	      this.processorContext = processorContext;
	      keyValueStore = (KeyValueStore<Long, String>) processorContext.getStateStore(STATE_STORE_NAME);
	      this.processorContext.schedule(PUNCTUATE_INTERVAL_MILLISECONDS, PunctuationType.STREAM_TIME, (timestamp) -> {
	          try(KeyValueIterator<Long, String> iter = this.keyValueStore.all();) {
		          while (iter.hasNext()) {
		              KeyValue<Long, String> entry = iter.next();
		              processorContext.forward(entry.key, entry.value);
		          }
	          }
	          processorContext.commit();
	      });
		
	}

	@Override
	public void process(Long key, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// do nothing
	}
}
