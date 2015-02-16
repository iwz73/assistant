package idv.hsiehpinghan.mailassistant.assistant;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailAssistant {
	@Autowired
	private JavaMailSender sender;
	
	public void sendMail(String from, String to, String subject, String msg) {
		MimeMessage message = sender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg);

		}
		catch (MessagingException e) {
			throw new MailParseException(e);
		}

		sender.send(message);
	}
}
