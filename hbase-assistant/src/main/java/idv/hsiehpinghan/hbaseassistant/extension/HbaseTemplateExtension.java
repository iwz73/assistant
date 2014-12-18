package idv.hsiehpinghan.hbaseassistant.extension;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;

public class HbaseTemplateExtension extends HbaseTemplate {

	public HbaseTemplateExtension() {
		super();
	}

	public HbaseTemplateExtension(Configuration configuration) {
		super(configuration);
	}

	/**
	 * Get all version.
	 */
	public <T> T getAllVersion(String tableName, String rowName,
			final RowMapper<T> mapper) {
		return getAllVersion(tableName, rowName, null, null, mapper);
	}

	/**
	 * Get all version.
	 */
	public <T> T getAllVersion(String tableName, final String rowName,
			final String familyName, final String qualifier,
			final RowMapper<T> mapper) {
		return execute(tableName, new TableCallback<T>() {
			@Override
			public T doInTable(HTableInterface htable) throws Throwable {
				Get get = new Get(rowName.getBytes(getCharset()))
						.setMaxVersions();
				if (familyName != null) {
					byte[] family = familyName.getBytes(getCharset());

					if (qualifier != null) {
						get.addColumn(family, qualifier.getBytes(getCharset()));
					} else {
						get.addFamily(family);
					}
				}
				Result result = htable.get(get);
				return mapper.mapRow(result, 0);
			}
		});
	}
}
