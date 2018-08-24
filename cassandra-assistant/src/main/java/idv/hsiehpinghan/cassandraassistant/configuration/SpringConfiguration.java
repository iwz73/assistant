package idv.hsiehpinghan.cassandraassistant.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Cluster.Builder;

@PropertySource("classpath:/cassandra_assistant.property")
@Configuration("cassandraAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.cassandraassistant" })
public class SpringConfiguration {
//    public Cluster cluster() {
//    	Builder b = Cluster.builder().
//    			.addContactPoint(node);
//    }
//
//    public Session session() {
//    	
//    }
}
