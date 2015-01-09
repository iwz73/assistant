package idv.hsiehpinghan.hbaseassistant.utility;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

public class PrintUtility {
	public static void print(byte[] bytes) {
		for (byte b : bytes) {
			System.err.print(b);
		}
		System.err.println();
	}

	public static void print(Map<String, BigDecimal> map) {
		for (Map.Entry<String, BigDecimal> entry : map.entrySet()) {
			System.err.println(entry.getKey() + "/" + entry.getValue());
		}
	}

	public static void print(String[] strArr) {
		for (String s : strArr) {
			System.err.println(s);
		}
	}

	public static void print(Vector<?> vec) {
		for (int i = 0, size = vec.size(); i < size; ++i) {
			System.err.println(vec.get(i));
		}
	}

	public static void print(Hashtable<?, ?> hashTable) {
		Enumeration<?> en = hashTable.keys();
		while (en.hasMoreElements()) {
			Object key = en.nextElement();
			Object value = hashTable.get(key);
			System.err.println(key + " / " + value);
		}
	}

}
