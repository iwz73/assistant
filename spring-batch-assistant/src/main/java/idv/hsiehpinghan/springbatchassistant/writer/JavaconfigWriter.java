package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("step")
public class JavaconfigWriter implements ItemWriter<Integer> {
	@Value("#{jobParameters['value']}")
	private String value;

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		System.err.println("JavaconfigWriter write items(" + items + "), value(" + value + ")");
	}
}
