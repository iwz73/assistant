package idv.hsiehpinghan.phantomjsdriverassistant.utility;

import java.lang.reflect.Constructor;

public class VoidUtility {
	static {
		Constructor<Void> constructor;
		Void v = null;
		try {
			constructor = Void.class.getDeclaredConstructor();
			constructor.setAccessible(true);
			v = constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		VOID = v;
	}
	public static final Void VOID;
}