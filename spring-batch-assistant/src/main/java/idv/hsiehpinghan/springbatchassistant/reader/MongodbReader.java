package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import idv.hsiehpinghan.springbatchassistant.entity.MongodbDocument;

public class MongodbReader implements ItemStreamReader<MongodbDocument> {
	private ItemReader<MongodbDocument> delegate;

	@Override
	public synchronized MongodbDocument read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		MongodbDocument vo = delegate.read();
		System.err.println("MongodbReader read vo(" + vo + ")");
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

	public ItemReader<MongodbDocument> getDelegate() {
		return delegate;
	}

	public void setDelegate(ItemReader<MongodbDocument> delegate) {
		this.delegate = delegate;
	}
}
