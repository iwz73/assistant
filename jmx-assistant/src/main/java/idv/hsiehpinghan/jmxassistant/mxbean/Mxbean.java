package idv.hsiehpinghan.jmxassistant.mxbean;

import java.util.concurrent.atomic.AtomicLong;

import javax.management.AttributeChangeNotification;
import javax.management.NotificationBroadcasterSupport;

public class Mxbean extends NotificationBroadcasterSupport implements
		MxbeanMXBean {
	public static final String MX_BEAN_NAME = "idv.hsiehpinghan.jmxassistant.mxbean:type=Mxbean";
	private AtomicLong atomicLong = new AtomicLong(1);
	private CompositeData compositeData;

	public Mxbean() {
		super();
		this.compositeData = new CompositeData(3, "string");
	}

	@Override
	public void setCompositeData(CompositeData compositeData) {
		synchronized (this.compositeData) {
			Object source = this;
			long sequenceNumber = atomicLong.getAndIncrement();
			long timeStamp = System.currentTimeMillis();
			String msg = AttributeChangeNotification.ATTRIBUTE_CHANGE;
			String attributeName = "port";
			String attributeType = "java.lang.Integer";
			CompositeData oldValue = this.compositeData;
			this.compositeData = compositeData;
			CompositeData newValue = this.compositeData;
			AttributeChangeNotification notification = new AttributeChangeNotification(
					source, sequenceNumber, timeStamp, msg, attributeName,
					attributeType, oldValue, newValue);
			super.sendNotification(notification);
		}
	}

	@Override
	public CompositeData getCompositeData() {
		synchronized (compositeData) {
			return compositeData;
		}
	}

}