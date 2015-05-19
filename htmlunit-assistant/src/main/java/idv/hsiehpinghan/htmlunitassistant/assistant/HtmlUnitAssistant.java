package idv.hsiehpinghan.htmlunitassistant.assistant;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class HtmlUnitAssistant {
	public void test() throws Exception {
		try (WebClient webClient = new WebClient()) {
//			URL url = new URL("http://www.momoshop.com.tw/goods/GoodsDetail.jsp?i_code=3149105&str_category_code=1114700073&mdiv=1114700073-bt_9_002_01&ctype=B&Area=DgrpCategory");
			URL url = new URL("https://tw.user.mall.yahoo.com/store_admin/view/amountPromo?promotion_id=1756142&sid=bigeasy");
			
			WebRequest requeset = new WebRequest(url, HttpMethod.HEAD);
			Page page = webClient.getPage(requeset);
			List<NameValuePair> headers = page.getWebResponse().getResponseHeaders();
			for(NameValuePair header : headers) {
				System.err.println(header.getName() + " : " + header.getValue());
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		HtmlUnitAssistant a = new HtmlUnitAssistant();
		a.test();
	}
}
