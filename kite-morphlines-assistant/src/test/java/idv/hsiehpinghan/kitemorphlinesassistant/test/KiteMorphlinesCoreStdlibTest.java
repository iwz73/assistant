package idv.hsiehpinghan.kitemorphlinesassistant.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.kitesdk.morphline.api.AbstractMorphlineTest;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.Fields;

public class KiteMorphlinesCoreStdlibTest extends AbstractMorphlineTest {

//	@Test
	public void addCurrentTime() throws Exception {
		morphline = createMorphline("conf/addCurrentTime");
		File file = new File(RESOURCES_DIR + "/data/csv.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertTrue(((Long)rec_0.getFirstValue("timestamp")) <= System.currentTimeMillis());
		}
	}

//	@Test
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
	
//	@Test
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
	
//	@Test
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
	
	
	
	
	
	
	
//	@Test
	public void _if() throws Exception {
		morphline = createMorphline("conf/if");
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

}
