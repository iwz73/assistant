package idv.hsiehpinghan.rabbitmqassistant.consumer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Component
public class BasicConsumer {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final Map<String, Object> ARGUMENTS = null;
	private final boolean AUTO_ACK = true;
	private List<String> messages = new LinkedList<>();
	@Value("${rabbitmq.basic.queue}")
	private String queue;
	@Value("${rabbitmq.basic.durable}")
	private boolean durable;
	@Value("${rabbitmq.basic.exclusive}")
	private boolean exclusive;
	@Value("${rabbitmq.basic.autoDelete}")
	private boolean autoDelete;
	@Autowired
	private ConnectionFactory connectionFactory;

	public void consume() throws IOException, TimeoutException {
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(queue, durable, exclusive, autoDelete, ARGUMENTS);
		LOGGER.info("waiting for message.");
		DeliverCallback deliverCallback = (consumerTag, message) -> {
			String msg = new String(message.getBody(), "UTF-8");
			LOGGER.info("get message({}).", msg);
			messages.add(msg);
		};
		CancelCallback cancelCallback = consumerTag -> {
		};
		channel.basicConsume(queue, AUTO_ACK, deliverCallback, cancelCallback);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
