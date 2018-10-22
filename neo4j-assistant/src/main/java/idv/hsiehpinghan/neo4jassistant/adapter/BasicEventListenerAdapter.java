package idv.hsiehpinghan.neo4jassistant.adapter;

import org.neo4j.ogm.session.event.Event;
import org.neo4j.ogm.session.event.EventListenerAdapter;

public class BasicEventListenerAdapter extends EventListenerAdapter {
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
