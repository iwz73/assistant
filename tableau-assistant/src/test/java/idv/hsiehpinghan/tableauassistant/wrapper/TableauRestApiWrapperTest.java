package idv.hsiehpinghan.tableauassistant.wrapper;

import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.tableauassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.tableauassistant.model.SignInRequestBody;
import idv.hsiehpinghan.tableauassistant.model.SignInResponseBody;
import idv.hsiehpinghan.tableauassistant.utility.SSLUtility;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class TableauRestApiWrapperTest extends AbstractTestNGSpringContextTests {
	private String adminName;
	private String adminPassword;
	private String contentUrl;
	private String token;

	@Autowired
	private Environment environment;
	@Autowired
	private TableauRestApiWrapper wrapper;
	@Autowired
	@Qualifier("signInRequestBodyJAXBContext")
	private JAXBContext signInRequestJAXBContext;

	@BeforeClass
	public void beforeClass() {
		String tableauServerIp = environment.getRequiredProperty("tableau_server_ip");
		SSLUtility.skipSslCheck(tableauServerIp);
		adminName = environment.getRequiredProperty("admin_name");
		adminPassword = environment.getRequiredProperty("admin_password");
		contentUrl = environment.getRequiredProperty("site_name");
	}

	@AfterClass
	public void afterClass() {
		wrapper.signOut(token);
	}

	@Test
	public void signIn() throws Exception {
		SignInRequestBody signInRequestBody = SignInRequestBody.build(adminName, adminPassword, contentUrl, null);
		SignInResponseBody signInResponseBody = wrapper.signIn(signInRequestBody);
		Assert.assertEquals(signInResponseBody.getCredentials().getSite().getId(),
				"6186702e-6b73-4d01-888a-cc28a6a31d31");
		Assert.assertEquals(signInResponseBody.getCredentials().getSite().getContentUrl(), contentUrl);
		Assert.assertEquals(signInResponseBody.getCredentials().getUser().getId(),
				"3956d8ff-9ff5-4943-835f-8794208cb81c");
		token = signInResponseBody.getCredentials().getToken();
	}

}
