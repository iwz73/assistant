package idv.hsiehpinghan.springbatchassistant.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

public class CopyFileTasklet implements Tasklet {
	private Resource fileResource;
	private String targetDirectory;
	private String targetFile;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.err.println("copy fileResource(" + fileResource + ") to " + targetDirectory + "/" + targetFile);
		return RepeatStatus.FINISHED;
	}

	public void setFileResource(Resource fileResource) {
		this.fileResource = fileResource;
	}

	public void setTargetDirectory(String targetDirectory) {
		this.targetDirectory = targetDirectory;
	}

	public void setTargetFile(String targetFile) {
		this.targetFile = targetFile;
	}

}
