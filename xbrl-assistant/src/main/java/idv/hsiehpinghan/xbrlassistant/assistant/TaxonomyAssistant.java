package idv.hsiehpinghan.xbrlassistant.assistant;

import idv.hsiehpinghan.xbrlassistant.cache.TaxonomyCache;
import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;

import java.io.File;

import jcx.xbrl.taxonomy.XbrlTaxonomy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TaxonomyAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private InstanceAssistant instanceAssistant;
	@Autowired
	private TaxonomyCache cache;

	/**
	 * Get xbrl taxonomy.
	 * 
	 * @param instanceFile
	 * @return
	 * @throws Exception
	 */
	public XbrlTaxonomy getXbrlTaxonomy(File instanceFile) throws Exception {
		XbrlTaxonomyVersion version = instanceAssistant
				.getXbrlTaxonomyVersion(instanceFile);
		return cache.getTaxonomy(version, getTaxonomyPath(version));
	}

	private String getTaxonomyPath(XbrlTaxonomyVersion version) {
		switch (version) {
		case TIFRS_BASI_CR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BASI/CR/tifrs-basi-cr-2013-03-31.xsd";
		case TIFRS_BASI_IR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BASI/IR/tifrs-basi-ir-2013-03-31.xsd";
		case TIFRS_BD_CR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BD/CR/tifrs-bd-cr-2013-03-31.xsd";
		case TIFRS_BD_ER_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BD/ER/tifrs-bd-er-2013-03-31.xsd";
		case TIFRS_BD_IR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/BD/IR/tifrs-bd-ir-2013-03-31.xsd";
		case TIFRS_CI_CR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2013-03-31.xsd";
		case TIFRS_CI_IR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/CI/IR/tifrs-ci-ir-2013-03-31.xsd";
		case TIFRS_FH_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/FH/tifrs-fh-2013-03-31.xsd";
		case TIFRS_INS_CR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/INS/CR/tifrs-ins-cr-2013-03-31.xsd";
		case TIFRS_INS_IR_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/INS/IR/tifrs-ins-ir-2013-03-31.xsd";
		case TIFRS_MIM_2013_03_31:
			return "xbrl-taxonomy/tifrs-20130331/XBRL_TW_Entry_Points/MIM/tifrs-mim-2013-03-31.xsd";
		case TIFRS_BASI_CR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BASI/CR/tifrs-basi-cr-2014-03-31.xsd";
		case TIFRS_BASI_IR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BASI/IR/tifrs-basi-ir-2014-03-31.xsd";
		case TIFRS_BD_CR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BD/CR/tifrs-bd-cr-2014-03-31.xsd";
		case TIFRS_BD_ER_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BD/ER/tifrs-bd-er-2014-03-31.xsd";
		case TIFRS_BD_IR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/BD/IR/tifrs-bd-ir-2014-03-31.xsd";
		case TIFRS_CI_CR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/CI/CR/tifrs-ci-cr-2014-03-31.xsd";
		case TIFRS_CI_IR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/CI/IR/tifrs-ci-ir-2014-03-31.xsd";
		case TIFRS_FH_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/FH/tifrs-fh-2014-03-31.xsd";
		case TIFRS_INS_CR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/INS/CR/tifrs-ins-cr-2014-03-31.xsd";
		case TIFRS_INS_IR_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/INS/IR/tifrs-ins-ir-2014-03-31.xsd";
		case TIFRS_MIM_2014_03_31:
			return "xbrl-taxonomy/tifrs-20140331/XBRL_TW_Entry_Points/MIM/tifrs-mim-2014-03-31.xsd";
		}
		throw new RuntimeException("XbrlTaxonomy version undefined");
	}
}
