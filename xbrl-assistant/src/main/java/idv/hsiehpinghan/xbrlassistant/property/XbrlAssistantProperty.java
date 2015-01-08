package idv.hsiehpinghan.xbrlassistant.property;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class XbrlAssistantProperty implements InitializingBean {
	private String taxonomyDir;

	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		String pTaxonomyDir = "xbrl-assistant.taxonomy_dir";
		taxonomyDir = environment.getProperty(pTaxonomyDir);
		if (taxonomyDir == null) {
			throw new RuntimeException(pTaxonomyDir + " not set !!!");
		}
	}

	public String getTaxonomyDir() {
		return taxonomyDir;
	}

}
