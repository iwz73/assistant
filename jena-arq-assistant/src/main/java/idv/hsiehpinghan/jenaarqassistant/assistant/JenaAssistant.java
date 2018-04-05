package idv.hsiehpinghan.jenaarqassistant.assistant;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.stereotype.Component;

@Component
public class JenaAssistant {
	public List<QuerySolution> query(InputStream inputStream, String queryString) {
		List<QuerySolution> querySolutions = new LinkedList<>();
		Model model = ModelFactory.createDefaultModel();
		Query query = QueryFactory.create(queryString);
		try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
			ResultSet resultSet = qexec.execSelect();
			while (resultSet.hasNext()) {
				QuerySolution querySolution = resultSet.next();
				querySolutions.add(querySolution);
			}
			return querySolutions;
		}
	}
}
