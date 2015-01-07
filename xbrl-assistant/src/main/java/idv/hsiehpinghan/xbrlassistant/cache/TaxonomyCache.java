package idv.hsiehpinghan.xbrlassistant.cache;

import idv.hsiehpinghan.xbrlassistant.enumeration.XbrlTaxonomyVersion;

import java.io.File;

import jcx.xbrl.taxonomy.XbrlTaxonomy;

import org.springframework.stereotype.Component;

@Component
public class TaxonomyCache {
	XbrlTaxonomyVersion version;
	XbrlTaxonomy taxonomy;

	/**
	 * Get cached or new taxonomy.
	 * 
	 * @param version
	 * @param taxonomyPath
	 * @return
	 * @throws Exception
	 */
	public XbrlTaxonomy getTaxonomy(File taxonomyDir,
			XbrlTaxonomyVersion version) throws Exception {
		if (this.version != version) {
			File taxonomyFile = new File(taxonomyDir.getAbsolutePath()
					+ version.getPath());
			this.taxonomy = new XbrlTaxonomy(taxonomyFile.getParent(),
					taxonomyFile.getName());
			this.version = version;
		}
		return taxonomy;
	}

}
