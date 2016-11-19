package idv.hsiehpinghan.hanlpassistant.tokenizer.dictionary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.corpus.dictionary.item.Item;
import com.hankcs.hanlp.dictionary.BaseSearcher;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;

@Component
public class CustomDictionaryAssistant {
	public boolean add(String word, String natureWithFrequency) {
		return CustomDictionary.add(word, natureWithFrequency);
	}

	public boolean insert(String word, String natureWithFrequency) {
		return CustomDictionary.insert(word, natureWithFrequency);
	}

	public void remove(String word) {
		CustomDictionary.remove(word);
	}

	public CoreDictionary.Attribute get(String word) {
		return CustomDictionary.get(word);
	}

	/**
	 * AhoCorasickDoubleArrayTrie自动机分词
	 * 
	 * @param text
	 * @return
	 */
	public List<Item> parseText(String text) {
		final List<Item> items = new ArrayList<>();
		final char[] charArray = text.toCharArray();
		AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute> processor = new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>() {
			@Override
			public void hit(int begin, int end, CoreDictionary.Attribute value) {
				String param = String.format("%s %s", new String(charArray, begin, end - begin), value);
				Item item = Item.create(param);
				items.add(item);
			}
		};
		CustomDictionary.parseText(charArray, processor);
		return items;
	}

	/**
	 * trie树分词
	 * 
	 * @param text
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public BaseSearcher<CoreDictionary.Attribute> getSearcher(String text) {
		char[] charArray = text.toCharArray();
		return CustomDictionary.getSearcher(charArray);
	}

}
