package idv.hsiehpinghan.pigassistant.repository;

import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.assistant.HbaseAssistant;
import idv.hsiehpinghan.hbaseassistant.repository.RepositoryBase;
import idv.hsiehpinghan.pigassistant.entity.TestTable2;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.RowKey;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.apache.hadoop.hbase.filter.FuzzyRowFilter;
import org.apache.hadoop.hbase.filter.KeyOnlyFilter;
import org.apache.hadoop.hbase.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestTable2Repository extends RepositoryBase {
	@Autowired
	private HbaseAssistant hbaseAssistant;

	@Override
	public Class<? extends HBaseTable> getTargetTableClass() {
		return TestTable2.class;
	}

	public TestTable2 generateEntity(String stockCode, Date date) {
		TestTable2 entity = new TestTable2();
		generateRowKey(stockCode, date, entity);
		return entity;
	}

	public TestTable2 get(String stockCode, Date date)
			throws IllegalAccessException, NoSuchMethodException,
			SecurityException, InstantiationException,
			IllegalArgumentException, InvocationTargetException, IOException {
		HBaseRowKey rowKey = getRowKey(stockCode, date);
		return (TestTable2) hbaseAssistant.get(rowKey);
	}

	public TreeSet<TestTable2> fuzzyScan(String stockCode, Date date) {
		TestTable2.RowKey rowKey = (TestTable2.RowKey) getRowKey(stockCode,
				date);
		List<Pair<byte[], byte[]>> fuzzyKeysData = new ArrayList<Pair<byte[], byte[]>>();
		Pair<byte[], byte[]> pair = new Pair<byte[], byte[]>(rowKey.getBytes(),
				rowKey.getFuzzyBytes(stockCode, date));
		fuzzyKeysData.add(pair);
		FuzzyRowFilter fuzzyRowFilter = new FuzzyRowFilter(fuzzyKeysData);
		@SuppressWarnings("unchecked")
		TreeSet<TestTable2> testTable2s = (TreeSet<TestTable2>) (Object) hbaseAssistant
				.scan(getTargetTableClass(), fuzzyRowFilter);
		return testTable2s;
	}

	public int getRowAmount() {
		return hbaseAssistant.getRowAmount(getTargetTableClass());
	}

	public TreeSet<RowKey> getRowKeys() {
		TreeSet<HBaseTable> entities = hbaseAssistant.scan(
				getTargetTableClass(), new KeyOnlyFilter());
		TreeSet<RowKey> rowKeys = new TreeSet<RowKey>();
		for (HBaseTable entity : entities) {
			RowKey rowKey = (RowKey) entity.getRowKey();
			rowKeys.add(rowKey);
		}
		return rowKeys;
	}

	public boolean exists(String stockCode, Date date)
			throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException,
			InstantiationException, IOException {
		HBaseRowKey key = getRowKey(stockCode, date);
		return super.exists(key);
	}

	@Override
	protected HbaseAssistant getHbaseAssistant() {
		return hbaseAssistant;
	}

	private HBaseRowKey getRowKey(String stockCode, Date date) {
		TestTable2 entity = new TestTable2();
		generateRowKey(stockCode, date, entity);
		return entity.getRowKey();
	}

	private void generateRowKey(String stockCode, Date date, TestTable2 entity) {
		entity.new RowKey(stockCode, date, entity);
	}
}
