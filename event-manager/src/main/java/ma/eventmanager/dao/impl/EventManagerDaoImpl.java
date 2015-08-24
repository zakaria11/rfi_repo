package ma.eventmanager.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Event;
import ma.eventmanager.entitys.Subscription;
import ma.eventmanager.entitys.User;
import ma.eventmanager.entitys.UserRole;

@Repository
public class EventManagerDaoImpl extends HibernateDaoSupport implements EventManagerDao{

	public void addEvent(Event event){
		event.setState("1");
		getHibernateTemplate().save(event);		
	}

	public List<Event> listEvents(){
		return getHibernateTemplate().find("from Event where state=1");	
	}

	public void deleteEvent(Integer eventId){
		Event event = new Event();
		event.setId(eventId);
		getHibernateTemplate().delete(event);
	}

	public Event retreivEvent(Integer eventId){
		return getHibernateTemplate().get(Event.class, eventId);
	}

	public void updateEvent(Event event){
		event.setState("1");
		getHibernateTemplate().update(event);
	}

	public void saveSubscription(Subscription subscription){
		getHibernateTemplate().save(subscription);
	}
	
	public User getUsers(String username){
		return (User) getHibernateTemplate().find("FROM User where username = '"+username+"'").get(0);
	}

	public void addUser(String login,String password,List<String> roles){
		User user = new User(login,new BCryptPasswordEncoder().encode(password));		
		getHibernateTemplate().save(user);
		
		for(String role: roles){
			getHibernateTemplate().save(new UserRole(user,role));
		}

	}

}
