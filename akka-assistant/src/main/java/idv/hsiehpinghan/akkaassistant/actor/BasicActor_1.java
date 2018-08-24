package idv.hsiehpinghan.akkaassistant.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import idv.hsiehpinghan.akkaassistant.message.Message_1;

public class BasicActor_1 extends AbstractActor {
	private LoggingAdapter loggingAdapter = Logging.getLogger(getContext().getSystem(), this);

	public static Props props() {
		return Props.create(BasicActor_1.class, () -> new BasicActor_1());
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Message_1.class, message_1 -> {
			loggingAdapter.info(message_1.getMessage());
		}).build();
	}

}
