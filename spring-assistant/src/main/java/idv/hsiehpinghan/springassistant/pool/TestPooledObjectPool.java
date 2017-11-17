package idv.hsiehpinghan.springassistant.pool;

import org.apache.commons.pool2.PooledObject;
import org.springframework.aop.target.CommonsPool2TargetSource;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.springassistant.pool.object.TestPooledObject;

@Component
public class TestPooledObjectPool extends CommonsPool2TargetSource {
	private static final String targetBeanName = "testPooledObject";
	private int MAX_IDLE = 8;
	private int MIN_IDLE = 0;
	private long MAX_WAIT_MILLIS = -1;
	private long TIME_BETWEEN_EVICTION_RUNS_MILLIS = -1;
	private long MIN_EVICTABLE_IDLE_TIME_MILLIS = 1000 * 60 * 30;
	private boolean BLOCK_WHEN_EXHAUSTED = true;

	public TestPooledObjectPool() {
		super();
		setTargetBeanName(targetBeanName);
		setMaxIdle(MAX_IDLE);
		setMinIdle(MIN_IDLE);
		setMaxWait(MAX_WAIT_MILLIS);
		setTimeBetweenEvictionRunsMillis(TIME_BETWEEN_EVICTION_RUNS_MILLIS);
		setMinEvictableIdleTimeMillis(MIN_EVICTABLE_IDLE_TIME_MILLIS);
		setBlockWhenExhausted(BLOCK_WHEN_EXHAUSTED);
	}

	@Override
	public boolean validateObject(PooledObject<Object> pooledObject) {
		System.err.println(String.format("pooledObject(%s) validateObject", pooledObject));
		if (pooledObject.getObject() instanceof TestPooledObject == false) {
			System.err.println(String.format("pooledObject type(%s) is not %s", pooledObject.getClass().getName(),
					TestPooledObject.class.getName()));
			return false;
		}
		return true;
	}

	@Override
	public void activateObject(PooledObject<Object> pooledObject) throws Exception {
		TestPooledObject testPooledObject = (TestPooledObject) pooledObject.getObject();
		testPooledObject.addActivateCount();
		System.err.println(String.format("pooledObject(%s) activateObject", pooledObject));
	}

	@Override
	public void passivateObject(PooledObject<Object> pooledObject) throws Exception {
		if (validateObject(pooledObject) == false) {
			throw new RuntimeException(String.format("pooledObject type(%s) is not %s",
					pooledObject.getClass().getName(), TestPooledObject.class.getName()));
		}
		TestPooledObject testPooledObject = (TestPooledObject) pooledObject.getObject();
		testPooledObject.setName(null);
		System.err.println(String.format("pooledObject(%s) passivateObject", pooledObject));
	}
}
