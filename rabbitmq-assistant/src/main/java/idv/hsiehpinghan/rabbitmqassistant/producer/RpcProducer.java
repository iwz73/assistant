package idv.hsiehpinghan.rabbitmqassistant.producer;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

@Component
public class RpcProducer {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final Map<String, Object> ARGUMENTS = null;
	private final boolean AUTO_ACK = false;
	private final boolean MULTIPLE = false;
	@Value("${rabbitmq.rpc.queue}")
	private String queue;
	@Value("${rabbitmq.rpc.durable}")
	private boolean durable;
	@Value("${rabbitmq.rpc.exclusive}")
	private boolean exclusive;
	@Value("${rabbitmq.rpc.autoDelete}")
	private boolean autoDelete;
	@Autowired
	private ConnectionFactory connectionFactory;

	public String publish(String message) throws IOException, TimeoutException, InterruptedException {
		connectionFactory.newConnection();
		try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
			channel.queueDeclare(queue, durable, exclusive, autoDelete, ARGUMENTS);
			String exchange = "";
			String replyQueue = channel.queueDeclare().getQueue();
			String correlationId = UUID.randomUUID().toString();
			BasicProperties props = new BasicProperties.Builder().correlationId(correlationId).replyTo(replyQueue)
					.build();
			channel.basicPublish(exchange, queue, props, message.getBytes("UTF-8"));
			LOGGER.info("publish message({})", message);
			BlockingQueue<String> response = new ArrayBlockingQueue<>(1);
			DeliverCallback deliverCallback = (consumerTag, returnMessage) -> {
				if (returnMessage.getProperties().getCorrelationId().equals(correlationId) == false) {
					return;
				}
				String msg = new String(returnMessage.getBody(), "UTF-8");
				LOGGER.info("get message({}).", msg);
				response.offer(new String(returnMessage.getBody(), "UTF-8"));
				channel.basicAck(returnMessage.getEnvelope().getDeliveryTag(), MULTIPLE);
			};
			CancelCallback cancelCallback = consumerTag -> {
			};
			String consumerTag = channel.basicConsume(replyQueue, AUTO_ACK, deliverCallback, cancelCallback);
			String result = response.take();
			channel.basicCancel(consumerTag);
			return result;
		}
	}

}
