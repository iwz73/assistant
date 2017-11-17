package idv.hsiehpinghan.springassistant.pool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springassistant.configuration.TargetSourceSpringConfiguration;
import idv.hsiehpinghan.springassistant.pool.object.TestPooledObject;

@ContextConfiguration(classes = { TargetSourceSpringConfiguration.class })
public class TestPooledObjectPoolTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private TestPooledObjectPool testPooledObjectPool;
	private TestPooledObject testPooledObject_0;
	private TestPooledObject testPooledObject_1;

	@Test
	public void getTarget() throws Exception {
		testPooledObject_0 = (TestPooledObject) testPooledObjectPool.getTarget();
		testPooledObject_0.setName("name_0");
		Assert.assertEquals(1, testPooledObject_0.getActivateCount());
	}

	@Test(dependsOnMethods = { "getTarget" })
	public void releaseTarget() throws Exception {
		testPooledObjectPool.releaseTarget(testPooledObject_0);
	}

	@Test(dependsOnMethods = { "releaseTarget" })
	public void getTargetAgain() throws Exception {
		testPooledObject_1 = (TestPooledObject) testPooledObjectPool.getTarget();
		testPooledObject_1.setName("name_1");
		Assert.assertTrue(testPooledObject_0 == testPooledObject_1);
	}
}
