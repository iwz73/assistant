package idv.hsiehpinghan.xbrlassistant.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import jcx.xbrl.data.XbrlDocument;
import jcx.xbrl.taxonomy.XbrlTaxonomy;

import org.springframework.stereotype.Component;

@Component
public class InstanceCache {
	String filePath;
	XbrlDocument xbrlDocument;

	/**
	 * Get cached or new xbrlDocument.
	 * @param instanceFile
	 * @param taxonomy
	 * @return
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public XbrlDocument getXbrlDocument(File instanceFile, XbrlTaxonomy taxonomy) throws FileNotFoundException, Exception {
		String filePath = instanceFile.getAbsolutePath();
		if(filePath.equals(this.filePath)) {
			return this.xbrlDocument;
		}
		XbrlDocument xDoc = new XbrlDocument();
		xDoc.load(taxonomy, new FileInputStream(instanceFile));
		this.xbrlDocument = xDoc;
		this.filePath = filePath;
		return this.xbrlDocument;
	}
}
