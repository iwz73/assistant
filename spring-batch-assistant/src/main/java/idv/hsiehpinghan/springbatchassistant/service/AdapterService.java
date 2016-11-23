package idv.hsiehpinghan.springbatchassistant.service;

import java.math.BigDecimal;

import idv.hsiehpinghan.springbatchassistant.vo.AdapterVo;

public class AdapterService {
	private static int index = 0;

	public AdapterVo adapterRead() {
		if (index > 3) {
			return null;
		}
		++index;
		AdapterVo vo = generateAdapterVo(index);
		System.err.println("AdapterService adapterRead : vo(" + vo + ")");
		return vo;
	}

	public AdapterVo adapterProcess(AdapterVo vo) {
		long longValue = vo.getLongValue();
		vo.setLongValue(longValue + 1);
		System.err.println("AdapterService adaptProcess : vo(" + vo + ")");
		return vo;
	}

	public void adaptWrite(AdapterVo vo) {
		System.err.println("AdapterService adaptWrite : vo(" + vo + ")");
	}

	private AdapterVo generateAdapterVo(long i) {
		AdapterVo vo = new AdapterVo();
		vo.setLongValue(i);
		vo.setStringValue("stringValue_" + i);
		vo.setBigDecimalValue(BigDecimal.valueOf(i));
		return vo;
	}
}
