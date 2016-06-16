package idv.hsiehpinghan.cxfassistant.configuration;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class SpringConfigurationTest extends AbstractTestNGSpringContextTests {

  @Test
  public void endpoint() {
//    throw new RuntimeException("Test not implemented");
	  try {
		Thread.sleep(100000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
