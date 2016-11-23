package idv.hsiehpinghan.springbatchassistant.test;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class RetryTemplateTest extends AbstractTestNGSpringContextTests {
	private static final int MAX_RETRY_AMOUNT = 3;
	private static final int TEST_RETRY_AMOUNT = 2;
	private static final String RESULT_MESSAGE = "success";

	@Test
	public void test() throws Exception {
		RetryTemplate retryTemplate = new RetryTemplate();
		RetryPolicy retryPolicy = getRetryPolicy();
		retryTemplate.setRetryPolicy(retryPolicy);
		String result = retryTemplate.execute(new TestRetryCallback());
		Assert.assertEquals(result, RESULT_MESSAGE);
	}

	private RetryPolicy getRetryPolicy() {
		SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
		simpleRetryPolicy.setMaxAttempts(MAX_RETRY_AMOUNT);
		return simpleRetryPolicy;
	}

	public static class TestRetryCallback implements RetryCallback<String, Exception> {
		private static int retryCnt = 0;

		@Override
		public String doWithRetry(RetryContext context) throws Exception {
			if (retryCnt < TEST_RETRY_AMOUNT) {
				++retryCnt;
				System.err.println("TestRetryCallback retryCnt(" + retryCnt + ") !!!");
				throw new RuntimeException();
			}
			return RESULT_MESSAGE;
		}

	}
}
