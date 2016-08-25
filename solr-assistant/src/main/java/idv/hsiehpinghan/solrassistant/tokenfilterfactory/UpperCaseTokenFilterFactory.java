package idv.hsiehpinghan.solrassistant.tokenfilterfactory;

import java.util.Map;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.util.TokenFilterFactory;

import idv.hsiehpinghan.solrassistant.tokenfilter.UpperCaseTokenFilter;

public class UpperCaseTokenFilterFactory extends TokenFilterFactory {
	private String term;

	public UpperCaseTokenFilterFactory(Map<String, String> args) {
		super(args);
		term = require(args, "term");
	}

	@Override
	public TokenStream create(TokenStream tokenStream) {
		assureMatchVersion();
		return new UpperCaseTokenFilter(tokenStream, term);
	}

}
