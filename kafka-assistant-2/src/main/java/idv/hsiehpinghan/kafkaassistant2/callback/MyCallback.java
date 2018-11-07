package idv.hsiehpinghan.kafkaassistant2.callback;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MyCallback implements Callback {
	private boolean isCallbacked = false;

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		isCallbacked = true;
	}

	public boolean isCallbacked() {
		return isCallbacked;
	}

	public void setCallbacked(boolean isCallbacked) {
		this.isCallbacked = isCallbacked;
	}

}
