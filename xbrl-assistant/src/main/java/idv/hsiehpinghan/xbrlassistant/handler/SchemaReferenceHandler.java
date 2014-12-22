package idv.hsiehpinghan.xbrlassistant.handler;

import idv.hsiehpinghan.xbrlassistant.assistant.SaxParserBreakException;
import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;

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
			if ("tifrs-ci-cr-2013-03-31.xsd".equals(value)) {
				xbrlTaxonomyVersion = XbrlTaxonomyVersion.TIFRS_CI_CR_2013_03_31;
			}
			throw new SaxParserBreakException();
		}
	}

	public XbrlTaxonomyVersion getXbrlTaxonomyVersion() {
		return xbrlTaxonomyVersion;
	}

}
