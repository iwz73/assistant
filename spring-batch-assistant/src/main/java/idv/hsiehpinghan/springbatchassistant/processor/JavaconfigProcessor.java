package idv.hsiehpinghan.springbatchassistant.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("step")
public class JavaconfigProcessor implements ItemProcessor<String, String> {
	@Value("#{jobParameters['value']}")
	private String value;

	@Override
	public String process(String item) throws Exception {
		System.err.println("JavaconfigProcessor write process item(" + item + "), value(" + value + ")");
		return item;
	}
}