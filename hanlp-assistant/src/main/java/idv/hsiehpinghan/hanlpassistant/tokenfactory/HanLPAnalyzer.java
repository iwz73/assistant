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
public class HanLPAnalyzer extends Analyzer {

	boolean enablePorterStemming;
	public Set<String> filter;

	/**
	 * @param filter
	 *            停用词
	 * @param enablePorterStemming
	 *            是否分析词干（仅限英文）
	 */
	public HanLPAnalyzer(Set<String> filter, boolean enablePorterStemming) {
		this.filter = filter;
	}

	/**
	 * @param enablePorterStemming
	 *            是否分析词干.进行单复数,时态的转换
	 */
	public HanLPAnalyzer(boolean enablePorterStemming) {
		this.enablePorterStemming = enablePorterStemming;
	}

	public HanLPAnalyzer() {
		super();
	}

	/**
	 * 重载Analyzer接口，构造分词组件
	 */
	@Override
	protected TokenStreamComponents createComponents(String fieldName, Reader input) {
		Tokenizer tokenizer = new HanLPTokenizer(input, HanLP.newSegment().enableOffset(true), filter,
				enablePorterStemming);
		return new TokenStreamComponents(tokenizer);
	}
}