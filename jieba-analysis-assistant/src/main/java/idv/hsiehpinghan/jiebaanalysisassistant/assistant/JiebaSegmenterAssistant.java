package idv.hsiehpinghan.jiebaanalysisassistant.assistant;

import java.util.List;

import org.springframework.stereotype.Component;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.SegToken;

@Component
public class JiebaSegmenterAssistant {
	public List<SegToken> getSegTokens(String text, SegMode segMode) {
		JiebaSegmenter jiebaSegmenter = new JiebaSegmenter();
		return jiebaSegmenter.process(text, segMode);
	}
}
