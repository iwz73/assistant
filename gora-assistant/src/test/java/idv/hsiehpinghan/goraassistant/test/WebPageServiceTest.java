package idv.hsiehpinghan.goraassistant.test;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.avro.util.Utf8;
import org.apache.gora.filter.FilterList;
import org.apache.gora.filter.FilterList.Operator;
import org.apache.gora.filter.FilterOp;
import org.apache.gora.filter.SingleFieldValueFilter;
import org.apache.gora.query.Result;
import org.apache.nutch.storage.ParseStatus;
import org.apache.nutch.storage.ProtocolStatus;
import org.apache.nutch.storage.WebPage;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.goraassistant.service.WebPageService;
import idv.hsiehpinghan.goraassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.testutility.utility.CompareUtility;

public class WebPageServiceTest {
	 private String BATCH_ID = "batchId";
//	private String BATCH_ID = "1464158096-1608326935";
	private ApplicationContext applicationContext;
	private WebPageService service;

	@BeforeClass
	public void beforeClass() throws Exception {
		applicationContext = TestngSuitSetting.getApplicationContext();
		service = applicationContext.getBean(WebPageService.class);
	}

//	@Test
	public void put() {
		for (int i = 0; i < 10; ++i) {
			String key = String.valueOf(i);
			WebPage entity = generateWebPage(i);
			service.put(key, entity);
			WebPage returnEntity = service.get(key);
			assertEquals(returnEntity, entity);
		}
	}

	@Test
	public void query() throws Exception {
		query_0();
		query_1();
	}

	private void query_0() throws Exception {
		String[] fields = new String[] { WebPage.Field.STATUS.getName() };
		Result<String, WebPage> result = service.query(generateFilterList_0(), fields);
		int row = 0;
		while (result.next()) {
			++row;
		}
		Assert.assertTrue(row > 0);
	}

	private void query_1() throws Exception {
		String[] fields = new String[] { WebPage.Field.CONTENT.getName() };
		Result<String, WebPage> result = service.query(generateFilterList_1(), fields);
		int row = 0;
		while (result.next()) {

			String key = result.getKey();
			WebPage entity = result.get();
			System.err.println(key);
			System.err.println(entity);

			++row;
		}
		Assert.assertTrue(row > 0);
	}

	private FilterList<String, WebPage> generateFilterList_0() {
		FilterList<String, WebPage> filter = new FilterList<String, WebPage>(Operator.MUST_PASS_ALL);
		filter.addFilter(generateSingleFieldValueFilter_0());
		return filter;
	}

	private FilterList<String, WebPage> generateFilterList_1() {
		FilterList<String, WebPage> filter = new FilterList<String, WebPage>(Operator.MUST_PASS_ALL);
		filter.addFilter(generateSingleFieldValueFilter_1());
		return filter;
	}

	private SingleFieldValueFilter<String, WebPage> generateSingleFieldValueFilter_0() {
		SingleFieldValueFilter<String, WebPage> filter = new SingleFieldValueFilter<String, WebPage>();
		filter.setFieldName(WebPage.Field.BATCH_ID.toString());
		filter.setFilterOp(FilterOp.EQUALS);
		filter.setFilterIfMissing(true);
		filter.getOperands().add(new Utf8(BATCH_ID + 0));
		return filter;
	}

	private SingleFieldValueFilter<String, WebPage> generateSingleFieldValueFilter_1() {
		SingleFieldValueFilter<String, WebPage> filter = new SingleFieldValueFilter<String, WebPage>();
		filter.setFieldName(WebPage.Field.BATCH_ID.toString());
		filter.setFilterOp(FilterOp.EQUALS);
		filter.setFilterIfMissing(true);
		for(int i = 0; i < 3; ++i) {
			filter.getOperands().add(new Utf8(BATCH_ID + i));	
		}
		return filter;
	}

	private List<CharSequence> generateArgs() {
		final int SIZE = 100;
		List<CharSequence> charSequences = new ArrayList<CharSequence>(SIZE);
		for (int i = 0; i < SIZE; ++i) {
			charSequences.add(String.valueOf(i));
		}
		return charSequences;
	}

	private ProtocolStatus generateProtocolStatus(int i) {
		ProtocolStatus protocolStatus = new ProtocolStatus();
		protocolStatus.setCode(i);
		protocolStatus.setArgs(generateArgs());
		protocolStatus.setLastModified(Long.valueOf(i));
		return protocolStatus;
	}

	private ParseStatus generateParseStatus(int i) {
		ParseStatus parseStatus = new ParseStatus();
		parseStatus.setMajorCode(i);
		parseStatus.setMinorCode(i);
		parseStatus.setArgs(generateArgs());
		return parseStatus;
	}

	private Map<CharSequence, CharSequence> generateHeaders() {
		final int SIZE = 100;
		Map<CharSequence, CharSequence> headers = new HashMap<CharSequence, CharSequence>(SIZE);
		for (int i = 0; i < 100; ++i) {
			headers.put(String.valueOf(i), String.valueOf(i));
		}
		return headers;
	}

	private Map<CharSequence, CharSequence> generateOutlinks() {
		final int SIZE = 100;
		Map<CharSequence, CharSequence> outlinks = new HashMap<CharSequence, CharSequence>(SIZE);
		for (int i = 0; i < 100; ++i) {
			outlinks.put(String.valueOf(i), String.valueOf(i));
		}
		return outlinks;
	}

	private Map<CharSequence, CharSequence> generateInlinks() {
		final int SIZE = 100;
		Map<CharSequence, CharSequence> inlinks = new HashMap<CharSequence, CharSequence>(SIZE);
		for (int i = 0; i < 100; ++i) {
			inlinks.put(String.valueOf(i), String.valueOf(i));
		}
		return inlinks;
	}

	private Map<CharSequence, CharSequence> generateMarkers() {
		final int SIZE = 100;
		Map<CharSequence, CharSequence> markers = new HashMap<CharSequence, CharSequence>(SIZE);
		for (int i = 0; i < 100; ++i) {
			markers.put(String.valueOf(i), String.valueOf(i));
		}
		return markers;
	}

	private Map<CharSequence, ByteBuffer> generateMetadata() {
		final int SIZE = 100;
		Map<CharSequence, ByteBuffer> metadata = new HashMap<CharSequence, ByteBuffer>(SIZE);
		for (int i = 0; i < 100; ++i) {
			metadata.put(String.valueOf(i), generateByteBuffer(String.valueOf(i)));
		}
		return metadata;
	}

	private ByteBuffer generateByteBuffer(String str) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(str.length());
		byteBuffer.put(str.getBytes());
		return byteBuffer;
	}

	private WebPage generateWebPage(int i) {
		WebPage entity = new WebPage();
		entity.setBaseUrl("baseUrl");
		entity.setStatus(i);
		entity.setFetchTime(Long.valueOf(i));
		entity.setPrevFetchTime(Long.valueOf(i));
		entity.setFetchInterval(i);
		entity.setRetriesSinceFetch(i);
		entity.setModifiedTime(Long.valueOf(i));
		entity.setPrevModifiedTime(Long.valueOf(i));
		entity.setProtocolStatus(generateProtocolStatus(i));
		entity.setContent(generateByteBuffer("content"));
		entity.setPrevSignature(generateByteBuffer("prevSignature"));
		entity.setSignature(generateByteBuffer("signature"));
		entity.setTitle("title");
		entity.setText("text");
		entity.setParseStatus(generateParseStatus(i));
		entity.setScore(Float.valueOf(i));
		entity.setReprUrl("reprUrl");
		entity.setHeaders(generateHeaders());
		entity.setOutlinks(generateOutlinks());
		entity.setInlinks(generateInlinks());
		entity.setMarkers(generateMarkers());
		entity.setMetadata(generateMetadata());
		entity.setBatchId(BATCH_ID + i);
		return entity;
	}

	private void assertEquals(WebPage returnEntity, WebPage entity) {
		Assert.assertEquals(returnEntity.getBaseUrl().toString(), entity.getBaseUrl().toString());
		Assert.assertEquals(returnEntity.getStatus(), entity.getStatus());
		Assert.assertEquals(returnEntity.getFetchTime(), entity.getFetchTime());
		Assert.assertEquals(returnEntity.getPrevFetchTime(), entity.getPrevFetchTime());
		Assert.assertEquals(returnEntity.getFetchInterval(), entity.getFetchInterval());
		Assert.assertEquals(returnEntity.getRetriesSinceFetch(), entity.getRetriesSinceFetch());
		Assert.assertEquals(returnEntity.getModifiedTime(), entity.getModifiedTime());
		Assert.assertEquals(returnEntity.getPrevModifiedTime(), entity.getPrevModifiedTime());
		Assert.assertEquals(returnEntity.getProtocolStatus(), entity.getProtocolStatus());
		Assert.assertNull(CompareUtility.getIndexOfDifferentByte(returnEntity.getContent(), entity.getContent()));
		Assert.assertNull(
				CompareUtility.getIndexOfDifferentByte(returnEntity.getPrevSignature(), entity.getPrevSignature()));
		Assert.assertNull(CompareUtility.getIndexOfDifferentByte(returnEntity.getSignature(), entity.getSignature()));
		Assert.assertEquals(returnEntity.getTitle().toString(), entity.getTitle().toString());
		Assert.assertEquals(returnEntity.getText().toString(), entity.getText().toString());
		Assert.assertEquals(returnEntity.getParseStatus(), entity.getParseStatus());
		Assert.assertEquals(returnEntity.getScore(), entity.getScore());
		Assert.assertEquals(returnEntity.getReprUrl().toString(), entity.getReprUrl().toString());
		Assert.assertTrue(CompareUtility.compareCharSequenceMap(returnEntity.getHeaders(), entity.getHeaders()));
		Assert.assertTrue(CompareUtility.compareCharSequenceMap(returnEntity.getOutlinks(), entity.getOutlinks()));
		Assert.assertTrue(CompareUtility.compareCharSequenceMap(returnEntity.getInlinks(), entity.getInlinks()));
		Assert.assertTrue(CompareUtility.compareCharSequenceMap(returnEntity.getMarkers(), entity.getMarkers()));
		Assert.assertTrue(compare(returnEntity.getMetadata(), entity.getMetadata()));
		Assert.assertEquals(returnEntity.getBatchId().toString(), entity.getBatchId().toString());
	}

	private static boolean compare(Map<CharSequence, ByteBuffer> map0, Map<CharSequence, ByteBuffer> map1) {
		if (map0.size() != map1.size()) {
			return false;
		}
		Map<String, String> m0 = convertToStringMap(map0);
		Map<String, String> m1 = convertToStringMap(map1);
		for (Entry<String, String> ent : m0.entrySet()) {
			String k0 = ent.getKey();
			String v0 = ent.getValue();
			String v1 = m1.get(k0);
			if (v0.equals(v1) == false) {
				return false;
			}
		}
		return true;
	}

	private static Map<String, String> convertToStringMap(Map<CharSequence, ByteBuffer> map) {
		Map<String, String> m = new HashMap<String, String>(map.size());
		map.forEach((k, v) -> {
			String key = k.toString();
			String value = new String(v.array());
			m.put(key, value);
		});
		return m;
	}
}
