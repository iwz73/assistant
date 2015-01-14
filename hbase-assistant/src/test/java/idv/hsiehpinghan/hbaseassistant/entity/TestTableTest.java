package idv.hsiehpinghan.hbaseassistant.entity;

import idv.hsiehpinghan.hbaseassistant.entity.TestTable.TestFamily1.TestQualifier1;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.TestFamily1.TestValue1;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.TestFamily2.TestQualifier2;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.TestFamily2.TestValue2;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.TestRowKey;
import idv.hsiehpinghan.hbaseassistant.utility.HbaseEntityTestUtility;

import java.io.IOException;
import java.util.Date;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestTableTest {

	@BeforeClass
	public void beforeClass() throws IOException {
	}

	@Test
	public void toBytesFromBytes() {
		TestTable entity = new TestTable();
		// Test row key.
		TestRowKey key = entity.new TestRowKey(new Date(), "keyString1", 888,
				entity);
		HbaseEntityTestUtility.toBytesFromBytes(key);
		// Test testQualifier1.
		TestQualifier1 testQualifier1 = entity.getFamily1().new TestQualifier1(
				"qual1");
		HbaseEntityTestUtility.toBytesFromBytes(testQualifier1);
		// Test testValue1.
		TestValue1 testValue1 = entity.getFamily1().new TestValue1(new Date(),
				"valueString1", 111);
		HbaseEntityTestUtility.toBytesFromBytes(testValue1);
		// Test testQualifier2.
		TestQualifier2 testQualifier2 = entity.getFamily2().new TestQualifier2(
				"qual2");
		HbaseEntityTestUtility.toBytesFromBytes(testQualifier2);
		// Test testValue2.
		TestValue2 testValue2 = entity.getFamily2().new TestValue2(new Date(),
				"valueString2", 222);
		HbaseEntityTestUtility.toBytesFromBytes(testValue2);
	}
}
