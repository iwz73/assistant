package idv.hsiehpinghan.xbrlassistant.configuration;

import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;

import java.io.File;

import jcx.xbrl.taxonomy.XbrlTaxonomy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration("xbrlAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.xbrlassistant.assistant" })
public class SpringConfiguration {
//	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}

	@Bean
	public XbrlTaxonomy tifrsCiCr20130331() throws Exception{
		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2013-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}
	
	
}
