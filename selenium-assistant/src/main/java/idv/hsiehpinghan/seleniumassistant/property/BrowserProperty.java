package idv.hsiehpinghan.seleniumassistant.property;

public class BrowserProperty {
	private boolean javascriptEnabled = true;
	private String httpProxy;

	public BrowserProperty(boolean javascriptEnabled, String httpProxy) {
		super();
		this.javascriptEnabled = javascriptEnabled;
		this.httpProxy = httpProxy;
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
		this.httpProxy = httpProxy;
	}

}
