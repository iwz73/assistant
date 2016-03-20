package idv.hsiehpinghan.jmxassistant.mbean;

import java.util.concurrent.atomic.AtomicLong;

import javax.management.AttributeChangeNotification;
import javax.management.NotificationBroadcasterSupport;

public class Mbean extends NotificationBroadcasterSupport implements MbeanMBean {
	public static final String M_BEAN_NAME = "idv.hsiehpinghan.jmxassistant.mbean:type=Mbean";
	private AtomicLong atomicLong = new AtomicLong(1);
	private int attribute;

	@Override
	public void setAttribute(int attribute) {
		Object source = this;
		long sequenceNumber = atomicLong.getAndIncrement();
		long timeStamp = System.currentTimeMillis();
		String msg = AttributeChangeNotification.ATTRIBUTE_CHANGE;
		String attributeName = "port";
		String attributeType = "java.lang.Integer";
		int oldValue = this.attribute;
		this.attribute = attribute;
		int newValue = this.attribute;
		AttributeChangeNotification notification = new AttributeChangeNotification(
				source, sequenceNumber, timeStamp, msg, attributeName,
				attributeType, oldValue, newValue);
		super.sendNotification(notification);
	}

	@Override
	public int getAttribute() {
		return attribute;
	}

}