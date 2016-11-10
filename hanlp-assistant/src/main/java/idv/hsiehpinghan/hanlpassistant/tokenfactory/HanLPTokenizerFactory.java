package idv.hsiehpinghan.hanlpassistant.tokenfactory;

import java.io.Reader;
import java.util.Map;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

import com.hankcs.hanlp.HanLP;

/**
 * copy from https://github.com/smallmenu/hanlp-solr-plugin.git
 * 
 * @author hsiehpinghan
 *
 */
public class HanLPTokenizerFactory extends TokenizerFactory {
	private boolean enableIndexMode;
	private boolean enablePorterStemming;

	public HanLPTokenizerFactory(Map<String, String> args) {
		super(args);
		enableIndexMode = getBoolean(args, "enableIndexMode", true);
		enablePorterStemming = getBoolean(args, "enablePorterStemming", false);
	}

	@Override
	public Tokenizer create(AttributeFactory factory, Reader input) {
		return new HanLPTokenizer(input, HanLP.newSegment().enableOffset(true).enableIndexMode(enableIndexMode), null,
				enablePorterStemming);
	}
}