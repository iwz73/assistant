package idv.hsiehpinghan.jiebaanalysisassistant.tokenfactory;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;

import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;

/**
 * copy from https://github.com/sing1ee/jieba-solr/ for test.
 * 
 * @author thank
 *
 */
public class JiebaAnalyzer extends Analyzer {
	private String segMode;

	public JiebaAnalyzer() {
		this(SegMode.SEARCH.name());
	}

	public JiebaAnalyzer(String segMode) {
		this.segMode = segMode;
	}

	public JiebaAnalyzer(ReuseStrategy reuseStrategy) {
		super(reuseStrategy);
	}

	@Override
	protected TokenStreamComponents createComponents(String field, Reader in) {
		return new TokenStreamComponents(new JiebaTokenizer(in, this.segMode));
	}
}