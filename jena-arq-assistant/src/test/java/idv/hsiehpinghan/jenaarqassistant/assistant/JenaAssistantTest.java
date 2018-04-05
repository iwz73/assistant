package idv.hsiehpinghan.jenaarqassistant.assistant;

import java.io.InputStream;
import java.util.List;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.jenaarqassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class JenaAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JenaAssistant assistant;
	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	public void query() throws Exception {
		// @formatter:off
		String queryString = 
			"PREFIX ab: <http://learningsparql.com/ns/addressbook#>\n" + 
			"SELECT ?craigEmail\n" + 
			"WHERE\n" + 
			"{ ab:craig ab:email ?craigEmail . }";
		// @formatter:on
		try (InputStream inputStream = resourceLoader.getResource("classpath:/tutle.ttl").getInputStream();) {
			List<QuerySolution> querySolutions= assistant.query(inputStream, queryString);
			System.err.println(querySolutions.size());
			for(QuerySolution querySolution : querySolutions) {
				System.err.println(querySolution);
			}
		}

	}
}
