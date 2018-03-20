package idv.hsiehpinghan.xmltordfassistant.test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.xmltordfassistant.configuration.SpringConfiguration;
import no.acando.xmltordf.Builder;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BuilderTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	public void convertToStream() throws Exception {
		InputStream inputStream = resourceLoader.getResource("classpath:/tifrs-fr0-m1-ci-cr-1101-2013Q1.xml")
				.getInputStream();
		File file = new File("/tmp/convertToStream.ttl");
		OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
		Builder.getAdvancedBuilderStream().build().convertToStream(inputStream, outputStream);
		Assert.assertEquals(1961317, file.length());
	}
}
