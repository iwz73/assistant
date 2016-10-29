package idv.hsiehpinghan.hanlpassistant.segment.assistant;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.common.Term;

@Component
public class DijkstraSegmentAssistant {
	private DijkstraSegment dijkstraSegment = null;

	public DijkstraSegmentAssistant() {
		dijkstraSegment = new DijkstraSegment();
		dijkstraSegment.enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
	}

	public List<Term> segment(String str) {
		return dijkstraSegment.seg(str);
	}
}
