package idv.hsiehpinghan.tikaassistant.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.tika.Tika;
import org.apache.tika.detect.EncodingDetector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.langdetect.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageConfidence;
import org.apache.tika.language.detect.LanguageDetector;
import org.apache.tika.language.detect.LanguageResult;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlEncodingDetector;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.parser.txt.Icu4jEncodingDetector;
import org.apache.tika.parser.txt.UniversalEncodingDetector;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import idv.hsiehpinghan.tikaassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class TikaTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private Tika tika;
	@Autowired
	private HtmlParser htmlParser;

	@Test
	public void parse() throws Exception {
		parse_html();
		parse_pdf();
		parse_custom();
	}

	@Test
	public void detectMediaType() throws Exception {
		detectMediaType_html();
	}

	@Test
	public void detectLanguage() throws Exception {
		detectLanguage_html();
	}

	@Test
	public void detectEncoding() throws Exception {
		detectEncoding_HtmlEncodingDetector();
		detectEncoding_Icu4jEncodingDetector();
		detectEncoding_UniversalEncodingDetector();
	}

	private void detectEncoding_HtmlEncodingDetector() throws IOException {
		EncodingDetector encodingDetector = new HtmlEncodingDetector();
		File file = new File("/home/hsiehpinghan/git/assistant/tika-assistant/file/html");
		FileInputStream fileInputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		Metadata metadata = new Metadata();
		Charset charset = encodingDetector.detect(bufferedInputStream, metadata);
		Assert.assertEquals(charset.name(), "GBK");
	}

	private void detectEncoding_Icu4jEncodingDetector() throws IOException {
		EncodingDetector encodingDetector = new Icu4jEncodingDetector();
		File file = new File("/home/hsiehpinghan/git/assistant/tika-assistant/file/html");
		FileInputStream fileInputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		Metadata metadata = new Metadata();
		Charset charset = encodingDetector.detect(bufferedInputStream, metadata);
		Assert.assertEquals(charset.name(), "GB18030");
	}

	private void detectEncoding_UniversalEncodingDetector() throws IOException {
		EncodingDetector encodingDetector = new UniversalEncodingDetector();
		File file = new File("/home/hsiehpinghan/git/assistant/tika-assistant/file/html");
		FileInputStream fileInputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		Metadata metadata = new Metadata();
		Charset charset = encodingDetector.detect(bufferedInputStream, metadata);
		Assert.assertEquals(charset.name(), "GB18030");
	}

	public void detectLanguage_html() throws IOException {
		LanguageDetector languageDetector = new OptimaizeLangDetector().loadModels();
		String htmlBodyText = getHtmlBodyText();
		languageDetector.addText(htmlBodyText);
		LanguageResult languageResult = languageDetector.detect();
		Assert.assertEquals(String.valueOf(languageResult.getRawScore()), "0.9999973");
		Assert.assertEquals(languageResult.getConfidence(), LanguageConfidence.HIGH);
		Assert.assertEquals(languageResult.getLanguage(), "zh-CN");
	}

	private void detectMediaType_html() throws IOException {
		File file = new File("/home/hsiehpinghan/git/assistant/tika-assistant/file/html");
		String mediaType = tika.detect(file);
		Assert.assertEquals(mediaType, "application/xhtml+xml");
	}

	private void parse_custom() throws IOException, SAXException, TikaException {
		File file = new File("/home/hsiehpinghan/git/assistant/tika-assistant/file/html");
		FileInputStream fileInputStream = new FileInputStream(file);
		BodyContentHandler bodyContentHandler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		ParseContext parseContext = new ParseContext();
		htmlParser.parse(fileInputStream, bodyContentHandler, metadata, parseContext);
		Assert.assertEquals(bodyContentHandler.toString().length(), 4856);
		for (String name : metadata.names()) {
			System.err.println(String.format("%s : %s", name, metadata.get(name)));
		}
		Assert.assertEquals(metadata.names().length, 22);
	}

	private void parse_html() throws IOException {
		File file = new File("/home/hsiehpinghan/git/assistant/tika-assistant/file/html");
		StringBuilder sb = new StringBuilder();
		try (Reader reader = tika.parse(file); BufferedReader bufferedReader = new BufferedReader(reader);) {
			String line = null;
			while (true) {
				line = bufferedReader.readLine();
				if (line == null) {
					break;
				}
				sb.append(line);
				sb.append(System.lineSeparator());
			}
		}
		Assert.assertEquals(sb.length(), 4856);
	}

	private void parse_pdf() throws IOException {
		File file = new File("/home/hsiehpinghan/git/assistant/tika-assistant/file/pdf");
		StringBuilder sb = new StringBuilder();
		try (Reader reader = tika.parse(file); BufferedReader bufferedReader = new BufferedReader(reader);) {
			String line = null;
			while (true) {
				line = bufferedReader.readLine();
				if (line == null) {
					break;
				}
				sb.append(line);
				sb.append(System.lineSeparator());
			}
		}
		Assert.assertEquals(sb.length(), 3488);
	}

	private String getHtmlBodyText() throws IOException {
		File file = new File("/home/hsiehpinghan/git/assistant/tika-assistant/file/html");
		StringBuilder sb = new StringBuilder();
		try (Reader reader = tika.parse(file); BufferedReader bufferedReader = new BufferedReader(reader);) {
			String line = null;
			while (true) {
				line = bufferedReader.readLine();
				if (line == null) {
					break;
				}
				sb.append(line);
				sb.append(System.lineSeparator());
			}
		}
		return sb.toString();
	}
}
