package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class MultithreadedStepReader implements ItemReader<String>, ItemStream {
	private ItemReader<String> delegate;

	@Override
	public synchronized String read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return delegate.read();
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

	public ItemReader<String> getDelegate() {
		return delegate;
	}

	public void setDelegate(ItemReader<String> delegate) {
		this.delegate = delegate;
	}

}
