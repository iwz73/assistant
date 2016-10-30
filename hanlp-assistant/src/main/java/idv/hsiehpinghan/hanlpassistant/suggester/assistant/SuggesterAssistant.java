package idv.hsiehpinghan.hanlpassistant.suggester.assistant;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hankcs.hanlp.suggest.Suggester;

@Component
public class SuggesterAssistant {
	private Suggester suggester = null;

	public SuggesterAssistant() {
		suggester = new Suggester();
	}

	public void addSentence(String... sentences) {
		for (String sentence : sentences) {
			suggester.addSentence(sentence);
		}
	}

	public List<String> suggest(String keyword, int size) {
		return suggester.suggest(keyword, size);
	}
}
