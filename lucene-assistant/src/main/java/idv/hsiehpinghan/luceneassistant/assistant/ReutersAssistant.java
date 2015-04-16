package idv.hsiehpinghan.luceneassistant.assistant;

import org.apache.lucene.benchmark.utils.ExtractReuters;
import org.springframework.stereotype.Component;

@Component
public class ReutersAssistant {
	public String extractReuters(String reuters21578DirectoryPath,
			String outputDirectoryPath) {
		String[] args = new String[] { reuters21578DirectoryPath,
				outputDirectoryPath };
		ExtractReuters.main(args);
		return outputDirectoryPath;
	}
}
