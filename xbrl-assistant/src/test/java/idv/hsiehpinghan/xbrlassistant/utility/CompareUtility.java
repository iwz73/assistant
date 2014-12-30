package idv.hsiehpinghan.xbrlassistant.utility;

import org.apache.commons.lang3.ArrayUtils;

public class CompareUtility {
	public static int getIndexOfDifferentChar(String s1, String s2) {
		char[] charArr_1 = s1.toCharArray();
		char[] charArr_2 = s2.toCharArray();
		int size_1 = charArr_1.length;
		int size_2 = charArr_2.length;
		int size = (size_1 > size_2 ? size_2 : size_1);
		for(int i = 0; i < size; ++i) {
			if(charArr_1[i] != charArr_2[i]) {
				String str_1 = new String(ArrayUtils.subarray(charArr_1, i, size_1));
				String str_2 = new String(ArrayUtils.subarray(charArr_2, i, size_2));
				System.err.println("str_1 : " + str_1);
				System.err.println("str_2 : " + str_2);
				return i;
			}
		}
		return size;
	}
}
