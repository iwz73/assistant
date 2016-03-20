package idv.hsiehpinghan.jmxassistant.mbean;

import javax.management.Notification;
import javax.management.NotificationListener;

public class MbeanNotificationListener implements NotificationListener {

	@Override
	public void handleNotification(Notification notification, Object handback) {
		System.err
				.println("MbeanNotificationListener::handleNotification test.");
	}

}