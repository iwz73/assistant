package idv.hsiehpinghan.springbatchassistant.assistant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringBatchAssistant {
	@Autowired
	private JobExplorer jobExplorer;

	public List<JobExecution> getJobExecutionsByExitStatus(ExitStatus exitStatus) throws NoSuchJobException {
		List<JobExecution> results = new ArrayList<>();
		List<String> jobNames = jobExplorer.getJobNames();
		for (String jobName : jobNames) {
			int jobInstanceCount = jobExplorer.getJobInstanceCount(jobName);
			List<JobInstance> jobInstances = jobExplorer.getJobInstances(jobName, 0, jobInstanceCount);
			for (JobInstance jobInstance : jobInstances) {
				List<JobExecution> jobExecutions = jobExplorer.getJobExecutions(jobInstance);
				for (JobExecution jobExecution : jobExecutions) {
					if (jobExecution.getExitStatus().equals(exitStatus)) {
						results.add(jobExecution);
					}
				}
			}
		}
		return results;
	}
}
