package idv.hsiehpinghan.kitemorphlinesassistant.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.kitesdk.morphline.api.AbstractMorphlineTest;
import org.kitesdk.morphline.api.Record;
import org.kitesdk.morphline.base.Fields;

public class KiteMorphlinesCoreStdioTest extends AbstractMorphlineTest {

//	@Test
	public void readClob() throws Exception {
		morphline = createMorphline("conf/readClob");
		File file = new File(RESOURCES_DIR + "/data/clob.txt");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(collector.getRecords().size(), 1);
			Record rec = collector.getRecords().get(0);
			Assert.assertEquals("abcde12345", (String) rec.getFirstValue("message"));
		}
	}
	
//	@Test
	public void readCSV() throws Exception {
		morphline = createMorphline("conf/readCSV");
		File file = new File(RESOURCES_DIR + "/data/csv.csv");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(collector.getRecords().size(), 2);
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("na\nme_0", (String) rec_0.getFirstValue("name"));
			Assert.assertEquals("0", (String) rec_0.getFirstValue("age"));
			Record rec_1 = collector.getRecords().get(1);
			Assert.assertEquals("name_1", (String) rec_1.getFirstValue("name"));
			Assert.assertEquals("1", (String) rec_1.getFirstValue("age"));
		}
	}
	
//	@Test
	public void readLine() throws Exception {
		morphline = createMorphline("conf/readLine");
		File file = new File(RESOURCES_DIR + "/data/readLine.txt");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(collector.getRecords().size(), 2);
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("first line", (String) rec_0.getFirstValue("message"));
			Record rec_1 = collector.getRecords().get(1);
			Assert.assertEquals("second line", (String) rec_1.getFirstValue("message"));
		}
	}

	@Test
	public void readMultiLine() throws Exception {
		morphline = createMorphline("conf/readMultiLine");
		File file = new File(RESOURCES_DIR + "/data/readMultiLine.txt");
		try (InputStream inputStream = new FileInputStream(file);) {
			Record record = new Record();
			record.put(Fields.ATTACHMENT_BODY, inputStream);
			record.put(Fields.ATTACHMENT_MIME_TYPE, "text/plain");
			assertTrue(morphline.process(record));
			assertEquals(collector.getRecords().size(), 2);
			Record rec_0 = collector.getRecords().get(0);
			Assert.assertEquals("start_line_0\n001\n002\n003", (String) rec_0.getFirstValue("message"));
			Record rec_1 = collector.getRecords().get(1);
			Assert.assertEquals("start_line_1\n101\n102\n103", (String) rec_1.getFirstValue("message"));
		}
	}
}
