package idv.hsiehpinghan.rabbitmqassistant.consumer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Component
public class RoutingConsumer {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final boolean AUTO_ACK = false;
	private final boolean MULTIPLE = false;
	private final String OTHER_ROUTING_KEY = "otherRoutingKey";
	private List<String> messages = new LinkedList<>();
	@Value("${rabbitmq.routing.exchange}")
	private String exchange;
	@Value("${rabbitmq.routing.routingKey}")
	private String routingKey;
	@Autowired
	private ConnectionFactory connectionFactory;

	public void consume() throws IOException, TimeoutException {
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(exchange, BuiltinExchangeType.DIRECT);
		String queue = channel.queueDeclare().getQueue();
		channel.queueBind(queue, exchange, routingKey);
		channel.queueBind(queue, exchange, OTHER_ROUTING_KEY);
		LOGGER.info("waiting for message.");
		DeliverCallback deliverCallback = (consumerTag, message) -> {
			String msg = new String(message.getBody(), "UTF-8");
			LOGGER.info("get message({}).", msg);
			messages.add(msg);
			channel.basicAck(message.getEnvelope().getDeliveryTag(), MULTIPLE);
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
