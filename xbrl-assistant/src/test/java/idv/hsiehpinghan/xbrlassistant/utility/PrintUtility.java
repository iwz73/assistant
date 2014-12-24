package idv.hsiehpinghan.xbrlassistant.utility;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import jcx.xbrl.data.XbrlElement;

public class PrintUtility {
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

	public static void print(XbrlElement[] elements) {
		for (int i = 0, size = elements.length; i < size; ++i) {
			XbrlElement ele = elements[i];
			System.err.println(ele.getID() + " / " + ele.getContext().getID()
					+ " / " + ele.getValue());
		}
	}
}
