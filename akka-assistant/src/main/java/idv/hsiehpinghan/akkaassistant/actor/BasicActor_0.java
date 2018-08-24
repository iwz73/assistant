package idv.hsiehpinghan.akkaassistant.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import idv.hsiehpinghan.akkaassistant.message.Message_0;

public class BasicActor_0 extends AbstractActor {
	private LoggingAdapter loggingAdapter = Logging.getLogger(getContext().getSystem(), this);

	public static Props props() {
		return Props.create(BasicActor_0.class, () -> new BasicActor_0());
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Message_0.class, message_0 -> {
			loggingAdapter.info(message_0.getMessage());
		}).build();
	}

}
