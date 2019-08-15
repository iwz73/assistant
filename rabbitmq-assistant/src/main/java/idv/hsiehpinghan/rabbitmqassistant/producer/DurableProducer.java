package idv.hsiehpinghan.rabbitmqassistant.producer;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

@Component
public class DurableProducer {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final Map<String, Object> ARGUMENTS = null;
	@Value("${rabbitmq.durable.queue}")
	private String queue;
	@Value("${rabbitmq.durable.durable}")
	private boolean durable;
	@Value("${rabbitmq.durable.exclusive}")
	private boolean exclusive;
	@Value("${rabbitmq.durable.autoDelete}")
	private boolean autoDelete;
	@Autowired
	private ConnectionFactory connectionFactory;

	public void publish(String message) throws IOException, TimeoutException {
		connectionFactory.newConnection();
		try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
			channel.queueDeclare(queue, durable, exclusive, autoDelete, ARGUMENTS);
			String exchange = "";
			String routingKey = queue;
			BasicProperties props = MessageProperties.PERSISTENT_TEXT_PLAIN;
			byte[] body = message.getBytes("UTF-8");
			channel.basicPublish(exchange, routingKey, props, body);
			LOGGER.info("publish message({})", message);
		}
	}

}
