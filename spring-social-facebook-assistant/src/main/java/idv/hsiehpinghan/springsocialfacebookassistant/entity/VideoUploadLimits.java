package idv.hsiehpinghan.springsocialfacebookassistant.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VideoUploadLimits implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Length
	 */
	@Column(name = "video_upload_limits_length")
	private long length;
	/**
	 * size
	 */
	@Column(name = "video_upload_limits_size")
	private long size;

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
