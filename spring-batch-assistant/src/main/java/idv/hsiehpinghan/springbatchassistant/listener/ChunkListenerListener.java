package idv.hsiehpinghan.springbatchassistant.listener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class ChunkListenerListener implements ChunkListener {

	@Override
	public void beforeChunk(ChunkContext chunkContext) {
		System.err.println("ChunkListenerListener beforeChunk !!!");
	}

	@Override
	public void afterChunk(ChunkContext chunkContext) {
		System.err.println("ChunkListenerListener afterChunk !!!");
	}

	@Override
	public void afterChunkError(ChunkContext chunkContext) {
		System.err.println("ChunkListenerListener afterChunkError !!!");
	}

}
