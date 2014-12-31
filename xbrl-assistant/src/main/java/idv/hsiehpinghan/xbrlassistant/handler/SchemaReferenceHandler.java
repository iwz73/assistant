package idv.hsiehpinghan.xbrlassistant.handler;

import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;
import idv.hsiehpinghan.xbrlassistant.exception.SaxParserBreakException;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SchemaReferenceHandler extends DefaultHandler {
	private static final String ELEMENT_QNAME = "link:schemaRef";
	private static final String ATTRIBUTE_QNAME = "xlink:href";

	private XbrlTaxonomyVersion xbrlTaxonomyVersion;

	/**
	 * Find schema and set to xbrlTaxonomyVersion.
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SaxParserBreakException {
		if (ELEMENT_QNAME.equals(qName)) {
			String value = attributes.getValue(ATTRIBUTE_QNAME);
			switch (value) {
			case "tifrs-basi-cr-2013-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_BASI_CR_2013_03_31;
				break;
			case "tifrs-basi-ir-2013-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_BASI_IR_2013_03_31;
				break;
			case "tifrs-bd-cr-2013-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_BD_CR_2013_03_31;
				break;
			case "tifrs-bd-er-2013-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_BD_ER_2013_03_31;
				break;
			case "tifrs-bd-ir-2013-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_BD_IR_2013_03_31;
				break;
			case "tifrs-ci-cr-2013-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_CI_CR_2013_03_31;
				break;
			case "tifrs-ci-ir-2013-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_CI_IR_2013_03_31;
				break;
			case "tifrs-fh-2013-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_FH_2013_03_31;
				break;
			case "tifrs-ins-cr-2013-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_INS_CR_2013_03_31;
				break;
			case "tifrs-ins-ir-2013-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_INS_IR_2013_03_31;
				break;
			case "tifrs-mim-2013-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_MIM_2013_03_31;
				break;
			case "tifrs-basi-cr-2014-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_BASI_CR_2014_03_31;
				break;
			case "tifrs-basi-ir-2014-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_BASI_IR_2014_03_31;
				break;
			case "tifrs-bd-cr-2014-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_BD_CR_2014_03_31;
				break;
			case "tifrs-bd-er-2014-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_BD_ER_2014_03_31;
				break;
			case "tifrs-bd-ir-2014-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_BD_IR_2014_03_31;
				break;
			case "tifrs-ci-cr-2014-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_CI_CR_2014_03_31;
				break;
			case "tifrs-ci-ir-2014-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_CI_IR_2014_03_31;
				break;
			case "tifrs-fh-2014-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_FH_2014_03_31;
				break;
			case "tifrs-ins-cr-2014-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_INS_CR_2014_03_31;
				break;
			case "tifrs-ins-ir-2014-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_INS_IR_2014_03_31;
				break;
			case "tifrs-mim-2014-03-31.xsd":
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_MIM_2014_03_31;
				break;
			default:
				throw new RuntimeException("Not implement !!!");
			}
			throw new SaxParserBreakException();
		}
	}

	public XbrlTaxonomyVersion getXbrlTaxonomyVersion() {
		return xbrlTaxonomyVersion;
	}

}
