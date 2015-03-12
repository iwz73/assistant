package idv.hsiehpinghan.pigassistant.assistant;

import idv.hsiehpinghan.datetimeutility.utility.DateUtility;
import idv.hsiehpinghan.pigassistant.entity.TestTable2;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.RowKey;
import idv.hsiehpinghan.pigassistant.suit.TestngSuitSetting;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class PigAssistantTest {
	private String stockCode = "stockCode";
	private Date date = DateUtility.getDate(2015, 2, 3);
	private PigAssistant assistant;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		assistant = applicationContext.getBean(PigAssistant.class);
	}

	@Test
	public void runQuery() throws Exception {
		File targetDirectory = new File(FileUtils.getTempDirectory(),
				"runQuery");
		String dataName = "data";

		TestTable2 entity = new TestTable2();
		RowKey rowKey1 = entity.new RowKey(stockCode + 1, date, entity);

		RowKey rowKey3 = entity.new RowKey(stockCode + 3, date, entity);

		String query = dataName + " = load 'hbase://" + entity.getTableName()
				+ "' using org.apache.pig.backend.hadoop.hbase.HBaseStorage('"
				+ entity.getAFamily().getColumnFamilyName()
				+ ":*', '-loadKey true -ignoreWhitespace false -gt " + rowKey1.getHexString() + " -lt " + rowKey3.getHexString() + "') as (id, "
				+ entity.getAFamily().getColumnFamilyName() + ":map[]);";

//	     stockCode2 20150203        [string#string]
//	    	     stockCode3 20150203        [string#string]
//
//
//	    	      stockCode 20150203        [string#string]
//	    	     stockCode1 20150203        [string#string]
//	    	     stockCode2 20150203        [string#string]
//	    	    		 
//	     stockCode2 20150203        [string#string]

		System.err.println(query);

		assistant.runQuery(query);
		assistant.store(targetDirectory, dataName);
		Assert.assertTrue(targetDirectory.list().length > 0);
		// FileUtils.deleteDirectory(targetDirectory);
	}
}
