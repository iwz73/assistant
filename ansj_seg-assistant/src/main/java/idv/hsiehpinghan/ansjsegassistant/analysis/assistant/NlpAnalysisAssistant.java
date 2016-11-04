package idv.hsiehpinghan.ansjsegassistant.analysis.assistant;

import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.springframework.stereotype.Component;

@Component
public class NlpAnalysisAssistant {
	/**
	 * include : 用户自定义词典, 数字识别, 人名识别, 机构名识别, 新词发现
	 * 
	 * @param text
	 * @return
	 */
	public Result parse(String text) {
		return NlpAnalysis.parse(text);
	}
}
