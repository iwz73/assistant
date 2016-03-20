package idv.hsiehpinghan.springdatahadoopassistant.filter;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.KeyValueUtil;
import org.apache.hadoop.hbase.filter.FilterBase;

public class TestFilter extends FilterBase {
	/**
	 * reset the filter state before filtering a new row.
	 */
	@Override
	public void reset() throws IOException {
	}
	
	/**
	 * true means row scan is over; false means keep going.
	 */
	@Override
	public boolean filterAllRemaining() throws IOException {
		return true;
	}
	
	/**
	 * true means drop this row; false means include.
	 */
	@Override
	public boolean filterRowKey(byte[] buffer, int offset, int length)
			throws IOException {
		return true;
	}
	
	/**
	 * decides whether to include or exclude this Cell. See Filter.ReturnCode.
	 */
	@Override
	public ReturnCode filterKeyValue(Cell paramCell) throws IOException {
		return null;
	}

	/**
	 * if the Cell is included, let the filter transform the Cell.
	 */
	@Override
	public Cell transformCell(Cell v) throws IOException {
		return transform(KeyValueUtil.ensureKeyValue(v));
	}
	
	/**
	 * allows direct modification of the final list to be submitted
	 */
	@Override
	public void filterRowCells(List<Cell> ignored) throws IOException {
	}

	/**
	 * last chance to drop entire row based on the sequence of filter calls. Eg: filter a row if it doesn't contain a specified column.
	 */
	@Override
	public boolean filterRow() throws IOException {
		return false;
	}
	

}
