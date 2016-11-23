package idv.hsiehpinghan.mailassistant.assistant;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.mailassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class MailAssistantTest extends AbstractTestNGSpringContextTests {
	private String from = "daniel.hsiehpinghan@gmail.com";
	private String to = "thank.hsiehpinghan@gmail.com";
	private String subject = "subject";
	private String msg = "message";
	@Autowired
	private MailAssistant mailAssistant;

	// @Test
	public void sendMail() throws MessagingException {
		mailAssistant.sendMail(from, to, subject, msg, false);
	}

	// @Test
	public void sendMailWithAttachment() throws MessagingException, IOException {
		Map<String, File> map = new HashMap<String, File>(2);
		File jpeg = new ClassPathResource("file/jpeg.jpeg").getFile();
		map.put("jpeg", jpeg);
		File xls = new ClassPathResource("file/xls.xls").getFile();
		map.put("xls", xls);
		mailAssistant.sendMailWithAttachment(from, to, subject, msg, false, map);
	}

	@Test
	public void sendMailWithInline() throws MessagingException, IOException {
		Map<String, File> map = new HashMap<String, File>(1);
		StringBuilder sb = new StringBuilder();
		sb.append("<html> ");
		sb.append("<body> ");
		sb.append("<img src='cid:jpeg'> ");
		sb.append("</body> ");
		sb.append("</html> ");
		File jpeg = new ClassPathResource("file/jpeg.jpeg").getFile();
		map.put("jpeg", jpeg);
		mailAssistant.sendMailWithInline(from, to, subject, sb.toString(), true, map);
	}
}
