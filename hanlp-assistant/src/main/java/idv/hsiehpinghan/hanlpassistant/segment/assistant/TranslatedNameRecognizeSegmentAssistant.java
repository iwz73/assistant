package idv.hsiehpinghan.hanlpassistant.segment.assistant;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

@Component
public class TranslatedNameRecognizeSegmentAssistant {
	private Segment segment = null;

	public TranslatedNameRecognizeSegmentAssistant() {
		segment = HanLP.newSegment().enableTranslatedNameRecognize(true);
	}

	public List<Term> segment(String str) {
		return segment.seg(str);
	}
}
