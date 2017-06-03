package idv.hsiehpinghan.springassistant.schedule.singlethread;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewSingleThreadExecutor {
	private final long SLEEP_MILISECONDS = 5000;

	@Scheduled(fixedRate = 1000)
	public void job_0() {
		printMessage("job_0");
	}

	@Scheduled(fixedRate = 1000)
	public void job_1() {
		printMessage("job_1");
	}

	@Scheduled(fixedRate = 1000)
	public void job_2() {
		printMessage("job_2");
	}

	private void printMessage(String methodName) {
		System.out.println(this.getClass().getName() + " " + methodName);
		try {
			Thread.sleep(SLEEP_MILISECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
