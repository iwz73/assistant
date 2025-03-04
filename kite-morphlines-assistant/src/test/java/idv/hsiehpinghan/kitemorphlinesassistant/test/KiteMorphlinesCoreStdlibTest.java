package idv.hsiehpinghan.kitemorphlinesassistant.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.kitesdk.morphline.api.AbstractMorphlineTest;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.Fields;

public class KiteMorphlinesCoreStdlibTest extends AbstractMorphlineTest {

	@Test
	public void addCurrentTime() throws Exception {
		morphline = createMorphline("conf/addCurrentTime");
		File file = new File(RESOURCES_DIR + "/data/csv.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertTrue(((Long) rec_0.getFirstValue("timestamp")) <= System.currentTimeMillis());
		}
	}

	@Test
	public void addLocalHost() throws Exception {
		morphline = createMorphline("conf/addLocalHost");
		File file = new File(RESOURCES_DIR + "/data/csv.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			System.err.println("host : " + rec_0.getFirstValue("host"));
		}
	}

	@Test
	public void addValues() throws Exception {
		morphline = createMorphline("conf/addValues");
		File file = new File(RESOURCES_DIR + "/data/csv.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("added string", rec_0.getFirstValue("added_string"));
			Assert.assertEquals("[item 0, item 1, item 2]", rec_0.get("added_array").toString());
			Assert.assertEquals("na\nme_0", rec_0.getFirstValue("copied_name"));
			Assert.assertEquals("0", rec_0.getFirstValue("copied_age"));
		}
	}

	@Test
	public void addValuesIfAbsent() throws Exception {
		morphline = createMorphline("conf/addValuesIfAbsent");
		File file = new File(RESOURCES_DIR + "/data/csv.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("added string", rec_0.getFirstValue("added_string"));
			Assert.assertEquals("[item 0, item 1, item 2]", rec_0.get("added_array").toString());
			Assert.assertEquals("na\nme_0", rec_0.getFirstValue("copied_name"));
			Assert.assertEquals("0", rec_0.getFirstValue("copied_age"));
		}
	}

	@Test
	public void contains() throws Exception {
		morphline = createMorphline("conf/ifContains");
		File file = new File(RESOURCES_DIR + "/data/csv.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("false", rec_0.getFirstValue("isContainName_1"));
			Assert.assertEquals("true", rec_0.getFirstValue("isContainAge0Or9"));
			Record rec_1 = collector.getRecords().get(1);
			Assert.assertEquals("true", rec_1.getFirstValue("isContainName_1"));
			Assert.assertEquals("false", rec_1.getFirstValue("isContainAge0Or9"));
		}
	}

	@Test
	public void convertTimestamp() throws Exception {
		morphline = createMorphline("conf/convertTimestamp");
		File file = new File(RESOURCES_DIR + "/data/convertTimestamp.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("2017-07-08T00:18:07.000Z", rec_0.getFirstValue("date"));
			Assert.assertEquals("2017-07-08T00:18:07.551Z", rec_0.getFirstValue("milliseconds"));
		}
	}

	@Test
	public void dropRecord() throws Exception {
		morphline = createMorphline("conf/dropRecord");
		File file = new File(RESOURCES_DIR + "/data/csv.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Assert.assertEquals(0, collector.getRecords().size());
		}
	}

	@Test
	public void equals() throws Exception {
		morphline = createMorphline("conf/ifEquals");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("true", (String) rec_0.getFirstValue("isIdEquals0"));
			Assert.assertEquals("false", (String) rec_0.getFirstValue("isTextArrayEquals1"));
			Record rec_1 = collector.getRecords().get(1);
			Assert.assertEquals("false", (String) rec_1.getFirstValue("isIdEquals0"));
			Assert.assertEquals("true", (String) rec_1.getFirstValue("isTextArrayEquals1"));
		}
	}

	@Test
	public void extractURIComponents() throws Exception {
		morphline = createMorphline("conf/extractURIComponents");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
			Record rec_1 = collector.getRecords().get(1);
			Assert.assertEquals("[kitesdk.org, www.google.com.tw, tw.yahoo.com:80]",
					rec_1.get("extracted_authority").toString());
			Assert.assertEquals("[extractURIComponents]", rec_1.get("extracted_fragment").toString());
			Assert.assertEquals("[kitesdk.org, www.google.com.tw, tw.yahoo.com]",
					rec_1.get("extracted_host").toString());
			Assert.assertEquals("[/docs/1.1.0/morphlines/morphlines-reference-guide.html, /, /]",
					rec_1.get("extracted_path").toString());
			Assert.assertEquals("[-1, -1, 80]", rec_1.get("extracted_port").toString());
			Assert.assertEquals("[gws_rd=ssl]", rec_1.get("extracted_query").toString());
			Assert.assertEquals("[http, https, https]", rec_1.get("extracted_scheme").toString());
			Assert.assertEquals(
					"[//kitesdk.org/docs/1.1.0/morphlines/morphlines-reference-guide.html, //www.google.com.tw/?gws_rd=ssl, //tw.yahoo.com:80/]",
					rec_1.get("extracted_schemeSpecificPart").toString());
		}
	}

	@Test
	public void extractURIComponent() throws Exception {
		morphline = createMorphline("conf/extractURIComponent");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
			Record rec_1 = collector.getRecords().get(1);
			Assert.assertEquals("[kitesdk.org, www.google.com.tw, tw.yahoo.com]",
					rec_1.get("extracted_host").toString());
		}
	}

	@Test
	public void extractURIQueryParameters() throws Exception {
		morphline = createMorphline("conf/extractURIQueryParameters");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
			Record rec_1 = collector.getRecords().get(1);
			Assert.assertEquals("[ssl]", rec_1.get("extractedParameters").toString());
		}
	}

	@Test
	public void findReplace() throws Exception {
		morphline = createMorphline("conf/findReplace");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals(Arrays.asList("findReplaceTest_A_0", "findReplaceTest_B_0", "findReplaceTest_C_0"),
					rec_0.get("text_txt"));
		}
	}

	@Test
	public void generateUUID() throws Exception {
		morphline = createMorphline("conf/generateUUID");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertNotNull(rec_0.get("uuid"));
		}
	}

	@Test
	public void grok() throws Exception {
		morphline = createMorphline("conf/grok");
		File file = new File(RESOURCES_DIR + "/data/grok.txt");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(1, collector.getRecords().size());
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("[164]", rec_0.get("priority").toString());
			Assert.assertEquals("[Feb  4 10:46:14]", rec_0.get("timestamp").toString());
			Assert.assertEquals("[syslog]", rec_0.get("hostname").toString());
			Assert.assertEquals("[sshd]", rec_0.get("program").toString());
			Assert.assertEquals("[607]", rec_0.get("pid").toString());
			Assert.assertEquals("[listening on 0.0.0.0 port 22.]", rec_0.get("msg").toString());
		}
	}

	@Test
	public void head() throws Exception {
		morphline = createMorphline("conf/head");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(2, collector.getRecords().size());
		}
	}

	@Test
	public void _if() throws Exception {
		morphline = createMorphline("conf/ifContains");
		File file = new File(RESOURCES_DIR + "/data/csv.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("false", rec_0.getFirstValue("isContainName_1"));
			Assert.assertEquals("true", rec_0.getFirstValue("isContainAge0Or9"));
			Record rec_1 = collector.getRecords().get(1);
			Assert.assertEquals("true", rec_1.getFirstValue("isContainName_1"));
			Assert.assertEquals("false", rec_1.getFirstValue("isContainAge0Or9"));
		}
	}

	@Test
	public void java() throws Exception {
		morphline = createMorphline("conf/java");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals(Integer.valueOf(100), (Integer) rec_0.getFirstValue("id"));
		}
	}

	@Test
	public void log() throws Exception {
		morphline = createMorphline("conf/log");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
		}
	}

	@Test
	public void not() throws Exception {
		morphline = createMorphline("conf/ifNotContains");
		File file = new File(RESOURCES_DIR + "/data/csv.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("false", rec_0.getFirstValue("isContainName_1"));
			Assert.assertEquals("true", rec_0.getFirstValue("isContainAge0Or9"));
			Record rec_1 = collector.getRecords().get(1);
			Assert.assertEquals("true", rec_1.getFirstValue("isContainName_1"));
			Assert.assertEquals("false", rec_1.getFirstValue("isContainAge0Or9"));
		}
	}

	@Test
	public void removeFields() throws Exception {
		morphline = createMorphline("conf/removeFields");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals(
					"{_attachment_body=[{\"docId\":0,\"bool\":true,\"short\":32767,\"int\":2147483647,\"long\":9223372036854775807,\"double\":1.7976931348623157E308,\"date\":\"1001-01-01T01:01:01.001Z\",\"date_array\":[\"1101-01-01T01:01:01.001Z\",\"1201-01-01T01:01:01.001Z\",\"1301-01-01T01:01:01.001Z\"],\"text\":\"中文測試\",\"text_array\":[\"中文測試A0\",\"中文測試B0\",\"中文測試C0\"],\"object_array\":[{\"url\":\"http://A\"},{\"url\":\"http://B\"},{\"url\":\"http://C\"}]}], _attachment_mimetype=[json/java+memory], bool_b=[true], date_dt=[1001-01-01T01:01:01.001Z], date_dts=[1101-01-01T01:01:01.001Z, 1201-01-01T01:01:01.001Z, 1301-01-01T01:01:01.001Z], double_d=[1.7976931348623157E308], id=[0], int_i=[2147483647], text_t=[中文測試], text_txt=[中文測試A0, 中文測試B0, 中文測試C0], urls_ss=[http://A, http://B, http://C]}",
					rec_0.toString());
		}
	}

	@Test
	public void removeValues() throws Exception {
		morphline = createMorphline("conf/removeValues");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertNull(rec_0.getFirstValue("int_i"));
			Assert.assertNull(rec_0.getFirstValue("text_t"));
			Assert.assertEquals("[中文測試A0, 中文測試C0]", rec_0.get("text_txt").toString());
		}
	}

	@Test
	public void replaceValues() throws Exception {
		morphline = createMorphline("conf/replaceValues");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("replaced_value", rec_0.getFirstValue("int_i"));
			Assert.assertEquals("replaced_value", rec_0.getFirstValue("text_t"));
			Assert.assertEquals("replaced_value", rec_0.get("text_txt").get(1));
		}
	}

	@Test
	public void sample() throws Exception {
		morphline = createMorphline("conf/sample");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(1, collector.getRecords().size());
		}
	}

	@Test
	public void setValues() throws Exception {
		morphline = createMorphline("conf/setValues");
		File file = new File(RESOURCES_DIR + "/data/csv.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("added string", rec_0.getFirstValue("added_string"));
			Assert.assertEquals("[item 0, item 1, item 2]", rec_0.get("added_array").toString());
			Assert.assertEquals("na\nme_0", rec_0.getFirstValue("copied_name"));
			Assert.assertEquals("0", rec_0.getFirstValue("copied_age"));
		}
	}

	@Test
	public void split() throws Exception {
		morphline = createMorphline("conf/split");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("1001-01-01T01", rec_0.getFirstValue("first_token").toString());
			Assert.assertEquals("01", rec_0.getFirstValue("second_token").toString());
			Assert.assertEquals("01.001Z", rec_0.getFirstValue("third_token").toString());
		}
	}

	@Test
	public void splitKeyValue() throws Exception {
		morphline = createMorphline("conf/splitKeyValue");
		File file = new File(RESOURCES_DIR + "/data/splitKeyValue.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("v", rec_0.getFirstValue("prefix_k"));
		}
	}

	@Test
	public void startReportingMetricsToCSV() throws Exception {
		morphline = createMorphline("conf/startReportingMetricsToCSV");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
		}
	}

	@Test
	public void startReportingMetricsToSLF4J() throws Exception {
		morphline = createMorphline("conf/startReportingMetricsToSLF4J");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(3, collector.getRecords().size());
		}
	}

	@Test
	public void toByteArray() throws Exception {
		morphline = createMorphline("conf/toByteArray");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertNotEquals("中文測試", rec_0.getFirstValue("text_t"));
		}
	}

	@Test
	public void toString_() throws Exception {
		morphline = createMorphline("conf/toString");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("2147483647", (String) rec_0.getFirstValue("int_i"));
		}
	}

	@Test
	public void translate() throws Exception {
		morphline = createMorphline("conf/translate");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("first record", (String) rec_0.getFirstValue("id"));
		}
	}

	@Test
	public void tryRules() throws Exception {
		morphline = createMorphline("conf/tryRules");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("zero", (String) rec_0.getFirstValue("test_tryRules"));
			Record rec_1 = collector.getRecords().get(1);
			Assert.assertEquals("one", (String) rec_1.getFirstValue("test_tryRules"));
			Record rec_2 = collector.getRecords().get(2);
			Assert.assertEquals("default value", (String) rec_2.getFirstValue("test_tryRules"));
		}
	}
}
