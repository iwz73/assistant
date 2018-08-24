package idv.hsiehpinghan.akkaassistant.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import idv.hsiehpinghan.akkaassistant.message.Message_0;
import idv.hsiehpinghan.akkaassistant.message.Message_1;

public class BasicActor extends AbstractActor {
	private ActorRef actorRef_0;
	private ActorRef actorRef_1;

	public static Props props(ActorRef actorRef_0, ActorRef actorRef_1) {
		return Props.create(BasicActor.class, () -> new BasicActor(actorRef_0, actorRef_1));
	}

	public BasicActor(ActorRef actorRef_0, ActorRef actorRef_1) {
		super();
		this.actorRef_0 = actorRef_0;
		this.actorRef_1 = actorRef_1;
	}

	@Override
	public Receive createReceive() {
		return receiveBuilder().match(Message_0.class, message_0 -> {
			actorRef_0.tell(message_0, getSelf());
		}).match(Message_1.class, message_1 -> {
			actorRef_1.tell(message_1, getSelf());
		}).build();
	}

}
