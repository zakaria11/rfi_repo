package ma.eventmanager.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import ma.evenetmanager.criteria.CriteriaModel;
import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Client;
import ma.eventmanager.entitys.Event;
import ma.eventmanager.entitys.Room;
import ma.eventmanager.entitys.Subscription;
import ma.eventmanager.entitys.User;
import ma.eventmanager.entitys.UserRole;

@Repository
public class EventManagerDaoImpl extends HibernateDaoSupport implements EventManagerDao{

	public void addEvent(Event event){
		event.setState("ACTIVATED");
		getHibernateTemplate().save(event);		
	}

	public List<Event> listEvents(int offset, Integer rows){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Event");
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		List<Event> list = (List<Event>) query.list();
		session.flush();
		session.close();
		return list;
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

	public void addUser(User user,List<String> roles){
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));		
		getHibernateTemplate().save(user);
		
		for(String role: roles){
			getHibernateTemplate().save(new UserRole(user,role));
		}

	}

	public Integer getUsersCount(){
		return DataAccessUtils.intResult(getHibernateTemplate().find("SELECT count(*) FROM User"));
	}

	public List<User> getUsers(int offset, Integer rows){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM User");
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		List<User> list = (List<User>) query.list();
		session.flush();
		session.close();
		return list;
	}

	public void addUser(User user){
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		getHibernateTemplate().save(user);	
	}

	public void editUser(User user){
		getHibernateTemplate().update(user);
		
	}

	public List<Room> getRooms(int offset, Integer rows)
	{		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Room");
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		List<Room> list = (List<Room>) query.list();
		session.flush();
		session.close();
		return list;
	}

	public void addRoom(Room room){
		getHibernateTemplate().save(room);		
	}

	public Integer getEventsCount(){
		return DataAccessUtils.intResult(getHibernateTemplate().find("SELECT count(*) FROM Event"));
	}

	public List<Client> listClients(int offset, Integer rows){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Client");
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		List<Client> list = (List<Client>) query.list();
		session.flush();
		session.close();
		return list;

	}

	public void addClient(Client client){
		getHibernateTemplate().save(client);
	}

	public List<User> getUsers(int offset, Integer rows, List<CriteriaModel> usedSearchFields){
		
		if(usedSearchFields == null && usedSearchFields.size() == 0){
			return getUsers(offset, rows);
		}else{
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(User.class);
			buildCiteria(usedSearchFields,criteria);
			return criteria.list();
		}

	}

	public List<Client> listClients(int offset, Integer rows, List<CriteriaModel> usedSearchFields){
		if(usedSearchFields == null && usedSearchFields.size() == 0){
			return listClients(offset, rows);
		}else{
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Client.class);
			buildCiteria(usedSearchFields,criteria);
			return criteria.list();
		}
	}

	private void buildCiteria(List<CriteriaModel> usedSearchFields, Criteria criteria){
		for(CriteriaModel criteriaModel : usedSearchFields){
			if(criteriaModel.getFieldValue() != null && criteriaModel.getFieldName() != null){
				criteria.add(Restrictions.like(criteriaModel.getFieldName(),"%"+criteriaModel.getFieldValue()+"%"));					
			}
		}
	}

	public Integer getClientsCount(){
		return DataAccessUtils.intResult(getHibernateTemplate().find("SELECT count(*) FROM Client"));
	}

	public List<Room> getRooms(int offset, Integer rows, List<CriteriaModel> usedSearchFields){
		if(usedSearchFields == null && usedSearchFields.size() == 0){
			return getRooms(offset, rows);
		}else{
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Room.class);
			buildCiteria(usedSearchFields,criteria);
			return criteria.list();
		}
	}

	public List<Event> listEvents(int offset, Integer rows, List<CriteriaModel> usedSearchFields){
		if(usedSearchFields == null && usedSearchFields.size() == 0){
			return listEvents(offset, rows);
		}else{
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Event.class);
			buildCiteria(usedSearchFields,criteria);
			return criteria.list();
		}
	}

	public Client retreivClient(Integer selectedClientId){
		return getHibernateTemplate().get(Client.class, selectedClientId);

	}

}
