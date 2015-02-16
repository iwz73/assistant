package idv.hsiehpinghan.mailassistant.assistant;

import idv.hsiehpinghan.mailassistant.suit.TestngSuitSetting;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MailAssistantTest {
	private MailAssistant mailAssistant;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		mailAssistant = applicationContext.getBean(MailAssistant.class);
	}

	@Test
	public void sendMail() {
		String from = "daniel.hsiehpinghan@gmail.com";
		String to = "thank.hsiehpinghan@gmail.com";
		String subject = "subject";
		String msg = "message";
		mailAssistant.sendMail(from, to, subject, msg);
	}
}
