package ma.eventmanager.dao;

import java.util.List;

import ma.evenetmanager.criteria.CriteriaModel;
import ma.eventmanager.entitys.Client;
import ma.eventmanager.entitys.Event;
import ma.eventmanager.entitys.Room;
import ma.eventmanager.entitys.Subscription;
import ma.eventmanager.entitys.User;

public interface EventManagerDao {

	public void addEvent(Event event);

	public List<Event> listEvents(int offset, Integer rows);

	public void deleteEvent(Integer eventId);

	public Event retreivEvent(Integer eventId);

	public void updateEvent(Event event);

	public void saveSubscription(Subscription subscription);

	public User getUsers(String username);

	public void addUser(User user,List<String> roles);

	public Integer getUsersCount();

	public List<User> getUsers(int offset, Integer rows);

	public void addUser(User user);

	public void editUser(User user);

	public List<Room> getRooms(int offset, Integer rows);

	public void addRoom(Room room);

	public Integer getEventsCount();

	public List<Client> listClients(int offset, Integer rows);

	public void addClient(Client client);

	public List<User> getUsers(int offset, Integer rows, List<CriteriaModel> usedSearchFields);

	public List<Client> listClients(int offset, Integer rows, List<CriteriaModel> usedSearchFields);

	public Integer getClientsCount();

	public List<Room> getRooms(int offset, Integer rows, List<CriteriaModel> usedSearchFields);

	public List<Event> listEvents(int offset, Integer rows, List<CriteriaModel> usedSearchFields);

	public Client retreivClient(Integer selectedClientId);

}
