package idv.hsiehpinghan.hbaseassistant.repository;

import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.assistant.HbaseAssistant;
import idv.hsiehpinghan.hbaseassistant.entity.DemoTable;
import idv.hsiehpinghan.hbaseassistant.entity.DemoTable.RowKey;
import idv.hsiehpinghan.hbaseassistant.utility.ByteConvertUtility;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.FuzzyRowFilter;
import org.apache.hadoop.hbase.filter.KeyOnlyFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DemoTableRepository extends RepositoryBase {
	@Autowired
	private HbaseAssistant hbaseAssistant;

	@Override
	public Class<? extends HBaseTable> getTargetTableClass() {
		return DemoTable.class;
	}

	public DemoTable generateEntity(String stockCode, Date date) {
		DemoTable entity = new DemoTable();
		generateRowKey(stockCode, date, entity);
		return entity;
	}

	public DemoTable get(String stockCode, Date date)
			throws IllegalAccessException, NoSuchMethodException,
			SecurityException, InstantiationException,
			IllegalArgumentException, InvocationTargetException, IOException {
		HBaseRowKey rowKey = getRowKey(stockCode, date);
		return (DemoTable) hbaseAssistant.get(rowKey);
	}

	public TreeSet<DemoTable> fuzzyScan(String stockCode, Date date) {
		DemoTable.RowKey rowKey = (DemoTable.RowKey) getRowKey(stockCode, date);
		List<Pair<byte[], byte[]>> fuzzyKeysData = new ArrayList<Pair<byte[], byte[]>>();
		Pair<byte[], byte[]> pair = new Pair<byte[], byte[]>(rowKey.getBytes(),
				rowKey.getFuzzyBytes(stockCode, date));
		fuzzyKeysData.add(pair);
		FuzzyRowFilter fuzzyRowFilter = new FuzzyRowFilter(fuzzyKeysData);
		@SuppressWarnings("unchecked")
		TreeSet<DemoTable> demoTables = (TreeSet<DemoTable>) (Object) hbaseAssistant
				.scan(getTargetTableClass(), fuzzyRowFilter);
		return demoTables;
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

	public DemoTable getWithSimpleFamilyOnly(String stockCode, Date date) {
		DemoTable entity = generateEntity(stockCode, date);
		RowFilter rowFilter = getRowFilter(entity);
		FamilyFilter familyFilter = getFamilyFilter("simpleFamily");
		FilterList filterList = new FilterList(rowFilter, familyFilter);
		TreeSet<HBaseTable> entities = hbaseAssistant.scan(DemoTable.class,
				filterList);
		if (entities.size() <= 0) {
			return null;
		}
		return (DemoTable) entities.first();
	}

	public TreeSet<DemoTable> scanWithSimpleFamilyOnly() {
		FamilyFilter familyFilter = getFamilyFilter("simpleFamily");
		@SuppressWarnings("unchecked")
		TreeSet<DemoTable> entities = (TreeSet<DemoTable>) (Object) hbaseAssistant
				.scan(DemoTable.class, familyFilter);
		return entities;
	}

	public DemoTable getWithCombinedFamilyOnly(String stockCode, Date date) {
		DemoTable entity = generateEntity(stockCode, date);
		RowFilter rowFilter = getRowFilter(entity);
		FamilyFilter familyFilter = getFamilyFilter("combinedFamily");
		FilterList filterList = new FilterList(rowFilter, familyFilter);
		TreeSet<HBaseTable> entities = hbaseAssistant.scan(DemoTable.class,
				filterList);
		if (entities.size() <= 0) {
			return null;
		}
		return (DemoTable) entities.first();
	}

	public TreeSet<DemoTable> scanWithCombinedFamilyOnly() {
		FamilyFilter familyFilter = getFamilyFilter("combinedFamily");
		@SuppressWarnings("unchecked")
		TreeSet<DemoTable> entities = (TreeSet<DemoTable>) (Object) hbaseAssistant
				.scan(DemoTable.class, familyFilter);
		return entities;
	}

	public DemoTable getWithComplexFamilyOnly(String stockCode, Date date) {
		DemoTable entity = generateEntity(stockCode, date);
		RowFilter rowFilter = getRowFilter(entity);
		FamilyFilter familyFilter = getFamilyFilter("complexFamily");
		FilterList filterList = new FilterList(rowFilter, familyFilter);
		TreeSet<HBaseTable> entities = hbaseAssistant.scan(DemoTable.class,
				filterList);
		if (entities.size() <= 0) {
			return null;
		}
		return (DemoTable) entities.first();
	}

	public TreeSet<DemoTable> scanWithComplexFamilyOnly() {
		FamilyFilter familyFilter = getFamilyFilter("complexFamily");
		@SuppressWarnings("unchecked")
		TreeSet<DemoTable> entities = (TreeSet<DemoTable>) (Object) hbaseAssistant
				.scan(DemoTable.class, familyFilter);
		return entities;
	}

	private RowFilter getRowFilter(DemoTable entity) {
		return new RowFilter(CompareFilter.CompareOp.EQUAL,
				new BinaryComparator(entity.getRowKey().getBytes()));
	}

	private FamilyFilter getFamilyFilter(String columnFamilyName) {
		return new FamilyFilter(CompareFilter.CompareOp.EQUAL,
				new BinaryComparator(
						ByteConvertUtility.toBytes(columnFamilyName)));
	}

	@Override
	protected HbaseAssistant getHbaseAssistant() {
		return hbaseAssistant;
	}

	private HBaseRowKey getRowKey(String stockCode, Date date) {
		DemoTable entity = new DemoTable();
		generateRowKey(stockCode, date, entity);
		return entity.getRowKey();
	}

	private void generateRowKey(String stockCode, Date date, DemoTable entity) {
		entity.new RowKey(stockCode, date, entity);
	}
}
