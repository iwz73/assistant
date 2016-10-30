package idv.hsiehpinghan.hanlpassistant.tokenizer.assistant;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.SpeedTokenizer;

@Component
public class SpeedTokenizerAssistant {
	public List<Term> segment(String str) {
		return SpeedTokenizer.segment(str);
	}
}
