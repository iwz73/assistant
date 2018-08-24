package idv.hsiehpinghan.cassandraassistant.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@Component
public class CassandraAssistant {
	@Autowired
    private Cluster cluster;
	@Autowired
    private Session session;
}
