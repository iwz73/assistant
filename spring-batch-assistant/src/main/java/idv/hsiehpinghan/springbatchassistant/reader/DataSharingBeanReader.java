package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import idv.hsiehpinghan.springbatchassistant.vo.DataSharingBeanVo;

public class DataSharingBeanReader implements ItemReader<String> {
	private final static String[] ITEMS = new String[] { "0" };
	private static int index = 0;
	private DataSharingBeanVo vo;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		addAttribute();
		if (index >= ITEMS.length) {
			return null;
		}
		String item = ITEMS[index++];
		System.err.println("DataSharingBeanReader read item(" + item + ")");
		return item;
	}

	public DataSharingBeanVo getVo() {
		return vo;
	}

	public void setVo(DataSharingBeanVo vo) {
		this.vo = vo;
	}

	private void addAttribute() {
		vo.setLongValue(3L);
		vo.setStringValue("stringValue");
	}
}
