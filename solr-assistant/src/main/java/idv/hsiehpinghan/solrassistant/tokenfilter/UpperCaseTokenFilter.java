package idv.hsiehpinghan.solrassistant.tokenfilter;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class UpperCaseTokenFilter extends TokenFilter {
	private final CharTermAttribute charTermAttribute = addAttribute(CharTermAttribute.class);
	private final String TERM;

	public UpperCaseTokenFilter(TokenStream tokenStream, String term) {
		super(tokenStream);
		this.TERM = term;
	}

	@Override
	public boolean incrementToken() throws IOException {
		if (input.incrementToken() == false) {
			return false;
		}
		char[] term = charTermAttribute.buffer();
		int len = charTermAttribute.length();
		String token = new String(term, 0, len);
		if (TERM.equals(token)) {
			charTermAttribute.setEmpty().append(token.toUpperCase());
		}
		return true;
	}

}
