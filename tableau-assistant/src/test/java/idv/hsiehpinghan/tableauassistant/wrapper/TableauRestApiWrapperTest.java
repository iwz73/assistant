package idv.hsiehpinghan.tableauassistant.wrapper;

import javax.xml.bind.JAXBContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.tableauassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.tableauassistant.model.SignInRequest;
import idv.hsiehpinghan.tableauassistant.model.SignInResponse;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class TableauRestApiWrapperTest extends AbstractTestNGSpringContextTests {
	private String tableauEmailAddress;
	private String tableauPassword;
	private String contentUrl;

	@Autowired
	private Environment environment;
	@Autowired
	private TableauRestApiWrapper wrapper;
	@Autowired
	@Qualifier("signInRequestJAXBContext")
	private JAXBContext signInRequestJAXBContext;

	@BeforeClass
	public void beforeClass() {
		tableauEmailAddress = environment.getRequiredProperty("tableauEmailAddress");
		tableauPassword = environment.getRequiredProperty("tableauPassword");
		contentUrl = environment.getRequiredProperty("tableau_site_name");
	}

	@Test
	public void signIn() throws Exception {
		SignInRequest signInRequest = SignInRequest.build(tableauEmailAddress, tableauPassword, contentUrl);
		SignInResponse signInResponse = wrapper.signIn(signInRequest);
		Assert.assertEquals(signInResponse.getCredentials().getSite().getId(), "a9a405cc-12b0-49ed-a6b1-e73562570af8");
		Assert.assertEquals(signInResponse.getCredentials().getSite().getContentUrl(), contentUrl);
		Assert.assertEquals(signInResponse.getCredentials().getUser().getId(), "ba3ed6b4-af85-4d2c-8f7e-be4db1fb6fe8");
	}

}
