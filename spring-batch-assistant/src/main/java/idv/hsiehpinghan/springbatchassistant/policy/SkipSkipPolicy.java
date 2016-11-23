package idv.hsiehpinghan.springbatchassistant.policy;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class SkipSkipPolicy implements SkipPolicy {

	@Override
	public boolean shouldSkip(Throwable t, int skipCount) throws SkipLimitExceededException {
		if (t instanceof RuntimeException) {
			System.err.println(
					"SkipSkipPolicy Throwable message(" + t.getMessage() + "), skipCount(" + skipCount + ") !!!");
			return true;
		}
		return false;
	}

}
