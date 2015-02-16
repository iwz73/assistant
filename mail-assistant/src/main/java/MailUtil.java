import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

public class MailUtil {
	static {
		Authenticator auth = new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("daniel.hsiehpinghan@gmail.com", "3isagesjdje");	// 請修改，填入email登人帳號及密碼
			}
		};
		authenticator = auth;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");	// 請修改
		props.put("mail.smtp.port", "587");	// 請修改
		props.put("mail.debug", "false");
		properties = props;
	}
	private static final Properties properties;
	private static final Authenticator authenticator;
	
	public static boolean sendMail(String fromEmail, Set<String> toEmails, String subject, String text) {
		Session session = Session.getInstance(properties, authenticator);
		try {
			// 轉換email
			InternetAddress[] addressTo = new InternetAddress[toEmails.size()];
			int i = 0;
			for(String toEmail : toEmails) {
				addressTo[i] = new InternetAddress(toEmail);
				++i;
			}
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO, addressTo);
			message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B"));
			message.setText(text);
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static void main(String[] args) {
		String fromEmail = "daniel.hsiehpinghan@gmail.com";
		Set<String> toEmails = new HashSet<String>();
		toEmails.add("thank.hsiehpinghan@gmail.com");
		String subject = "測試標題";
		String text = "測試內文";
		System.out.println(MailUtil.sendMail(fromEmail, toEmails, subject, text));
	}
}
