package idv.hsiehpinghan.hanlpassistant.tokenizer.assistant;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.NLPTokenizer;

@Component
public class NLPTokenizerAssistant {
	public List<Term> segment(String str) {
		return NLPTokenizer.segment(str);
	}
}
