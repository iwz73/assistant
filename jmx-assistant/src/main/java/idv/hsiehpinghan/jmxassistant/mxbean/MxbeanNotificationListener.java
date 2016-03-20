package idv.hsiehpinghan.jmxassistant.mxbean;

import javax.management.Notification;
import javax.management.NotificationListener;

public class MxbeanNotificationListener implements NotificationListener {

	@Override
	public void handleNotification(Notification notification, Object handback) {
		System.err
				.println("MxbeanNotificationListener::handleNotification test.");
	}

}