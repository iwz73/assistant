package idv.hsiehpinghan.neo4jassistant.listener;

import org.neo4j.ogm.session.event.Event;
import org.neo4j.ogm.session.event.EventListener;

public class BasicEventListener implements EventListener {
	public static Boolean isOnPreSaveExecute;
	public static Boolean isOnPostSaveExecute;
	public static Boolean isOnPreDeleteExecute;
	public static Boolean isOnPostDeleteExecute;

	@Override
	public void onPreSave(Event event) {
		isOnPreSaveExecute = Boolean.TRUE;
	}

	@Override
	public void onPostSave(Event event) {
		isOnPostSaveExecute = Boolean.TRUE;
	}

	@Override
	public void onPreDelete(Event event) {
		isOnPreDeleteExecute = Boolean.TRUE;
	}

	@Override
	public void onPostDelete(Event event) {
		isOnPostDeleteExecute = Boolean.TRUE;
	}

}
