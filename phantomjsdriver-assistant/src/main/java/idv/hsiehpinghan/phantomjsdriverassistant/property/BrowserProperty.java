package idv.hsiehpinghan.phantomjsdriverassistant.property;

public class BrowserProperty {
	private boolean javascriptEnabled = true;
	private String httpProxy;

	public BrowserProperty(boolean javascriptEnabled, String httpProxy) {
		super();
		setJavascriptEnabled(javascriptEnabled);
		setHttpProxy(httpProxy);
	}

	public boolean isJavascriptEnabled() {
		return javascriptEnabled;
	}

	public void setJavascriptEnabled(boolean javascriptEnabled) {
		this.javascriptEnabled = javascriptEnabled;
	}

	public String getHttpProxy() {
		return httpProxy;
	}

	public void setHttpProxy(String httpProxy) {
		if (httpProxy == null) {
			this.httpProxy = ":";
		} else {
			this.httpProxy = httpProxy;
		}
	}

}
