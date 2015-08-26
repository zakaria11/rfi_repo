package ma.eventmanager.dao;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ma.eventmanager.entitys.Event;
import ma.eventmanager.entitys.Subscription;
import ma.eventmanager.entitys.User;

public interface EventManagerDao {

	void addEvent(Event event);

	List<Event> listEvents();

	void deleteEvent(Integer eventId);

	Event retreivEvent(Integer eventId);

	void updateEvent(Event event);

	void saveSubscription(Subscription subscription);

	public User getUsers(String username);

	public void addUser(String login,String password,List<String> roles);

	Integer getUsersCount();

	List<User> getUsers(int offset, Integer rows);

}
