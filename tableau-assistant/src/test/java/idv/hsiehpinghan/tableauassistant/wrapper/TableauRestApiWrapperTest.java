package idv.hsiehpinghan.tableauassistant.wrapper;

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

@ContextConfiguration(classes = { SpringConfiguration.class })
public class TableauRestApiWrapperTest extends AbstractTestNGSpringContextTests {
	private String tableauEmailAddress;
	private String tableauPassword;
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
		tableauEmailAddress = environment.getRequiredProperty("tableauEmailAddress");
		tableauPassword = environment.getRequiredProperty("tableauPassword");
		contentUrl = environment.getRequiredProperty("tableau_site_name");
	}

	@AfterClass
	public void afterClass() {
		wrapper.signOut(token);
	}

	@Test
	public void signIn() throws Exception {
		SignInRequestBody signInRequestBody = SignInRequestBody.build(tableauEmailAddress, tableauPassword, contentUrl,
				null);
		SignInResponseBody signInResponseBody = wrapper.signIn(signInRequestBody);
		Assert.assertEquals(signInResponseBody.getCredentials().getSite().getId(),
				"a9a405cc-12b0-49ed-a6b1-e73562570af8");
		Assert.assertEquals(signInResponseBody.getCredentials().getSite().getContentUrl(), contentUrl);
		Assert.assertEquals(signInResponseBody.getCredentials().getUser().getId(),
				"ba3ed6b4-af85-4d2c-8f7e-be4db1fb6fe8");
		token = signInResponseBody.getCredentials().getToken();
	}

}
