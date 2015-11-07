package idv.hsiehpinghan.springmvcassistant.controller;

import idv.hsiehpinghan.springmvcassistant.vo.TsvVo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/httpMessageConverter")
public class HttpMessageConverterController {
	private final int ROW_SIZE = 5;
	private final int COLUMN_SIZE = 3;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "/httpMessageConverter/index";
	}

	@ResponseBody
	@RequestMapping(value = "/tsv", method = RequestMethod.GET)
	public TsvVo tsv() {
		return generateTsvVo();
	}

	private TsvVo generateTsvVo() {
		TsvVo vo = new TsvVo();
		buildTsvVoTitles(vo);
		buildTsvVoDatas(vo);
		return vo;
	}

	private void buildTsvVoTitles(TsvVo vo) {
		String[] titles = new String[COLUMN_SIZE];
		for (int i = 0; i < COLUMN_SIZE; ++i) {
			titles[i] = "title_" + i;
		}
		vo.setTitles(titles);
	}

	private void buildTsvVoDatas(TsvVo vo) {
		String[][] datas = new String[ROW_SIZE][COLUMN_SIZE];
		for (int row = 0; row < ROW_SIZE; ++row) {
			for (int col = 0; col < COLUMN_SIZE; ++col) {
				datas[row][col] = "data_(" + row + "," + col + ")";
			}
		}
		vo.setDatas(datas);
	}
}
