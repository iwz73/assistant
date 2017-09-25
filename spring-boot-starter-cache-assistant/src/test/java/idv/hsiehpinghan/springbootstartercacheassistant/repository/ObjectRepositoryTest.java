package idv.hsiehpinghan.springbootstartercacheassistant.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import idv.hsiehpinghan.springbootstartercacheassistant.SpringBootStarterCacheAssistantApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringBootStarterCacheAssistantApplication.class })
public class ObjectRepositoryTest {
	@Autowired
	private ObjectRepository objectRepository;

	@Test
	public void getString() {
		Assert.assertEquals(0, objectRepository.getNewStringCount());
		objectRepository.getString("aaa");
		Assert.assertEquals(1, objectRepository.getNewStringCount());
		objectRepository.getString("aaa");
		Assert.assertEquals(1, objectRepository.getNewStringCount());
	}
}
