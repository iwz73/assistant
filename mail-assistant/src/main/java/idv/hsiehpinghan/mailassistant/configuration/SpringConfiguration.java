package idv.hsiehpinghan.mailassistant.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration("mailAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.mailassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private Environment environment;

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setUsername(getUsername());
		javaMailSender.setPassword(getPassword());
		javaMailSender.setJavaMailProperties(getProperties());
		return javaMailSender;
	}

	private String getUsername() {
		String usernameProp = "mail_assistant_username";
		String username = environment.getProperty(usernameProp);
		if (username == null) {
			throw new RuntimeException(usernameProp + " not set !!!");
		}
		return username;
	}

	private String getPassword() {
		String passwordProp = "mail_assistant_password";
		String password = environment.getProperty(passwordProp);
		if (password == null) {
			throw new RuntimeException(passwordProp + " not set !!!");
		}
		return password;
	}

	private Properties getProperties() {
		Properties prop = new Properties();
		prop.setProperty("mail.smtp.auth", "true");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		return prop;
	}
}