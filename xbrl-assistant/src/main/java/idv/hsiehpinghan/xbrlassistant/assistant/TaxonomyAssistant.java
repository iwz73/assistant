package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import jcx.xbrl.taxonomy.XbrlTaxonomy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

@Component
public class TaxonomyAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private InstanceAssistant instanceAssistant;

	/**
	 * Get xbrl taxonomy.
	 * 
	 * @param instanceFile
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public XbrlTaxonomy getXbrlTaxonomy(File instanceFile)
			throws ParserConfigurationException, SAXException, IOException {
		XbrlTaxonomyVersion version = instanceAssistant
				.getXbrlTaxonomyVersion(instanceFile);
		switch (version) {
		case TIFRS_BASI_CR_2013_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsBasiCr20130331");
		case TIFRS_BASI_IR_2013_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsBasiIr20130331");
		case TIFRS_BD_CR_2013_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsBdCr20130331");
		case TIFRS_BD_ER_2013_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsBdEr20130331");
		case TIFRS_BD_IR_2013_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsBdIr20130331");
		case TIFRS_CI_CR_2013_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsCiCr20130331");
		case TIFRS_CI_IR_2013_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsCiIr20130331");
		case TIFRS_FH_2013_03_31:
			return (XbrlTaxonomy) applicationContext.getBean("tifrsFh20130331");
		case TIFRS_INS_CR_2013_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsInsCr20130331");
		case TIFRS_INS_IR_2013_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsInsIr20130331");
		case TIFRS_MIM_2013_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsMim20130331");
		case TIFRS_BASI_CR_2014_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsBasiCr20140331");
		case TIFRS_BASI_IR_2014_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsBasiIr20140331");
		case TIFRS_BD_CR_2014_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsBdCr20140331");
		case TIFRS_BD_ER_2014_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsBdEr20140331");
		case TIFRS_BD_IR_2014_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsBdIr20140331");
		case TIFRS_CI_CR_2014_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsCiCr20140331");
		case TIFRS_CI_IR_2014_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsCiIr20140331");
		case TIFRS_FH_2014_03_31:
			return (XbrlTaxonomy) applicationContext.getBean("tifrsFh20140331");
		case TIFRS_INS_CR_2014_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsInsCr20140331");
		case TIFRS_INS_IR_2014_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsInsIr20140331");
		case TIFRS_MIM_2014_03_31:
			return (XbrlTaxonomy) applicationContext
					.getBean("tifrsMim20140331");
		}
		throw new RuntimeException("XbrlTaxonomy version undefined");
	}

}
