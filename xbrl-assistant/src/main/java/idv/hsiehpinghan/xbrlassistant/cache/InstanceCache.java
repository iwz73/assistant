package idv.hsiehpinghan.xbrlassistant.cache;

import jcx.xbrl.data.XbrlDocument;

import org.springframework.stereotype.Component;

@Component
public class InstanceCache {
	String filePath;
	XbrlDocument xbrlDocument;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public XbrlDocument getXbrlDocument() {
		return xbrlDocument;
	}

	public void setXbrlDocument(XbrlDocument xbrlDocument) {
		this.xbrlDocument = xbrlDocument;
	}

}
