package idv.hsiehpinghan.jmxassistant.main;

import idv.hsiehpinghan.jmxassistant.mbean.Mbean;
import idv.hsiehpinghan.jmxassistant.mbean.MbeanNotificationListener;
import idv.hsiehpinghan.jmxassistant.mxbean.Mxbean;
import idv.hsiehpinghan.jmxassistant.mxbean.MxbeanNotificationListener;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class Main {
	public static void main(String[] args) throws Exception {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		server.registerMBean(generateMbean(), new ObjectName(Mbean.M_BEAN_NAME));
		server.registerMBean(generateMxbean(), new ObjectName(
				Mxbean.MX_BEAN_NAME));
		System.out.println("Waiting for requests....");
		Thread.sleep(Long.MAX_VALUE);
	}

	private static Mbean generateMbean() {
		Mbean mBean = new Mbean();
		MbeanNotificationListener listener = new MbeanNotificationListener();
		mBean.addNotificationListener(listener, null, null);
		return mBean;
	}

	private static Mxbean generateMxbean() {
		Mxbean mxbean = new Mxbean();
		MxbeanNotificationListener listener = new MxbeanNotificationListener();
		mxbean.addNotificationListener(listener, null, null);
		return mxbean;
	}
}
