package idv.hsiehpinghan.akkaassistant;

import java.io.IOException;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import idv.hsiehpinghan.akkaassistant.actor.BasicActor;
import idv.hsiehpinghan.akkaassistant.actor.BasicActor_0;
import idv.hsiehpinghan.akkaassistant.actor.BasicActor_1;
import idv.hsiehpinghan.akkaassistant.message.Message_0;
import idv.hsiehpinghan.akkaassistant.message.Message_1;

public class ActorMain {
	public static void main(String[] args) {
		final ActorSystem actorSystem = ActorSystem.create("actorMain");
		try {
			ActorRef basicActor_0Ref = actorSystem.actorOf(BasicActor_0.props(), "basicActor_0");
			ActorRef basicActor_1Ref = actorSystem.actorOf(BasicActor_1.props(), "basicActor_1");
			ActorRef basicActorRef = actorSystem.actorOf(BasicActor.props(basicActor_0Ref, basicActor_1Ref),
					"basicActor");
			basicActorRef.tell(new Message_0("message_0"), ActorRef.noSender());
			basicActorRef.tell(new Message_1("message_1"), ActorRef.noSender());
			System.out.println(">>> Press ENTER to exit <<<");
			System.in.read();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			actorSystem.terminate();
		}
	}
}
