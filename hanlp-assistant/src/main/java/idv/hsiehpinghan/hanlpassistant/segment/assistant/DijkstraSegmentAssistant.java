package idv.hsiehpinghan.hanlpassistant.segment.assistant;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.common.Term;

@Component
public class DijkstraSegmentAssistant {
	private Segment segment = null;

	public DijkstraSegmentAssistant() {
		segment = new DijkstraSegment();
		segment.enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
	}

	public List<Term> segment(String str) {
		return segment.seg(str);
	}
}
