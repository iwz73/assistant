package idv.hsiehpinghan.springbootstarterwebassistant.property;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ConfigurationPropertyTest {
	@Autowired
	private ConfigurationProperty configurationProperty;

	@Test
	public void getProperty() {
		Assert.assertEquals("configuration_property", configurationProperty.getProperty());
	}
}
