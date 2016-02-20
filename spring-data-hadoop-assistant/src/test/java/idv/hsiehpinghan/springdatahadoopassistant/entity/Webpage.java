package idv.hsiehpinghan.springdatahadoopassistant.entity;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;

import org.apache.hadoop.hbase.util.Bytes;

public class Webpage {
	private byte[] rowKey;
	private P p;
	private F f;
	private S s;
	private ColumnFamily il;
	private ColumnFamily ol;
	private ColumnFamily h;
	private ColumnFamily mtdt;
	private ColumnFamily mk;

	public Webpage(byte[] rowKey, NavigableMap<byte[], byte[]> p,
			NavigableMap<byte[], byte[]> f, NavigableMap<byte[], byte[]> s,
			NavigableMap<byte[], byte[]> il, NavigableMap<byte[], byte[]> ol,
			NavigableMap<byte[], byte[]> h, NavigableMap<byte[], byte[]> mtdt,
			NavigableMap<byte[], byte[]> mk) {
		super();
		this.rowKey = rowKey;
		this.p = new P(p);
		this.f = new F(f);
		this.s = new S(s);
		this.il = new ColumnFamily(il);
		this.ol = new ColumnFamily(ol);
		this.h = new ColumnFamily(h);
		this.mtdt = new ColumnFamily(mtdt);
		this.mk = new ColumnFamily(mk);
	}

	public String getRowKey() {
		return Bytes.toString(rowKey);
	}

	public P getP() {
		return p;
	}

	public F getF() {
		return f;
	}

	public S getS() {
		return s;
	}

	public ColumnFamily getIl() {
		return il;
	}

	public ColumnFamily getOl() {
		return ol;
	}

	public ColumnFamily getH() {
		return h;
	}

	public ColumnFamily getMtdt() {
		return mtdt;
	}

	public ColumnFamily getMk() {
		return mk;
	}

	@Override
	public String toString() {
		return "Webpage [rowKey=" + getRowKey() + ", p=" + getP() + ", f="
				+ getF() + ", s=" + getS() + ", il=" + getIl() + ", ol="
				+ getOl() + ", h=" + getH() + ", mtdt=" + getMtdt() + ", mk="
				+ getMk() + "]";
	}

	public static class ColumnFamily {
		private NavigableMap<byte[], byte[]> cf;

		public ColumnFamily(NavigableMap<byte[], byte[]> cf) {
			super();
			this.cf = cf;
		}

		public NavigableMap<byte[], byte[]> getCf() {
			return cf;
		}

		public String getString() {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<byte[], byte[]> ent : cf.entrySet()) {
				String key = Bytes.toString(ent.getKey());
				String value = Bytes.toString(ent.getValue());
				sb.append(key).append(":").append(value)
						.append(System.lineSeparator());
			}
			return sb.toString();
		}

		public Map<String, String> getUrlMap() {
			Map<String, String> urlMap = new LinkedHashMap<String, String>(
					cf.size());
			for (Map.Entry<byte[], byte[]> ent : cf.entrySet()) {
				String key = Bytes.toString(ent.getKey());
				String value = Bytes.toString(ent.getValue());
				urlMap.put(key, value);
			}
			return urlMap;
		}
	}

	public static class P {
		private NavigableMap<byte[], byte[]> cf;

		public P(NavigableMap<byte[], byte[]> cf) {
			super();
			this.cf = cf;
		}

		public String getTitle() {
			byte[] bytes = cf.get(Bytes.toBytes("t"));
			return bytes == null ? null : Bytes.toString(bytes);
		}

		public String getText() {
			byte[] bytes = cf.get(Bytes.toBytes("c"));
			return bytes == null ? null : Bytes.toString(bytes);
		}

		public Byte getParseStatus() {
			byte[] bytes = cf.get(Bytes.toBytes("st"));
			return bytes == null ? null : bytes[0];
		}

		public String getSignature() {
			byte[] bytes = cf.get(Bytes.toBytes("sig"));
			if (bytes == null) {
				return null;
			}
			int size = bytes.length;
			char[] chars = new char[size];
			for (int i = 0; i < size; ++i) {
				chars[i] = (char) bytes[i];
			}
			return String.valueOf(chars);
		}

		public String getPrevSignature() {
			byte[] bytes = cf.get(Bytes.toBytes("psig"));
			return bytes == null ? null : Bytes.toString(bytes);
		}

		@Override
		public String toString() {
			return "P [title=" + getTitle() + ", text=" + getText()
					+ ", parseStatus=" + getParseStatus() + ", signature="
					+ getSignature() + ", prevSignature=" + getPrevSignature()
					+ "]";
		}

	}

	public static class F {
		private NavigableMap<byte[], byte[]> cf;

		public F(NavigableMap<byte[], byte[]> cf) {
			super();
			this.cf = cf;
		}

		public String getBaseUrl() {
			byte[] bytes = cf.get(Bytes.toBytes("bas"));
			return bytes == null ? null : Bytes.toString(bytes);
		}

		public Integer getStatus() {
			byte[] bytes = cf.get(Bytes.toBytes("st"));
			return bytes == null ? null : Bytes.toInt(bytes);
		}

		public Date getPrevFetchTime() {
			byte[] bytes = cf.get(Bytes.toBytes("pts"));
			return bytes == null ? null : new Date(Bytes.toLong(bytes));
		}

		public Date getFetchTime() {
			byte[] bytes = cf.get(Bytes.toBytes("ts"));
			return bytes == null ? null : new Date(Bytes.toLong(bytes));
		}

		public Integer getFetchInterval() {
			byte[] bytes = cf.get(Bytes.toBytes("fi"));
			return bytes == null ? null : Bytes.toInt(bytes);
		}

		public Integer getRetriesSinceFetch() {
			byte[] bytes = cf.get(Bytes.toBytes("rsf"));
			return bytes == null ? null : Bytes.toInt(bytes);
		}

		public String getReprUrl() {
			byte[] bytes = cf.get(Bytes.toBytes("rpr"));
			return bytes == null ? null : Bytes.toString(bytes);
		}

		public String getContent() {
			byte[] bytes = cf.get(Bytes.toBytes("cnt"));
			return bytes == null ? null : Bytes.toString(bytes);
		}

		public String getContentType() {
			byte[] bytes = cf.get(Bytes.toBytes("typ"));
			return bytes == null ? null : Bytes.toString(bytes);
		}

		public Byte getProtocolStatus() {
			byte[] bytes = cf.get(Bytes.toBytes("prot"));
			return bytes == null ? null : bytes[0];
		}

		public Date getModifiedTime() {
			byte[] bytes = cf.get(Bytes.toBytes("mod"));
			if (bytes == null) {
				return null;
			}
			long l = Bytes.toLong(bytes);
			return l == 0L ? null : new Date(l);
		}

		public Date getPrevModifiedTime() {
			byte[] bytes = cf.get(Bytes.toBytes("pmod"));
			if (bytes == null) {
				return null;
			}
			long l = Bytes.toLong(bytes);
			return l == 0L ? null : new Date(l);
		}

		public String getBatchId() {
			byte[] bytes = cf.get(Bytes.toBytes("bid"));
			return bytes == null ? null : Bytes.toString(bytes);
		}

		@Override
		public String toString() {
			return "F [baseUrl=" + getBaseUrl() + ", status=" + getStatus()
					+ ", prevFetchTime=" + getPrevFetchTime() + ", fetchTime="
					+ getFetchTime() + ", fetchInterval=" + getFetchInterval()
					+ ", retriesSinceFetch=" + getRetriesSinceFetch()
					+ ", reprUrl=" + getReprUrl() + ", content=" + getContent()
					+ ", contentType=" + getContentType() + ", protocolStatus="
					+ getProtocolStatus() + ", modifiedTime="
					+ getModifiedTime() + ", prevModifiedTime="
					+ getPrevModifiedTime() + ", batchId=" + getBatchId() + "]";
		}
	}

	public static class S {
		private NavigableMap<byte[], byte[]> cf;

		public S(NavigableMap<byte[], byte[]> cf) {
			super();
			this.cf = cf;
		}

		public Float getScore() {
			byte[] bytes = cf.get(Bytes.toBytes("s"));
			return bytes == null ? null : Bytes.toFloat(bytes);
		}

		@Override
		public String toString() {
			return "S [score=" + getScore() + "]";
		}

	}

}
