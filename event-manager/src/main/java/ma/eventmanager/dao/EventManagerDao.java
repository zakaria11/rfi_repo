package ma.eventmanager.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import ma.evenetmanager.criteria.CriteriaModel;
import ma.eventmanager.entitys.Client;
import ma.eventmanager.entitys.Event;
import ma.eventmanager.entitys.Room;
import ma.eventmanager.entitys.State;
import ma.eventmanager.entitys.Subscription;
import ma.eventmanager.entitys.Tag;
import ma.eventmanager.entitys.User;
import ma.eventmanager.model.Attribute;
import ma.eventmanager.model.EntityGroup;
import ma.eventmanager.model.EntityGroupedBy;

public interface EventManagerDao {

	//dao EVnmt
	public void addEvent(Event event);

	public List<Event> listEvents(int offset, Integer rows,Date from,Date to,String sourceView);

	public void deleteEvent(Integer eventId);

	public void updateEvent(Event event);

	public void saveSubscription(Subscription subscription);
	//dao User

	public User getUsers(String username);

	public void addUser(User user,List<String> roles);

	public Integer getUsersCount();

	public List<User> getUsers(int offset, Integer rows);

	public void addUser(User user);

	public void editUser(User user);

	

	public Integer getEventsCount();
	//dao client

	public List<Client> listClients(int offset, Integer rows);

	public Integer addClient(Client client);

	public List<User> getUsers(int offset, Integer rows, List<CriteriaModel> usedSearchFields);

	public List<Client> listClients(int offset, Integer rows, List<CriteriaModel> usedSearchFields);

	public Integer getClientsCount();


	//Rooms
	public List<Room> getRooms(int offset, Integer rows, List<CriteriaModel> usedSearchFields);
	public List<Room> getRooms(int offset, Integer rows);
	public Integer addRoom(Room room);

	//Tags 
	public List<Tag> getTags(int offset, Integer rows, List<CriteriaModel> usedSearchFields);
	public List<Tag> getTags(int offset, Integer rows);
	
	//States
	public List<State> getStates(int offset, Integer rows, List<CriteriaModel> usedSearchFields);
	public List<State> getStates(int offset, Integer rows);
	
	
	
	
	public List<Event> listEvents(int offset, Integer rows, List<CriteriaModel> usedSearchFields,Date from,Date to);

	public Client retreiveClient(String entityId);

	public List<Attribute> loadEntity(String entityName, String entityId);

	public Event retrieveEvent(String eventId);
	
	public Room retrieveRoom(String roomId);
	
	public User retrieveUser(String userId);
	
	public Subscription retrieveSubscription(String subscriptionId);
	
	public List<Subscription> loadSubscription(Integer eventId, Integer cleintId);

	public Subscription getSubscriptionById(Integer subscriptionId);

	public void updateSubscription(Subscription subscription);

	public List<Object[]> eventHistory();

	public void addEvents(List<Event> events);

	public void updateRoom(Room room);

	public void updateClient(Client client);

	public void updateUser(User user);

	public void deleteRoom(Integer roomId);

	public void deleteClient(Integer clientId);

	public void deleteUser(Integer userId);

	public void addClients(List<Client> clientsList);

	public void addState(State state);

	public Integer getRoomsCount();

	public void addTag(Tag tag);

	public EntityGroupedBy getEventsGroupedBy(String groupBy,String sourceView);

	public EntityGroupedBy getEventsInDay(Date date, String sourceView);

	public EntityGroupedBy getEventsInMonth(String date, String sourceView) throws ParseException;

	public void saveClient(Client client);

	public Client retrieveClientByIdentityNumber(String numPme);



}
