package idv.hsiehpinghan.kitemorphlinesassistant.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.kitesdk.morphline.api.AbstractMorphlineTest;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.Fields;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class KiteMorphlinesJsonTest extends AbstractMorphlineTest {

	@Test
	public void readJson() throws Exception {
		morphline = createMorphline("conf/readJson");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(collector.getRecords().size(), 3);
			Record rec = collector.getRecords().get(0);
			ObjectNode objectNode = (ObjectNode)rec.getFirstValue("_attachment_body");
			Assert.assertEquals(0, objectNode.get("docId").asInt());
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void extractJsonPaths() throws Exception {
		morphline = createMorphline("conf/extractJsonPaths");
		File file = new File(RESOURCES_DIR + "/data/json.json");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(collector.getRecords().size(), 3);
			Record rec = collector.getRecords().get(0);
			Assert.assertEquals(Integer.valueOf(0), (Integer)rec.getFirstValue("id"));
			Assert.assertEquals(Boolean.TRUE, (Boolean)rec.getFirstValue("bool_b"));
			Assert.assertEquals(Integer.valueOf("32767"), (Integer)rec.getFirstValue("short_i"));
			Assert.assertEquals(Integer.valueOf("2147483647"), (Integer)rec.getFirstValue("int_i"));
			Assert.assertEquals(Long.valueOf("9223372036854775807"), (Long)rec.getFirstValue("long_l"));
			Assert.assertEquals(Double.valueOf("1.7976931348623157E308"), (Double)rec.getFirstValue("double_d"));
			Assert.assertEquals("1001-01-01T01:01:01.001Z", (String)rec.getFirstValue("date_dt"));
			List<String> dates = (List<String>)rec.get("date_dts");
			Assert.assertEquals("1101-01-01T01:01:01.001Z", dates.get(0));
			Assert.assertEquals("1201-01-01T01:01:01.001Z", dates.get(1));
			Assert.assertEquals("1301-01-01T01:01:01.001Z", dates.get(2));
			Assert.assertEquals("中文測試", (String)rec.getFirstValue("text_t"));
			List<String> texts = (List<String>)rec.get("text_txt");
			Assert.assertEquals("中文測試A0", texts.get(0));
			Assert.assertEquals("中文測試B0", texts.get(1));
			Assert.assertEquals("中文測試C0", texts.get(2));
			List<String> urls = (List<String>)rec.get("urls_ss");
			Assert.assertEquals("http://A", urls.get(0));
			Assert.assertEquals("http://B", urls.get(1));
			Assert.assertEquals("http://C", urls.get(2));
		}
	}
}
