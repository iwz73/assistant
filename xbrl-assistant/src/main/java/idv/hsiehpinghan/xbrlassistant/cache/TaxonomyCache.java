package idv.hsiehpinghan.xbrlassistant.cache;

import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;
import jcx.xbrl.taxonomy.XbrlTaxonomy;

import org.springframework.stereotype.Component;

@Component
public class TaxonomyCache {
	XbrlTaxonomyVersion version;
	XbrlTaxonomy taxonomy;

	public XbrlTaxonomyVersion getVersion() {
		return version;
	}

	public void setVersion(XbrlTaxonomyVersion version) {
		this.version = version;
	}

	public XbrlTaxonomy getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(XbrlTaxonomy taxonomy) {
		this.taxonomy = taxonomy;
	}

}
