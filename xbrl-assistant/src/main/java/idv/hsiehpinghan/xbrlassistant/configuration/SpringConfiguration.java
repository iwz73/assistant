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
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public XbrlTaxonomy tifrsBasiCr20130331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BASI/CR/tifrs-basi-cr-2013-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsBasiIr20130331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BASI/IR/tifrs-basi-ir-2013-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsBdCr20130331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BD/CR/tifrs-bd-cr-2013-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsBdEr20130331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BD/ER/tifrs-bd-er-2013-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsBdIr20130331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BD/IR/tifrs-bd-ir-2013-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsCiCr20130331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2013-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsCiIr20130331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/CI/IR/tifrs-ci-ir-2013-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsFh20130331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/FH/tifrs-fh-2013-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsInsCr20130331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/INS/CR/tifrs-ins-cr-2013-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsInsIr20130331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/INS/IR/tifrs-ins-ir-2013-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsMim20130331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/MIM/tifrs-mim-2013-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsBasiCr20140331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BASI/CR/tifrs-basi-cr-2014-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsBasiIr20140331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BASI/IR/tifrs-basi-ir-2014-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsBdCr20140331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BD/CR/tifrs-bd-cr-2014-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsBdEr20140331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BD/ER/tifrs-bd-er-2014-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsBdIr20140331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BD/IR/tifrs-bd-ir-2014-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsCiCr20140331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2014-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsCiIr20140331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/CI/IR/tifrs-ci-ir-2014-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsFh20140331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/FH/tifrs-fh-2014-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsInsCr20140331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/INS/CR/tifrs-ins-cr-2014-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsInsIr20140331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/INS/IR/tifrs-ins-ir-2014-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

	@Bean
	public XbrlTaxonomy tifrsMim20140331() throws Exception {
		String taxonomyPath = "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/MIM/tifrs-mim-2014-03-31.xsd";
		File taxonomy = ResourceUtility.getFileResource(taxonomyPath);
		return new XbrlTaxonomy(taxonomy.getParent(), taxonomy.getName());
	}

}
