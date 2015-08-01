package ma.eventmanager.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import ma.eventmanager.dao.EventDao;
import ma.eventmanager.entitys.Event;

@Repository
public class EventDaoImpl extends HibernateDaoSupport implements EventDao{

	public void add(Event event){
		event.setState("1");
		getHibernateTemplate().save(event);		
	}

	public List<Event> list(){
		return getHibernateTemplate().find("from Event where state=1");	
	}

	public void delete(Integer eventId){
		Event event = new Event();
		event.setId(eventId);
		getHibernateTemplate().delete(event);
	}

	public Event retreivEvent(Integer eventId){
		return getHibernateTemplate().get(Event.class, eventId);
	}

	public void update(Event event){
		event.setState("1");
		getHibernateTemplate().update(event);
	}

}
