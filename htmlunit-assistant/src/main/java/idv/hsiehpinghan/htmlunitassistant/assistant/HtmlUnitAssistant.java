package idv.hsiehpinghan.htmlunitassistant.assistant;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

@Component
public class HtmlUnitAssistant {
	private WebClient webClient = new WebClient();

	public HtmlPage post(String url, List<NameValuePair> requestParameters)
			throws FailingHttpStatusCodeException, IOException {
		WebRequest requeset = new WebRequest(new URL(url), HttpMethod.POST);
		requeset.setRequestParameters(requestParameters);
		return webClient.getPage(requeset);
	}

	public void getWebRequest() throws FailingHttpStatusCodeException,
			IOException, InterruptedException {
		WebRequest requeset = new WebRequest(new URL(
				"http://mops.twse.com.tw/mops/web/t51sb01"));
		HtmlPage page = webClient.getPage(requeset);
		HtmlButtonInput input = null;
		for (int i = 0; i < 20; i++) {
			input = (HtmlButtonInput) page
					.getFirstByXPath("//*[@id='search_bar1']/div/input");
			if (input == null) {
				break;
			}
			synchronized (page) {
				page.wait(500);
			}
		}
		 Page p = input.click();
		 WebRequest r = p.getWebResponse().getWebRequest();
		 System.err.println(r.getUrl());
		 for(NameValuePair pair : r.getRequestParameters()) {
			 System.err.println(pair.getName() + " : " + pair.getValue());
		 }
		 
	}
}
