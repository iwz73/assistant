package idv.hsiehpinghan.mailassistant.assistant;

import idv.hsiehpinghan.mailassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.testutility.utility.SystemResourceUtility;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MailAssistantTest {
	private MailAssistant mailAssistant;
	private String from = "daniel.hsiehpinghan@gmail.com";
	private String to = "thank.hsiehpinghan@gmail.com";
	private String subject = "subject";
	private String msg = "message";

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		mailAssistant = applicationContext.getBean(MailAssistant.class);
	}

//	@Test
	public void sendMail() throws MessagingException {
		mailAssistant.sendMail(from, to, subject, msg);
	}

//	@Test
	public void sendMailWithAttachment() throws MessagingException {
		Map<String, File> map = new HashMap<String, File>(2);
		File jpeg = SystemResourceUtility
				.getFileResource("file/jpeg.jpeg");
		map.put("jpeg", jpeg);
		File xls = SystemResourceUtility
				.getFileResource("file/xls.xls");
		map.put("xls", xls);
		mailAssistant.sendMailWithAttachment(from, to, subject, msg, map);
	}
	
	@Test
	public void sendMailWithInline() throws MessagingException {
		Map<String, File> map = new HashMap<String, File>(1);
		StringBuilder sb = new StringBuilder();
		sb.append("<html> ");
		sb.append("<body> ");
		sb.append("<img src='cid:jpeg'> ");
		sb.append("</body> ");
		sb.append("</html> ");
		File jpeg = SystemResourceUtility
				.getFileResource("file/jpeg.jpeg");
		map.put("jpeg", jpeg);
		mailAssistant.sendMailWithInline(from, to, subject, sb.toString(), map);
	}
}
