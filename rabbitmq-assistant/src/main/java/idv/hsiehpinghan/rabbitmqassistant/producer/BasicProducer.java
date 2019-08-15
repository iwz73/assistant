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

@Component
public class BasicProducer {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final Map<String, Object> ARGUMENTS = null;
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

	public void publish(String message) throws IOException, TimeoutException {
		connectionFactory.newConnection();
		try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
			channel.queueDeclare(queue, durable, exclusive, autoDelete, ARGUMENTS);
			String exchange = "";
			String routingKey = queue;
			BasicProperties props = null;
			byte[] body = message.getBytes("UTF-8");
			channel.basicPublish(exchange, routingKey, props, body);
			LOGGER.info("publish message({})", message);
		}
	}

}
