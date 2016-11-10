package idv.hsiehpinghan.hanlpassistant.tokenfactory;

import java.io.Reader;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

import com.hankcs.hanlp.HanLP;

/**
 * copy from https://github.com/smallmenu/hanlp-solr-plugin.git
 * 
 * @author hsiehpinghan
 *
 */
public class HanLPIndexAnalyzer extends Analyzer {

	private boolean pstemming;
	private Set<String> filter;

	/**
	 * @param filter
	 *            停用词
	 * @param pstemming
	 *            是否分析词干
	 */
	public HanLPIndexAnalyzer(Set<String> filter, boolean pstemming) {
		this.filter = filter;
	}

	/**
	 * @param pstemming
	 *            是否分析词干.进行单复数,时态的转换
	 */
	public HanLPIndexAnalyzer(boolean pstemming) {
		this.pstemming = pstemming;
	}

	public HanLPIndexAnalyzer() {
		super();
	}

	@Override
	protected TokenStreamComponents createComponents(String fieldName, Reader input) {
		Tokenizer tokenizer = new HanLPTokenizer(input, HanLP.newSegment().enableIndexMode(true), filter, pstemming);
		return new TokenStreamComponents(tokenizer);
	}
}