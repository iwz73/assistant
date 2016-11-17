package temp.listener_;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class ChunkListenerListener implements ChunkListener {

	@Override
	public void beforeChunk(ChunkContext chunkContext) {
		System.err.println("ChunkListenerListener beforeChunk !!!");
		chunkContext.getStepContext().getStepExecution().getExecutionContext()
				.putString("beforeChunk", "beforeChunk");
	}

	@Override
	public void afterChunk(ChunkContext chunkContext) {
		System.err.println("ChunkListenerListener afterChunk !!!");
		chunkContext.getStepContext().getStepExecution().getExecutionContext()
				.putString("afterChunk", "afterChunk");
	}

	@Override
	public void afterChunkError(ChunkContext chunkContext) {
		System.err.println("ChunkListenerListener afterChunkError !!!");
		chunkContext.getStepContext().getStepExecution().getExecutionContext()
				.putString("afterChunkError", "afterChunkError");
	}

}
