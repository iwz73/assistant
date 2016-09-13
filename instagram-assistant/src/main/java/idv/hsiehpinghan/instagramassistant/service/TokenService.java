package idv.hsiehpinghan.instagramassistant.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import idv.hsiehpinghan.instagramassistant.utility.UrlUtility;

@Service
public class TokenService implements InitializingBean {
	private final String HTTPS_URL = "https://api.instagram.com/oauth/access_token";
	private final Pattern ACCESS_TOKENP_ATTERN = Pattern.compile("\"access_token\": \"([^\"]+)\"");
	private String clientId;
	private String clientSecret;
	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		clientId = environment.getRequiredProperty("clientId");
		clientSecret = environment.getRequiredProperty("clientSecret");
	}

	public String getAccessToken(String code, String redirectUri) throws IOException {
		String accessTokenJson = getAccessTokenJson(code, redirectUri);
		Matcher matcher = ACCESS_TOKENP_ATTERN.matcher(accessTokenJson);
		while (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	public String getAccessTokenJson(String code, String redirectUri) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("grant_type=");
		sb.append(URLEncoder.encode("authorization_code", "UTF-8"));
		sb.append("&client_id=");
		sb.append(URLEncoder.encode(clientId, "UTF-8"));
		sb.append("&client_secret=");
		sb.append(URLEncoder.encode(clientSecret, "UTF-8"));
		sb.append("&redirect_uri=");
		sb.append(URLEncoder.encode(redirectUri, "UTF-8"));
		sb.append("&code=");
		sb.append(URLEncoder.encode(code, "UTF-8"));
		final String PARAMETER = sb.toString();
		final Map<String, String> REQUEST_PROPERTIES = getRequestProperties(PARAMETER);
		return UrlUtility.getContent(HTTPS_URL, REQUEST_PROPERTIES, PARAMETER);
	}

	private Map<String, String> getRequestProperties(String parameter) {
		Map<String, String> map = new HashMap<>();
		map.put("Content-Type", "application/x-www-form-urlencoded");
		map.put("Content-length", String.valueOf(parameter.length()));
		return map;
	}

}
