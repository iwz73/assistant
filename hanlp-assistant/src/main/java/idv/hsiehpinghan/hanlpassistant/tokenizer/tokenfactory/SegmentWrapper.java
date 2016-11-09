package idv.hsiehpinghan.hanlpassistant.tokenizer.tokenfactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;

/**
 * copy from https://github.com/smallmenu/hanlp-solr-plugin.git
 * 
 * @author hsiehpinghan
 *
 */
public class SegmentWrapper {
	BufferedReader br;
	Segment segment;
	/**
	 * 因为next是单个term出去的，所以在这里做一个记录
	 */
	Term[] termArray;
	/**
	 * termArray下标
	 */
	int index;
	/**
	 * term的偏移量，由于wrapper是按行读取的，必须对term.offset做一个校正
	 */
	int offset;

	public SegmentWrapper(BufferedReader br, Segment segment) {
		this.br = br;
		this.segment = segment;
	}

	/**
	 * 重置分词器
	 *
	 * @param br
	 */
	public void reset(BufferedReader br) {
		this.br = br;
		termArray = null;
		index = 0;
		offset = 0;
	}

	public Term next() throws IOException {
		if (termArray != null && index < termArray.length)
			return termArray[index++];
		String line = br.readLine();
		while (isBlank(line)) {
			if (line == null)
				return null;
			offset += line.length() + 1;
			line = br.readLine();
		}

		List<Term> termList = segment.seg(line);
		if (termList.size() == 0)
			return null;
		termArray = termList.toArray(new Term[0]);
		for (Term term : termArray) {
			term.offset += offset;
		}
		index = 0;
		offset += line.length() + 1;

		return termArray[index++];
	}

	/**
	 * 判断字符串是否为空（null和空格）
	 *
	 * @param cs
	 * @return
	 */
	private static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}