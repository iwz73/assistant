package idv.hsiehpinghan.rabbitmqassistant.consumer;

import java.io.IOException;
import java.util.Map;
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
public class RpcConsumer {
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	private final long SLEEP_MILLISECONDS = 5 * 1000;
	private final Map<String, Object> ARGUMENTS = null;
	private final boolean AUTO_ACK = false;
	private final boolean MULTIPLE = false;
	private final int PREFETCH_COUNT = 1;
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

	public void consume() throws IOException, TimeoutException {
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(queue, durable, exclusive, autoDelete, ARGUMENTS);
		channel.basicQos(PREFETCH_COUNT);
		Object monitor = new Object();
		DeliverCallback deliverCallback = (consumerTag, delivery) -> {
			BasicProperties props = new BasicProperties.Builder()
					.correlationId(delivery.getProperties().getCorrelationId()).build();
			String response = null;
			try {
				String msg = new String(delivery.getBody(), "UTF-8");
				LOGGER.info("get message({}).", msg);
				response = doSomething(msg);
				String exchange = "";
				String routingKey = delivery.getProperties().getReplyTo();
				byte[] body = response.getBytes("UTF-8");
				channel.basicPublish(exchange, routingKey, props, body);
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), MULTIPLE);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} finally {
				// RabbitMq consumer worker thread notifies the RPC server owner thread
				synchronized (monitor) {
					monitor.notify();
				}
			}
		};
		CancelCallback cancelCallback = consumerTag -> {
		};
		channel.basicConsume(queue, AUTO_ACK, deliverCallback, cancelCallback);
		// Wait and be prepared to consume the message from RPC client.
		while (true) {
			synchronized (monitor) {
				try {
					monitor.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String doSomething(String message) throws InterruptedException {
		Thread.sleep(SLEEP_MILLISECONDS);
		return String.format("Hello, %s.", message);
	}

}
