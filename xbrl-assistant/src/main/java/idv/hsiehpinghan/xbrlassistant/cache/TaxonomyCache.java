package idv.hsiehpinghan.xbrlassistant.cache;

import java.io.File;

import idv.hsiehpinghan.resourceutility.utility.ResourceUtility;
import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;
import jcx.xbrl.taxonomy.XbrlTaxonomy;

import org.springframework.stereotype.Component;

@Component
public class TaxonomyCache {
	XbrlTaxonomyVersion version;
	XbrlTaxonomy taxonomy;

	/**
	 * Get cached or new taxonomy.
	 * @param version
	 * @param taxonomyPath
	 * @return
	 * @throws Exception
	 */
	public XbrlTaxonomy getTaxonomy(XbrlTaxonomyVersion version,
			String taxonomyPath) throws Exception {
		if (this.version != version) {
			File taxonomyFile = ResourceUtility.getFileResource(taxonomyPath);
			this.taxonomy = new XbrlTaxonomy(taxonomyFile.getParent(),
					taxonomyFile.getName());
			this.version = version;
		}
		return taxonomy;
	}

}
