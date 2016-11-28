package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import idv.hsiehpinghan.springbatchassistant.vo.FlatFileVo;

public class MultiResourcePartitionerReader implements ItemReader<FlatFileVo>, ItemStream {
	private ItemReader<FlatFileVo> delegate;

	@Override
	public synchronized FlatFileVo read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		long threadId = Thread.currentThread().getId();
		FlatFileVo vo = delegate.read();
		System.err.println("threadId(" + threadId + ") MultiResourcePartitionerReader read vo(" + vo + ")");
		return vo;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		if (this.delegate instanceof ItemStream) {
			((ItemStream) this.delegate).open(executionContext);
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		if (this.delegate instanceof ItemStream) {
			((ItemStream) this.delegate).update(executionContext);
		}
	}

	@Override
	public void close() throws ItemStreamException {
		if (this.delegate instanceof ItemStream) {
			((ItemStream) this.delegate).close();
		}
	}

	public ItemReader<FlatFileVo> getDelegate() {
		return delegate;
	}

	public void setDelegate(ItemReader<FlatFileVo> delegate) {
		this.delegate = delegate;
	}

}
