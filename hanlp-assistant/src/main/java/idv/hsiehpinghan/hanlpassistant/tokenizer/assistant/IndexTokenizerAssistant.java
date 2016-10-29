package idv.hsiehpinghan.hanlpassistant.tokenizer.assistant;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.IndexTokenizer;

@Component
public class IndexTokenizerAssistant {
	public List<Term> segment(String str) {
		return IndexTokenizer.segment(str);
	}
}
