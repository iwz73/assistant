package idv.hsiehpinghan.springmvcassistant.converter;

import idv.hsiehpinghan.springmvcassistant.vo.TsvVo;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class TsvHttpMessageConverter extends
		AbstractHttpMessageConverter<TsvVo> {
	public static final MediaType MEDIA_TYPE = new MediaType("text",
			"tab-separated-values", Charset.forName("utf-8"));

	public TsvHttpMessageConverter() {
		super(MEDIA_TYPE);
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return TsvVo.class.equals(clazz);
	}

	@Override
	protected TsvVo readInternal(Class<? extends TsvVo> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		// do nothing.
		return null;
	}

	@Override
	protected void writeInternal(TsvVo vo, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		outputMessage.getHeaders().setContentType(MEDIA_TYPE);
		outputMessage.getHeaders().set(
				"Content-Disposition",
				"attachment; filename=\"" + System.currentTimeMillis()
						+ ".tsv\"");
		OutputStream out = outputMessage.getBody();
		writeTitles(vo.getTitles(), out);
		writeDatas(vo.getDatas(), out);
		out.close();
	}

	private void writeTitles(String[] titles, OutputStream out)
			throws IOException {
		for (String title : titles) {
			out.write(title.getBytes());
			out.write('\t');
		}
		out.write('\n');
	}

	private void writeDatas(String[][] datas, OutputStream out)
			throws IOException {
		for (String[] row : datas) {
			for (String col : row) {
				out.write(col.getBytes());
				out.write('\t');
			}
			out.write('\n');
		}
	}

}
