package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamWriter;
import org.springframework.batch.item.ItemWriter;

import idv.hsiehpinghan.springbatchassistant.entity.MongodbDocument;

public class MongodbWriter implements ItemStreamWriter<MongodbDocument> {
	private ItemWriter<MongodbDocument> delegate;

	@Override
	public void write(List<? extends MongodbDocument> items) throws Exception {
		delegate.write(items);
		System.err.println("MongodbWriter write items(" + items + ")");
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

	public ItemWriter<MongodbDocument> getDelegate() {
		return delegate;
	}

	public void setDelegate(ItemWriter<MongodbDocument> delegate) {
		this.delegate = delegate;
	}

}
