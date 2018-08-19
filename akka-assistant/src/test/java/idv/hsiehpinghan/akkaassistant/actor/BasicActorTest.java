package idv.hsiehpinghan.akkaassistant.actor;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import idv.hsiehpinghan.akkaassistant.message.Message_0;
import idv.hsiehpinghan.akkaassistant.message.Message_1;

public class BasicActorTest {
	private static ActorSystem actorSystem;
	private static final String MESSAGE_0 = "message_0";
	private static final String MESSAGE_1 = "message_1";

	@BeforeClass
	public static void setup() {
		actorSystem = ActorSystem.create();
	}

	@AfterClass
	public static void teardown() {
		TestKit.shutdownActorSystem(actorSystem);
		actorSystem = null;
	}

	@Test
	public void createReceive() throws IOException {
		final TestKit testKit_0 = new TestKit(actorSystem);
		final TestKit testKit_1 = new TestKit(actorSystem);
		final ActorRef basicActorRef = actorSystem.actorOf(BasicActor.props(testKit_0.getRef(), testKit_1.getRef()));
		basicActorRef.tell(new Message_0(MESSAGE_0), ActorRef.noSender());
		basicActorRef.tell(new Message_1(MESSAGE_1), ActorRef.noSender());
		Message_0 message_0 = testKit_0.expectMsgClass(Message_0.class);
		Assert.assertEquals(MESSAGE_0, message_0.getMessage());
		Message_1 message_1 = testKit_1.expectMsgClass(Message_1.class);
		Assert.assertEquals(MESSAGE_1, message_1.getMessage());
	}
}
