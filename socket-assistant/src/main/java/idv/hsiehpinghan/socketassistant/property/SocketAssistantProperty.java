package idv.hsiehpinghan.socketassistant.property;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SocketAssistantProperty implements InitializingBean {
	private Integer echoServerPort;

	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		processEchoServerPort();
	}

	public Integer getEchoServerPort() {
		return echoServerPort;
	}

	private void processEchoServerPort() {
		String pEchoServerPort = "socket_assistant_echoServer_port";
		echoServerPort = environment.getRequiredProperty(pEchoServerPort,
				Integer.class);
	}

}
