package idv.hsiehpinghan.hanlpassistant.tokenfactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Set;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

/**
 * copy from https://github.com/smallmenu/hanlp-solr-plugin.git
 * 
 * @author hsiehpinghan
 *
 */
public class HanLPTokenizer extends Tokenizer {
	// 当前词
	private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
	// 偏移量
	private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);
	// 距离
	private final PositionIncrementAttribute positionAttr = addAttribute(PositionIncrementAttribute.class);
	// 词性
	private TypeAttribute typeAtt = addAttribute(TypeAttribute.class);

	protected SegmentWrapper segment;
	private Set<String> filter;
	private boolean enablePorterStemming;
	private final PorterStemmer stemmer = new PorterStemmer();

	/**
	 *
	 * @param segment
	 *            HanLP中的某个分词器
	 * @param filter
	 *            停用词
	 * @param enablePorterStemming
	 *            英文原型转换
	 */
	public HanLPTokenizer(Reader input, Segment segment, Set<String> filter, boolean enablePorterStemming) {
		super(input);
		this.segment = new SegmentWrapper(new BufferedReader(input), segment);
		this.filter = filter;
		this.enablePorterStemming = enablePorterStemming;
	}

	@Override
	final public boolean incrementToken() throws IOException {
		clearAttributes();
		int position = 0;
		Term term;
		boolean un_increased = true;
		do {
			term = segment.next();
			if (term == null) {
				break;
			}
			if (enablePorterStemming && term.nature == Nature.nx) {
				term.word = stemmer.stem(term.word);
			}

			if (filter != null && filter.contains(term.word)) {
				continue;
			} else {
				++position;
				un_increased = false;
			}
		} while (un_increased);

		if (term != null) {
			positionAttr.setPositionIncrement(position);
			termAtt.setEmpty().append(term.word);
			offsetAtt.setOffset(term.offset, term.offset + term.word.length());
			typeAtt.setType(term.nature == null ? "null" : term.nature.toString());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 必须重载的方法，否则在批量索引文件时将会导致文件索引失败
	 */
	@Override
	public void reset() throws IOException {
		super.reset();
		segment.reset(new BufferedReader(this.input));
	}

}