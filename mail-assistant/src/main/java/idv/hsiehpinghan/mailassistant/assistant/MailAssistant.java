package idv.hsiehpinghan.mailassistant.assistant;

import idv.hsiehpinghan.datatypeutility.utility.StringUtility;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailAssistant {
	public static final String UTF_8 = StringUtility.UTF_8;
	
	@Autowired
	private JavaMailSender sender;

	public void sendMail(String from, String to, String subject, String text,
			boolean isHtml) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, UTF_8);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, isHtml);
		sender.send(message);
	}

	public void sendMailWithAttachment(String from, String to, String subject,
			String text, boolean isHtml, Map<String, File> map)
			throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, isHtml);
		for (Entry<String, File> ent : map.entrySet()) {
			helper.addAttachment(ent.getKey(), ent.getValue());
		}
		sender.send(message);
	}

	public void sendMailWithInline(String from, String to, String subject,
			String text, boolean isHtml, Map<String, File> map) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, isHtml);
		for (Entry<String, File> ent : map.entrySet()) {
			helper.addInline(ent.getKey(), ent.getValue());
		}
		sender.send(message);
	}
}
