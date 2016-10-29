package idv.hsiehpinghan.hanlpassistant.segment.assistant;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.common.Term;

@Component
public class NShortSegmentAssistant {
	private NShortSegment nshortSegment = null;

	public NShortSegmentAssistant() {
		nshortSegment = new NShortSegment();
		nshortSegment.enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
	}

	public List<Term> segment(String str) {
		return nshortSegment.seg(str);
	}
}
