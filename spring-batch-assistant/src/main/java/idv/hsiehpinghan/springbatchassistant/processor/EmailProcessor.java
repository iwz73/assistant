package idv.hsiehpinghan.springbatchassistant.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;

public class EmailProcessor implements ItemProcessor<String, SimpleMailMessage> {
	private String from = "daniel.hsiehpinghan@gmail.com";
	private String to = "thank.hsiehpinghan@gmail.com";
	private String subject = "spring batch assistant";
	private String text = "message";

	@Override
	public SimpleMailMessage process(String email) throws Exception {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(from);
		msg.setTo(to);
		msg.setSubject(subject);
		msg.setText(text);
		return msg;
	}

}