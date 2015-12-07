package ma.eventmanager.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sun.org.apache.bcel.internal.generic.NEW;

import ma.evenetmanager.criteria.CriteriaModel;
import ma.eventmanager.dao.EventManagerDao;
import ma.eventmanager.entitys.Client;
import ma.eventmanager.entitys.Event;
import ma.eventmanager.entitys.Room;
import ma.eventmanager.entitys.State;
import ma.eventmanager.entitys.Subscription;
import ma.eventmanager.entitys.Tag;
import ma.eventmanager.entitys.User;
import ma.eventmanager.entitys.UserRole;
import ma.eventmanager.model.Attribute;
import ma.eventmanager.model.EntityGroup;
import ma.eventmanager.model.EntityGroupedBy;
import ma.eventmanager.service.ReferenceService;

@Repository
public class EventManagerDaoImpl extends HibernateDaoSupport implements EventManagerDao{

	private static final String DATEFORMAT = "%Y-%c-%e";
	private static int MAX_BORDER_GROUPED_EVENTS_COUNT = 5;

	public void addEvent(Event event){
		event.setRemainingPlaces(event.getPlaces());
		getHibernateTemplate().save(event);		
	}

	public List<Event> listEvents(int offset, Integer rows,Date from,Date to,String sourceView){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM Event WHERE date between :from AND :to";
		
		if("CHOICE".equals(sourceView)){
			hql += " and state.id IN (1)";
		}		
		
		Query query = session.createQuery(hql);
		query.setDate("from", from);
		if(to == null){
			Calendar cal = Calendar.getInstance(); 
			cal.add(Calendar.YEAR, 7000);
			to = cal.getTime();
		}
		query.setDate("to",to);
		
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
	public void deleteRoom(Integer roomId){
		Room room = new Room();
		room.setId(roomId);
		getHibernateTemplate().delete(room);
	}

	public void deleteClient(Integer clientId){
		Client client = new Client();
		client.setId(clientId);
		getHibernateTemplate().delete(client);
	}

	
	public void updateEvent(Event event){
		getHibernateTemplate().update(event);
	}

	public void updateRoom(Room room){
		getHibernateTemplate().update(room);
	}
	
	public void updateClient(Client client){
		getHibernateTemplate().update(client);
	}
	
	public void updateUser(User user){
		getHibernateTemplate().update(user);
	}

	public void saveSubscription(Subscription subscription){
		getHibernateTemplate().save(subscription);
	}

	public void saveClient(Client client){
		getHibernateTemplate().save(client);
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
		if(user.getPassword() != null){
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));	
		}
		getHibernateTemplate().save(user);	
	}

	public void editUser(User user){
		getHibernateTemplate().update(user);
		
	}


	public Integer addRoom(Room room){
		 return (Integer) getHibernateTemplate().save(room);		
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

	public Integer addClient(Client client){
		return (Integer) getHibernateTemplate().save(client);
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
			if(criteriaModel.getFieldValue() != null && !"".equals(criteriaModel.getFieldValue()) && criteriaModel.getFieldName() != null){
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


	public List<State> getStates(int offset, Integer rows, List<CriteriaModel> usedSearchFields){
		if(usedSearchFields == null && usedSearchFields.size() == 0){
			return getStates(offset, rows);
		}else{
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(State.class);
			buildCiteria(usedSearchFields,criteria);
			return criteria.list();
		}
	}

	public List<State> getStates(int offset, Integer rows)
	{		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM State");
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		List<State> list = (List<State>) query.list();
		session.flush();
		session.close();
		return list;
	}

	public List<Tag> getTags(int offset, Integer rows, List<CriteriaModel> usedSearchFields){
		if(usedSearchFields == null && usedSearchFields.size() == 0){
			return getTags(offset, rows);
		}else{
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Tag.class);
			buildCiteria(usedSearchFields,criteria);
			return criteria.list();
		}
	}

	public List<Tag> getTags(int offset, Integer rows)
	{		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Tag");
		query.setFirstResult(offset);
		query.setMaxResults(rows);
		List<Tag> list = (List<Tag>) query.list();
		session.flush();
		session.close();
		return list;
	}

	public List<Event> listEvents(int offset, Integer rows, List<CriteriaModel> usedSearchFields,Date from,Date to){
		if(usedSearchFields == null && usedSearchFields.size() == 0){
			return listEvents(offset, rows,from,to,"CHOICE");
		}else{
			Session session = getHibernateTemplate().getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(Event.class);
			buildCiteria(usedSearchFields,criteria);
			return criteria.list();
		}
	}

	public Client retreiveClient(String clientId){
		return getHibernateTemplate().get(Client.class, Integer.parseInt(clientId));

	}

	public List<Attribute> loadEntity(String entityName, String entityId){
		Event entity;
		List<Attribute> attributes = new ArrayList<Attribute>();
		
		if("event".equals(entityName)){
			entity = (Event) getHibernateTemplate().load(Event.class, Integer.parseInt(entityId));
			Attribute attr = new Attribute("Id",entity.getId()+"");
			attributes.add(attr);
		}
		
		return attributes;
	}

	public Event retrieveEvent(String eventId){
		return getHibernateTemplate().get(Event.class, Integer.parseInt(eventId));	
	}

	public Room retrieveRoom(String roomId){
		return getHibernateTemplate().get(Room.class, Integer.parseInt(roomId));	
	}
	
	
	public User retrieveUser(String userId){
		return getHibernateTemplate().get(User.class, Integer.parseInt(userId));	
	}
	
	public Subscription retrieveSubscription(String subscriptionId){
		return getHibernateTemplate().get(Subscription.class, Integer.parseInt(subscriptionId));	
	}


	public List<Subscription> loadSubscription(Integer eventId, Integer cleintId){
		return getHibernateTemplate().find("FROM Subscription where event.id = "+eventId+" and client.id = "+cleintId);
	}

	public Subscription getSubscriptionById(Integer subscriptionId){
		return getHibernateTemplate().get(Subscription.class, subscriptionId);
	}

	public void updateSubscription(Subscription subscription){
		getHibernateTemplate().update(subscription);
	}

	public List<Object[]> eventHistory()
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -60);
		Date from= c.getTime();
		c.add(Calendar.DATE, +120);
		Date to = c.getTime();
		
		String sqlQuery = "SELECT DATE_FORMAT(date,'"+DATEFORMAT+"'),count(*) FROM Event "
				+ "WHERE "
				+ "date BETWEEN ? AND ? "
				+ "GROUP BY 1";
		return (List<Object[]>) getHibernateTemplate().find(sqlQuery,from ,to);			
	}

	public void addEvents(List<Event> events)
	{
		getHibernateTemplate().saveOrUpdateAll(events);
		
	}

	public void deleteRoom(int roomId)
	{
		Room room = new Room();
		room.setId(roomId);
		getHibernateTemplate().delete(room);
	}

	public void deleteClient(int clientId)
	{
		Client client = new Client();
		client.setId(clientId);
		getHibernateTemplate().delete(client);		
	}

	public void deleteUser(Integer userId)
	{
		User user = new User();
		user.setId(userId);
		String hql = "delete from UserRole where username= :userId";
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(User.class);
		session.createQuery(hql).setInteger("userId", userId).executeUpdate();
		session.delete(user);
		session.flush();
		session.close();
	}

	public void addClients(List<Client> clientsList){
		getHibernateTemplate().saveOrUpdateAll(clientsList);
		
	}

	public void addState(State state)
	{
		getHibernateTemplate().save(state);
		
	}

	public Integer getRoomsCount()
	{
		return DataAccessUtils.intResult(getHibernateTemplate().find("SELECT count(*) FROM Room"));
	}

	public void addTag(Tag tag)
	{
		getHibernateTemplate().save(tag);
		
	}

	public EntityGroupedBy getEventsGroupedBy(String groupBy,String sourceView)
	{

		Session session = getHibernateTemplate().getSessionFactory().openSession();
		EntityGroupedBy o = new EntityGroupedBy();
		MAX_BORDER_GROUPED_EVENTS_COUNT = Integer.valueOf(ReferenceService.getEnvVariabls().get("event.trending.max"));
		Criteria criteria = session.createCriteria(Event.class);
		if("ROOM".equals(groupBy)){
			criteria.setProjection(Projections.distinct(Projections.property("room")));
			List<Room> list = criteria.list();
			String hql = "";
			if(list != null && list.size()> 0){
				for(Room room: list){
					if(room != null){
						EntityGroup g = new EntityGroup();
						hql = "FROM Event WHERE room.id = :roomId AND rating >=2";
						if("BORDER".equals(sourceView)){
							hql += " and state.id in (1)";
						}
						Query query = session.createQuery(hql);
						query.setParameter("roomId", room.getId());
						query.setMaxResults(MAX_BORDER_GROUPED_EVENTS_COUNT);
						List<Event> events = query.list();
						if(events != null && events.size() >0){
							g.setList(events);
							g.setTitle(room.getName());
							o.getGroups().add(g);							
						}
					}

				}
			}
		}else if ("TAG".equals(groupBy)) {
			criteria.setProjection(Projections.distinct(Projections.property("tag")));
			List<Tag> list = criteria.list();
			String hql = "";
			if(list != null && list.size()> 0){
				for(Tag tag: list){
					if(tag != null){
						EntityGroup g = new EntityGroup();
						hql = "FROM Event WHERE tag.id = :tagId AND rating >=2 ";
						if("BORDER".equals(sourceView)){
							hql += " and state.id in (1)";
						}
						Query query = session.createQuery(hql);
						query.setParameter("tagId", tag.getId());
						query.setMaxResults(MAX_BORDER_GROUPED_EVENTS_COUNT);
						List<Event> events = query.list();
						if(events != null && events.size() >0){
							g.setList(events);
							g.setTitle(tag.getName());
							o.getGroups().add(g);							
						}
					}

				}
			}			
		}else if ("RATING".equals(groupBy)) {
			Conjunction conj = Restrictions.conjunction();
		    conj.add(Restrictions.ge("rating", 2));
			criteria.setProjection(Projections.distinct(Projections.property("rating")));
		    criteria.add(conj);
		    String hql = "";
			List<Integer> list = criteria.list();
			if(list != null && list.size()> 0){
				for(Integer rating: list){
					EntityGroup g = new EntityGroup();
					hql = "FROM Event WHERE rating = :ratingScore";
					if("BORDER".equals(sourceView)){
						hql += " and state.id in (1)";
					}
					Query query = session.createQuery(hql);
					query.setParameter("ratingScore", rating);
					query.setMaxResults(MAX_BORDER_GROUPED_EVENTS_COUNT);
					List<Event> events = query.list();
					if(events != null && events.size() >0){
						g.setList(events);
						g.setTitle(rating+"");
						o.getGroups().add(g);						
					}

				}
			}
		}
		session.flush();
		session.close();
		return o;
	}

	public EntityGroupedBy getEventsInDay(Date day,String sourceView){
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		EntityGroupedBy o = new EntityGroupedBy();
		MAX_BORDER_GROUPED_EVENTS_COUNT = Integer.valueOf(ReferenceService.getEnvVariabls().get("event.trending.max"));
		EntityGroup g = new EntityGroup();
		
		Criteria criteria = session.createCriteria(Event.class);
		Conjunction and = Restrictions.conjunction();
	    //and.add(Restrictions.ge("date", new Date()));
		Date minDay = new Date(day.getTime() - TimeUnit.DAYS.toMillis(2));
	    Date maxDay = new Date(day.getTime() + TimeUnit.DAYS.toMillis(2));
		and.add(Restrictions.ge("date", minDay));
	    and.add(Restrictions.le("date", maxDay));
	    and.add(Restrictions.ge("rating", 2));
	    if("BORDER".equals(sourceView)){
	    	and.add(Restrictions.eq("state.id", 1));
	    }
	    
		criteria.add(and);
		criteria.setMaxResults(MAX_BORDER_GROUPED_EVENTS_COUNT);
		List<Event> events = criteria.list();
		if(events != null && events.size() >0){
			g.setList(events);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			g.setTitle(sdf.format(day));
			o.getGroups().add(g);			
		}

		session.flush();
		session.close();
		return o;
	}

	public EntityGroupedBy getEventsInMonth(String month,String sourceView) throws ParseException{
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		EntityGroupedBy o = new EntityGroupedBy();
		MAX_BORDER_GROUPED_EVENTS_COUNT = Integer.valueOf(ReferenceService.getEnvVariabls().get("event.trending.max"));
		EntityGroup g = new EntityGroup();
		
		Criteria criteria = session.createCriteria(Event.class);
		Conjunction and = Restrictions.conjunction();
		SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
		
		Date minDay = sdfDay.parse(month+"-01");
	    Date maxDay = sdfDay.parse(month+"-31");

//	    and.add(Restrictions.ge("date", new Date()));
		and.add(Restrictions.ge("date", minDay));
	    and.add(Restrictions.le("date", maxDay));
	    and.add(Restrictions.ge("rating", 2));
	    if("BORDER".equals(sourceView)){
	    	and.add(Restrictions.eq("state.id", 1));
	    }

	    criteria.add(and);
		criteria.setMaxResults(MAX_BORDER_GROUPED_EVENTS_COUNT);
		List<Event> events = criteria.list();
		if(events != null && events.size() >0){
			g.setList(events);
			g.setTitle(month);
			o.getGroups().add(g);			
		}

		session.flush();
		session.close();
		return o;
	}

	public Client retrieveClientByIdentityNumber(String numPme){
		List<Client> clients = getHibernateTemplate().find("From Client where identityNumber = '"+numPme+"'");
		if(clients != null && clients.size() > 0){
			return clients.get(0);	
		}else {
			return null;
		}			
	}

}
