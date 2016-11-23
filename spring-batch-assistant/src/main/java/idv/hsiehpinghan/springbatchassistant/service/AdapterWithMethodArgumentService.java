package idv.hsiehpinghan.springbatchassistant.service;

import java.math.BigDecimal;

import idv.hsiehpinghan.springbatchassistant.vo.AdapterVo;

public class AdapterWithMethodArgumentService {
	private static int index = 0;

	public AdapterVo adapterRead() {
		if (index > 3) {
			return null;
		}
		++index;
		AdapterVo vo = generateAdapterVo(index);
		System.err.println("AdapterWithMethodArgumentService adapterRead : vo(" + vo + ")");
		return vo;
	}

	public void adaptWrite(long longValue, String stringValue, BigDecimal bigDecimalValue) {
		System.err.println("AdapterWithMethodArgumentService write : longValue(" + longValue + "),stringValue(" + stringValue
				+ "), bigDecimalValue(" + bigDecimalValue + ")");
	}

	private AdapterVo generateAdapterVo(long i) {
		AdapterVo vo = new AdapterVo();
		vo.setLongValue(i);
		vo.setStringValue("stringValue_" + i);
		vo.setBigDecimalValue(BigDecimal.valueOf(i));
		return vo;
	}
}
