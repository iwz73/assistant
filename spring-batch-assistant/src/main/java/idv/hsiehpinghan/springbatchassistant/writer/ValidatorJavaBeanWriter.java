package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import idv.hsiehpinghan.springbatchassistant.vo.ValidatorJavaBeanVo;

public class ValidatorJavaBeanWriter implements ItemWriter<ValidatorJavaBeanVo> {

	@Override
	public void write(List<? extends ValidatorJavaBeanVo> vos) throws Exception {
		System.err.println("ValidatorWriter write items(" + vos + ")");
	}
}
