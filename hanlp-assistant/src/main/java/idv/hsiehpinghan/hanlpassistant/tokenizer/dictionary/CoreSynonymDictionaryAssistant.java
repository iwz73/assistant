package idv.hsiehpinghan.hanlpassistant.tokenizer.dictionary;

import org.springframework.stereotype.Component;

import com.hankcs.hanlp.dictionary.CoreSynonymDictionary;

@Component
public class CoreSynonymDictionaryAssistant {
	public long distance(String itemA, String itemB) {
		return CoreSynonymDictionary.distance(itemA, itemB);
	}

}
