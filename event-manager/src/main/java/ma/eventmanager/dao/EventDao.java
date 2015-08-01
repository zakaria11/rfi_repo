package ma.eventmanager.dao;

import java.util.List;

import ma.eventmanager.entitys.Event;

public interface EventDao {

	void add(Event event);

	List<Event> list();

	void delete(Integer eventId);

	Event retreivEvent(Integer eventId);

	void update(Event event);

}
