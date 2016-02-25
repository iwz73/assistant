package idv.hsiehpinghan.springdatahadoopassistant.assistant;

import idv.hsiehpinghan.springdatahadoopassistant.entity.Webpage;

import java.util.Collection;
import java.util.NavigableMap;
import java.util.TreeSet;

import org.apache.hadoop.hbase.client.Get;
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
@SuppressWarnings("deprecation")
public class HbaseAssistant {
	@Autowired
	private HbaseTemplate hbaseTemplate;

	public Webpage get(String tableName, String rowKey, Filter filter) {
		return hbaseTemplate.execute(tableName, new TableCallback<Webpage>() {
			@Override
			public Webpage doInTable(HTableInterface tableInterface)
					throws Throwable {
				Get get = new Get(Bytes.toBytes(rowKey));
				get.setFilter(filter);
				Result result = tableInterface.get(get);
				return getWebpage(result);
			}
		});
	}

	public Collection<Webpage> scan(String tableName) {
		return scan(tableName, null);
	}

	public Collection<Webpage> scan(String tableName, Filter filter) {
		return hbaseTemplate.execute(tableName,
				new TableCallback<Collection<Webpage>>() {
					@Override
					public Collection<Webpage> doInTable(
							HTableInterface tableInterface) throws Throwable {
						Scan scan = new Scan();
						scan.setFilter(filter);
						ResultScanner scanner = tableInterface.getScanner(scan);
						Collection<Webpage> webpages = new TreeSet<Webpage>();
						for (Result result : scanner) {
							webpages.add(getWebpage(result));
						}
						return webpages;
					}
				});
	}

	private Webpage getWebpage(Result result) {
		byte[] key = result.getRow();
		NavigableMap<byte[], byte[]> p = result
				.getFamilyMap(Bytes.toBytes("p"));
		NavigableMap<byte[], byte[]> f = result
				.getFamilyMap(Bytes.toBytes("f"));
		NavigableMap<byte[], byte[]> s = result
				.getFamilyMap(Bytes.toBytes("s"));
		NavigableMap<byte[], byte[]> il = result.getFamilyMap(Bytes
				.toBytes("il"));
		NavigableMap<byte[], byte[]> ol = result.getFamilyMap(Bytes
				.toBytes("ol"));
		NavigableMap<byte[], byte[]> h = result
				.getFamilyMap(Bytes.toBytes("h"));
		NavigableMap<byte[], byte[]> mtdt = result.getFamilyMap(Bytes
				.toBytes("mtdt"));
		NavigableMap<byte[], byte[]> mk = result.getFamilyMap(Bytes
				.toBytes("mk"));
		if (p == null && f == null && s == null && il == null && ol == null
				&& h == null && mtdt == null && mk == null) {
			return null;
		}
		return new Webpage(key, p, f, s, il, ol, h, mtdt, mk);
	}

}
