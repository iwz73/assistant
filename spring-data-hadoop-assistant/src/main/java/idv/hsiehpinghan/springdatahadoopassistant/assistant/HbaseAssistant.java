package idv.hsiehpinghan.springdatahadoopassistant.assistant;

import idv.hsiehpinghan.springdatahadoopassistant.entity.HbaseEntity;

import java.util.TreeSet;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Component;

@Component
public class HbaseAssistant {
	@Autowired
	private HbaseTemplate hbaseTemplate;

	public TreeSet<HbaseEntity> scan(String tableName) {
		return scan(tableName, null);
	}

	public TreeSet<HbaseEntity> scan(String tableName, Filter filter) {

		return hbaseTemplate.execute(tableName,
				new TableCallback<TreeSet<HbaseEntity>>() {
					@Override
					public TreeSet<HbaseEntity> doInTable(
							HTableInterface tableInterface) throws Throwable {
						
						
						System.err.println("in !!!");
						
						
						
						Scan scan = new Scan();
						if (filter != null) {
							scan.setFilter(filter);
						}
						ResultScanner scanner = tableInterface.getScanner(scan);
						TreeSet<HbaseEntity> entities = new TreeSet<HbaseEntity>();
						for (Result result : scanner) {
							HbaseEntity entity = new HbaseEntity();
							Cell baseUrlCell = result.getColumnLatestCell(
									Bytes.toBytes("f"), Bytes.toBytes("bas"));
							entity.setBaseUrl(Bytes.toString(baseUrlCell
									.getValueArray()));
							entities.add(entity);
						}
						return entities;
					}
				});
	}

	// public List<HbaseEntity> find(String tableName, String columnFamily) {
	// final byte[] COLUMN_FAMILY = Bytes.toBytes(columnFamily);
	// final byte[] BASE_URL = Bytes.toBytes("bas");
	// return hbaseTemplate.find(tableName, columnFamily,
	// new RowMapper<HbaseEntity>() {
	// @Override
	// public HbaseEntity mapRow(Result result, int rowNum)
	// throws Exception {
	// HbaseEntity entity = new HbaseEntity();
	// entity.setBaseUrl(Bytes.toString(result.getValue(
	// COLUMN_FAMILY, BASE_URL)));
	// return entity;
	// }
	// });
	// }
}
