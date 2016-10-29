package idv.hsiehpinghan.hanlpassistant.tokenizer.assistant;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

@Component
public class StandardTokenizerAssistant {
	public List<Term> segment(String str) {
		return StandardTokenizer.segment(str);
	}
}
