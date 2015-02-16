package idv.hsiehpinghan.mailassistant.assistant;

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
	@Autowired
	private JavaMailSender sender;

	public void sendMail(String from, String to, String subject, String text)
			throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
		sender.send(message);
	}

	public void sendMailWithAttachment(String from, String to, String subject,
			String text, Map<String, File> map) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);
		for (Entry<String, File> ent : map.entrySet()) {
			helper.addAttachment(ent.getKey(), ent.getValue());
		}
		sender.send(message);
	}

	public void sendMailWithInline(String from, String to, String subject,
			String text, Map<String, File> map) throws MessagingException {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, true);
		for (Entry<String, File> ent : map.entrySet()) {
			helper.addInline(ent.getKey(), ent.getValue());
		}
		sender.send(message);
	}
}
